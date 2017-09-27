package com.asms.schoolmgmt.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.annotation.Resource;
import javax.security.auth.Subject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.common.mail.EmailSender;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.asms.schoolmgmt.entity.AcademicYear;
import com.asms.schoolmgmt.entity.AdditionalSubjects;
import com.asms.schoolmgmt.entity.Breaks;
import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.entity.ClassGroup;
import com.asms.schoolmgmt.entity.ClassSubjects;
import com.asms.schoolmgmt.entity.School;
import com.asms.schoolmgmt.entity.Section;
import com.asms.schoolmgmt.entity.SetupSchoolDetails;
import com.asms.schoolmgmt.request.AdditionalSubjectsDetails;
import com.asms.schoolmgmt.request.BroadCasteSearchTypesDetails;
import com.asms.schoolmgmt.request.ClassDetails;
import com.asms.schoolmgmt.request.GroupDetails;
import com.asms.schoolmgmt.request.SchoolDetails;
import com.asms.schoolmgmt.request.SectionDetails;
import com.asms.schoolmgmt.request.SubjectDetails;
import com.asms.usermgmt.dao.UserMgmtDao;
import com.asms.usermgmt.entity.Admin;
import com.asms.usermgmt.entity.student.Student;

@Service
@Component
public class SchoolMgmtDaoImpl implements SchoolMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(SchoolMgmtDaoImpl.class);

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Autowired
	private MultitenancyDao multitenancyDao;

	@Autowired
	private UserMgmtDao userMgmtDao;

	@Autowired
	private EmailSender emailSender;

	ResourceBundle messages;

	/*
	 * Method : getUserRole : gets the user role from database input : String
	 * (email) return : String
	 *
	 */

	@Override
	public Class getClassByName(String name, String schema) throws AsmsException {
		Session session = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from Class C where C.name=?";
			Class classObject = (Class) session.createQuery(hql).setParameter(0, name).uniqueResult();
			session.close();
			return classObject;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getClassByName()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public Section getSectionByName(String className, String sectionName, String schema) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from Section C where C.name=? and C.classObject.name = ?";
			Section section = (Section) session.createQuery(hql).setParameter(0, sectionName).setParameter(1, className)
					.uniqueResult();
			session.close();
			return section;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSectionByName()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<String> getNames() throws AsmsException {

		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "select B.boardNames from BoardMaster B";

			@SuppressWarnings("unchecked")
			List<String> names = session.createQuery(hql).list();
			session.close();
			return names;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getNames()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<Class> getClasses(String tenantId) throws AsmsException {
		Session session = null;
		try {

			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Class C";

				@SuppressWarnings("unchecked")
				List<Class> classes = session.createQuery(hql).list();
				session.close();
				return classes;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getClasses()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	private Set<Class> getAcademicYearClasses(String academicYear, String schema) throws AsmsException {
		Session session = null;
		Set<Class> classes = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from AcademicYear A where A.academicYearFromTo = ?";

			@SuppressWarnings("unchecked")
			AcademicYear aYear = (AcademicYear) session.createQuery(hql).setParameter(0, academicYear).uniqueResult();
			session.close();
			if (null != aYear) {
				classes = aYear.getClasses();
			}
			return classes;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getClasses()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public List<String> getSujects(int boardId) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "select B.subjectName from BoardSubjectMaster B where B.boardId='" + boardId + "'";

			@SuppressWarnings("unchecked")
			List<String> subjects = session.createQuery(hql).list();
			session.close();
			return subjects;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSujects()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public List<String> getClassSujects(int classId, String tenantId) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "select C.classSubjectName from ClassSubjects C where C.classId='" + classId + "'";

				@SuppressWarnings("unchecked")
				List<String> user = session.createQuery(hql).list();
				session.close();
				return user;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getClassSujects()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.schoolmgmt.dao.SchoolMgmtDao#createSchool(com.asms.schoolmgmt.
	 * request.SchoolDetails)
	 */
	@Override
	public void createSchool(SchoolDetails schoolDetails, String schema) throws AsmsException {

		School school = new School();
		school.setId(schoolDetails.getId());
		school.setName(schoolDetails.getName());
		school.setAffiliationId(schoolDetails.getAffiliationId());
		school.setRegistrationCode(schoolDetails.getRegistrationCode());
		school.setWebsite(schoolDetails.getWebsite());
		school.setLogo(schoolDetails.getLogo());
		school.setAddressLine1(schoolDetails.getAddressLine1());
		school.setAddressLine2(schoolDetails.getAddressLine2());
		school.setLocation(schoolDetails.getLocation());
		school.setCountry(schoolDetails.getCountry());
		school.setState(schoolDetails.getState());
		school.setDistrict(schoolDetails.getDistrict());
		school.setSubdivision(schoolDetails.getSubdivision());
		school.setTehsil(schoolDetails.getTehsil());
		school.setVillage(schoolDetails.getVillage());
		school.setPincode(Integer.parseInt(schoolDetails.getPincode()));
		school.setContactNo1(schoolDetails.getContactNo1());
		school.setContactNo2(schoolDetails.getContactNo2());
		school.setMobileNo(schoolDetails.getMobileNo());
		school.setEmailId(schoolDetails.getEmailId());
		school.setTotalNoOfStudents(schoolDetails.getTotalNoOfStudents());
		school.setContactPersonName(schoolDetails.getContactPersonName());
		school.setContactPersonEmailId(schoolDetails.getContactPersonEmailId());
		school.setGpsLatitude(schoolDetails.getGpsLatitude());
		school.setGpsLongitude(schoolDetails.getGpsLongitude());
		// enter trust id if not null and it has only integer value
		if (null != schoolDetails.getTrustId()) {
			if (schoolDetails.getTrustId().matches("[0-9]+")) {
				school.setTrustId(Integer.parseInt(schoolDetails.getTrustId()));
			}
		}

		insertSchool(school, schema);

		// return null;

	}

	public void insertSchool(School school, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			Role role = (Role) session.createQuery("from Role R where R.roleName = ?")
					.setParameter(0, Constants.role_admin).uniqueResult();

			SubRole sRole = (SubRole) session.createQuery("from SubRole S where S.subRoleName = ?")
					.setParameter(0, Constants.role_admin_subrole_admin).uniqueResult();

			session.save(school);
			Admin admin = createAdmin(school);
			userMgmtDao.createDefaultPrivileges(Constants.role_admin, admin);
			admin.setRoleObject(role);
			admin.setSubRoleObject(sRole);

			session.save(admin);

			tx.commit();
			session.close();
		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertSchool()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	private Admin createAdmin(School school) throws AsmsException {

		Admin admin = new Admin();
		admin.setCreatedOn(new Date());
		admin.setEmail(school.getContactPersonEmailId());
		admin.setIsNew("true");
		admin.setName(school.getContactPersonName());
		admin.setStatus("Complete");
		admin.setUserId(generateUserId());
		admin.setUserPassword("password");
		admin.setSchoolId(school.getSerialNo());

		return admin;

	}

	// generate userid
	private String generateUserId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public School getSchool(String schema) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from School S";

			@SuppressWarnings("unchecked")
			List<School> schools = session.createQuery(hql).list();

			session.close();
			return schools.get(0);

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSchool" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.schoolmgmt.dao.SchoolMgmtDao#setupSchool(com.asms.schoolmgmt.
	 * entity.SetupSchoolDetails, java.lang.String)
	 */
	@Override
	public void setupSchool(SetupSchoolDetails setupSchoolDetail, String tenant) throws AsmsException {
		// TODO Auto-generated method stub
		/**
		 * <<<<<<< HEAD <<<<<<< HEAD
		 * 
		 * @{author} mallikarjun.guranna 13-Sep-2017
		 */

		Class classes;
		List<ClassDetails> classDetails = (List<ClassDetails>) setupSchoolDetail.getClassDetails();
		Section section;
		ClassSubjects sub;
		AdditionalSubjects addSubs;
		Set<ClassSubjects> subList;
		AcademicYear academicYear;
		Set<AdditionalSubjects> addSubsList;
		Set<Class> classObjects = new HashSet<Class>();
		academicYear = new AcademicYear();
		academicYear.setAcademicYearFromTo(setupSchoolDetail.getCurrentAcademicYear());
		Session session = null;
		Transaction tx = null;
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		try {

			String schema = multitenancyDao.getSchema(tenant);
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from AcademicYear A where A.academicYearFromTo = ?";
			AcademicYear aYearInDB = (AcademicYear) session.createQuery(hql)
					.setParameter(0, setupSchoolDetail.getCurrentAcademicYear()).uniqueResult();
			session.close();
			if (null == aYearInDB) {

				for (ClassDetails cd : classDetails) {
					classes = new Class();
					classes.setName(cd.getName().trim());
					classes.setBoardId(cd.getBoardId());

					List<SectionDetails> sectionDetails = cd.getSectionDetails();
					if (null != sectionDetails) {
						for (SectionDetails se : sectionDetails) {
							section = new Section();
							section.setName(se.getName().trim());

							List<SubjectDetails> subjectDetails = se.getSubjectDetails();
							if (null != subjectDetails) {
								subList = new HashSet<ClassSubjects>();
								for (SubjectDetails sd : subjectDetails) {
									sub = new ClassSubjects();
									sub.setName(sd.getName().trim());
									sub.setSectionObject(section);
									subList.add(sub);
								}
								section.setSubjects(subList);
							}
							List<AdditionalSubjectsDetails> addSubjectDetails = se.getAdditionalSubjectsDetails();
							addSubsList = new HashSet<AdditionalSubjects>();
							if (null != addSubjectDetails) {
								for (AdditionalSubjectsDetails asd : addSubjectDetails) {
									addSubs = new AdditionalSubjects();
									addSubs.setName(asd.getName());
									addSubs.setSectionObject(section);
									addSubsList.add(addSubs);
								}
								section.setAdditionalSubjects(addSubsList);
							}

							section.setClassObject(classes);
							classes.getSectionObjects().add(section);

						}
					}
					// academicYear.getClasses().add(classes);
					// classes.getAcademicYears().add(academicYear);

					classObjects.add(classes);
					academicYear.setClasses(classObjects);

				}

				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				tx = session.beginTransaction();

				session.save(academicYear);

				tx.commit();
				session.close();
			} else {
				throw exceptionHandler.constructAsmsException("11", "setup already done");
			}

		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "setupSchool()" + "   ", ex);

			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.schoolmgmt.dao.SchoolMgmtDao#get(com.asms.schoolmgmt.request.
	 * BroadCasteSearchTypesDetails, java.lang.String)
	 */

	@Override
	public List<String> createBoradCasteMessage(BroadCasteSearchTypesDetails searchTypesDetails, String tenantId)
			throws AsmsException {
		Session session = null;
		String sDate1 = searchTypesDetails.getDateOfIssue();
		DateFormat edtFormat = new SimpleDateFormat("yyyy-mm-dd");

		try {
			Date aLD = edtFormat.parse(sDate1);
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				List<String> toEmailIdQry = new ArrayList<>();

				String hql = "select S.name from School S";
				String school = (String) session.createQuery(hql).uniqueResult();

				if (searchTypesDetails.isAllParents() == true) {
					toEmailIdQry.add("select fEmail from Parent");
				}
				if (searchTypesDetails.isAllStudents() == true) {
					toEmailIdQry.add("select studentCreatedByWadmin from Student");
				}

				if (searchTypesDetails.isAllStudents() == true && searchTypesDetails.isAllParents() == true
						&& searchTypesDetails.isAllManagement() == false) {
					toEmailIdQry.add("select studentCreatedByWadmin from Student");
				}
				if (searchTypesDetails.isAllManagement() == true && searchTypesDetails.isAllParents() == true
						&& searchTypesDetails.isAllStudents() == true) {
					toEmailIdQry.add("select mngmtCreatedByWadmin from Management");
				}
				if (searchTypesDetails.isAllNonTeaching() == true) {
					toEmailIdQry.add("select createdByWadmin from NonTeachingStaff");
				}

				if (searchTypesDetails.isAllTeachingStaff() == true) {
					toEmailIdQry.add("select createdByWadmin from TeachingStaff");
				}

				for (String qry : toEmailIdQry) {
					List<String> emails = session.createQuery(qry).list();

					for (String email : emails) {

						emailSender.send(school, email, "devendrasignh77@gmail.com", searchTypesDetails.getSubject(),
								searchTypesDetails.getMessage(), "text/html", aLD);
					}

				}
				session.close();

			}
		}

		catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "createBoradCasteMessage" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return null;

	}

	/**
	 * method : createGroups : creates classes in groups according start time
	 * and end time and breaks
	 * 
	 * @param List<GroupDetails>
	 *            details
	 * @return
	 * @throws AsmsException
	 */

	@Override
	public void createGroups(List<GroupDetails> details, String tenant) throws AsmsException {

		Session session = null;
		Transaction tx = null;

		List<ClassDetails> classDetailsList;
		Class classObject = null;
		List<ClassGroup> classGroups = new ArrayList<ClassGroup>();
		ClassGroup classGroup = null;
		String hql = null;
		List<Breaks> breakDetails = null;
		String className = null;
		Breaks breakes = null;
		try {
			String schema = multitenancyDao.getSchema(tenant);
			messages = AsmsHelper.getMessageFromBundle();
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();

				for (GroupDetails gd : details) {
					classDetailsList = gd.getClassDetails();
					classGroup = new ClassGroup();
					if (null != classDetailsList) {
						for (ClassDetails cd : classDetailsList) {
							className = cd.getName();

							hql = " from Class C where C.name = ?";
							classObject = (Class) session.createQuery(hql).setParameter(0, className).uniqueResult();
							if (null != classObject) {

								classGroup.getClasses().add(classObject);

							}

						}
						if (!classGroup.getClasses().isEmpty()) {
							classGroup.setStartTime(gd.getStartTime());
							classGroup.setEndTime(gd.getEndTime());
							classGroup.setPeriodDuration(gd.getPeriodDuration());
							breakDetails = gd.getBreaks();
							if (null != breakDetails) {
								for (Breaks b : breakDetails) {

									breakes = new Breaks();

									breakes.setStartTime(b.getStartTime());
									breakes.setEndTime(b.getEndTime());

									breakes.setClassGroup(classGroup);
									classGroup.getBreaks().add(breakes);

								}

								classGroup.setStartTime(gd.getStartTime());
								classGroup.setEndTime(gd.getEndTime());
								classGroups.add(classGroup);

							} else {
								throw exceptionHandler.constructAsmsException(messages.getString("BREAK_NULL_CODE"),
										messages.getString("BREAK_NULL_MSG"));
							}
						}
					} else {
						throw exceptionHandler.constructAsmsException(messages.getString("CLASSGROUPS_NULL_CODE"),
								messages.getString("CLASSGROUPS_NULL_MSG"));
					}
				}

				Set<Class> classes = null;
				Class classToUpdate = null;

				for (ClassGroup cg : classGroups) {
					tx = session.beginTransaction();
					classes = cg.getClasses();
					for (Class c : classes) {
						classToUpdate = (Class) session.load(Class.class, c.getId());
						c.setClassGroupObject(cg);
						session.update(c);
					}

					session.save(cg);

					tx.commit();

				}
				session.close();

			}

			else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "createGroups()" + "   ", ex);

			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		// TODO Auto-generated method stub

	}

	@Override
	public void setupSchoolCopy(String academicyear, String tenantId) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		try {

			String schema = multitenancyDao.getSchema(tenantId);
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from AcademicYear A where A.academicYearFromTo = ?";
			AcademicYear aYearInDB = (AcademicYear) session.createQuery(hql).setParameter(0, academicyear)
					.uniqueResult();
			AcademicYear academicYear = new AcademicYear();
			academicYear.setAcademicYearFromTo(academicyear);

			if (null != schema) {
				if (null == aYearInDB) {
					Set<Class> classes = getAcademicYearClasses(AsmsHelper.getPreviousAcademicYear(academicyear), schema);
					academicYear.setClasses(new HashSet<>(classes));

					tx = session.beginTransaction();

					session.save(academicYear);

					tx.commit();
					session.close();
				} else {
					throw exceptionHandler.constructAsmsException("11", "setup already done");
				}

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "setupSchool()" + "   ", ex);

			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

}
