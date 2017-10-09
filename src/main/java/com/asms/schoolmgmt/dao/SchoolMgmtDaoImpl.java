package com.asms.schoolmgmt.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import com.asms.schoolmgmt.entity.TimeTable;
import com.asms.schoolmgmt.request.AdditionalSubjectsDetails;
import com.asms.schoolmgmt.request.BroadCasteSearchTypesDetails;
import com.asms.schoolmgmt.request.ClassDetails;
import com.asms.schoolmgmt.request.GroupDetails;
import com.asms.schoolmgmt.request.SchoolDetails;
import com.asms.schoolmgmt.request.SectionDetails;
import com.asms.schoolmgmt.request.SubjectDetails;
import com.asms.schoolmgmt.request.TeacherDetails;
import com.asms.schoolmgmt.request.TimeTableData;
import com.asms.schoolmgmt.request.TimeTableDetails;
import com.asms.schoolmgmt.request.TimeTableOnchangeDetails;
import com.asms.usermgmt.dao.UserMgmtDao;
import com.asms.usermgmt.entity.Admin;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.entity.teachingStaff.TeachingStaff;
import com.asms.usermgmt.entity.teachingStaff.TeachingSubjects;
import com.asms.usermgmt.helper.EntityCreator;
import com.asms.usermgmt.request.UserDetails;

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
	 * com.asms.schoolmgmt.dao.SchoolMgmtDao#createBoradCasteMessage(com.asms.
	 * schoolmgmt.request. BroadCasteSearchTypesDetails, java.lang.String)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asms.schoolmgmt.dao.SchoolMgmtDao#getSections()
	 */
	@Override
	public List<Section> getSections(String tenantId) throws AsmsException {
		Session session = null;
		try {

			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Section S";

				@SuppressWarnings("unchecked")
				List<Section> sections = session.createQuery(hql).list();
				session.close();
				return sections;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSections()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
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
					Set<Class> classes = getAcademicYearClasses(AsmsHelper.getPreviousAcademicYear(academicyear),
							schema);
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

	/*
	 * Method : getTimeTableDetails : gets the timetabledetails input :
	 * className, sectionName,tenant return : TimeTableDetails
	 *
	 */

	@SuppressWarnings("unchecked")
	@Override
	public TimeTableDetails getTimeTableDetails(String academicYear, String className, String sectionName,
			String tenant) throws AsmsException {
		Session session = null;
		Transaction tx = null;

		ResourceBundle messages = AsmsHelper.getMessageFromBundle();
		try {

			String schema = multitenancyDao.getSchema(tenant);
			Class classobject = null;
			String startTime = null;
			String endTime = null;
			String periodDuration = null;
			Set<Breaks> breaks = null;
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from AcademicYear C where C.academicYearFromTo=?";
				AcademicYear academicYearObject = (AcademicYear) session.createQuery(hql).setParameter(0, academicYear)
						.uniqueResult();
				// get the classes for academec year
				if (null == academicYear) {
					throw exceptionHandler.constructAsmsException(
							messages.getString("SCHOOL_SETUP_INVALID_ACADEMICYEAR_CODE"),
							messages.getString("SCHOOL_SETUP_INVALID_ACADEMICYEAR_MSG"));
				}

				Set<Class> classes = academicYearObject.getClasses();
				for (Class c : classes) {
					if (c.getName().equalsIgnoreCase(className)) {
						List<Section> sections = c.getSectionObjects();
						for (Section s : sections) {
							if (s.getName().equalsIgnoreCase(sectionName)) {
								classobject = c;
								break;
							}
						}

					}
				}

				// get start time, endtime, period duration and breaks
				if (null == classobject) {
					throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SETUP_INVALID_CLASS_CODE"),
							messages.getString("SCHOOL_SETUP_INVALID_CLASS_MSG"));
				}
				startTime = classobject.getClassGroupObject().getStartTime();
				endTime = classobject.getClassGroupObject().getEndTime();
				periodDuration = classobject.getClassGroupObject().getPeriodDuration();
				breaks = classobject.getClassGroupObject().getBreaks();

				TimeTableDetails details = createTimeTable(startTime, endTime, periodDuration, breaks);

				List<ClassSubjects> subjects = getSubjectByName(className, sectionName, tenant);
				details.setSubjectDetails(subjects);
				details.setTeachers(getTeachersBySection(className, sectionName, schema));
				// add subjects

				// create time table if already not created
				List<TimeTable> timeTables = createTimeTables(classobject, details);

				hql = "from TimeTable T where sectionObject.classObject.id = ?";
				List<TimeTable> tts = (List<TimeTable>) session.createQuery(hql).setParameter(0, classobject.getId())
						.list();
				if (tts == null || tts.isEmpty()) {
					tx = session.beginTransaction();

					for (TimeTable tt : timeTables) {
						session.save(tt);
					}
					tx.commit();
				}
				session.close();
				return details;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getTimeTableDetails()" + "   ", ex);
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
	 * method : createTimeTable input :starttime, endtime, duration and breaks
	 * output : TimeTableDetails object
	 * 
	 */

	private TimeTableDetails createTimeTable(String startTime, String endTime, String periodDuration,
			Set<Breaks> breaks) throws AsmsException {
		TimeTableDetails details = new TimeTableDetails();
		TimeTableData data = null;
		List<TimeTableData> dataList = new ArrayList<TimeTableData>();
		try {
			// time from front end comes in format HH:mm convert that to
			// HH:mm:ss

			String appendZero = "00";
			startTime = startTime + ":" + appendZero;
			endTime = endTime + ":" + appendZero;
			int duration = Integer.parseInt(periodDuration);
			for (Breaks b : breaks) {
				b.setStartTime(b.getStartTime() + ":" + appendZero);
				b.setEndTime(b.getEndTime() + ":" + appendZero);
			}

			// create calendar objects as we need to compare times
			Calendar startTimeCalendar = null;
			Calendar endTimeCalendar = null;
			Calendar breakCalender = null;
			List<Calendar> breakCalenders = new ArrayList<Calendar>();

			Date time1;

			time1 = new SimpleDateFormat("HH:mm:ss").parse(startTime);
			startTimeCalendar = Calendar.getInstance();
			startTimeCalendar.setTime(time1);

			time1 = new SimpleDateFormat("HH:mm:ss").parse(endTime);
			endTimeCalendar = Calendar.getInstance();
			endTimeCalendar.setTime(time1);

			for (Breaks b : breaks) {
				// in breakCalenders time in even position is break start time
				// and odd position is end time
				time1 = new SimpleDateFormat("HH:mm:ss").parse(b.getStartTime());
				breakCalender = Calendar.getInstance();
				breakCalender.setTime(time1);
				breakCalenders.add(breakCalender);
				time1 = new SimpleDateFormat("HH:mm:ss").parse(b.getEndTime());
				breakCalender = Calendar.getInstance();
				breakCalender.setTime(time1);
				breakCalenders.add(breakCalender);
			}

			// sort break times
			Collections.sort(breakCalenders);

			String hour = null;
			String minute = null;
			int i = 0;

			while (startTimeCalendar.getTime().before(endTimeCalendar.getTime())) {

				data = new TimeTableData();
				int hourToAdd = duration / 60;
				int minuteToAdd = duration % 60;

				if (i < breakCalenders.size()) {
					startTimeCalendar.add(Calendar.MINUTE, duration);
					if (startTimeCalendar.getTime().compareTo(breakCalenders.get(i).getTime()) <= 0) {
						startTimeCalendar.add(Calendar.MINUTE, -duration);
						hour = String.valueOf(startTimeCalendar.get(Calendar.HOUR_OF_DAY));
						if (hour.length() == 1) {
							hour = "0" + hour;
						}
						minute = String.valueOf(startTimeCalendar.get(Calendar.MINUTE));
						if (minute.length() == 1) {
							minute = minute + "0";
						}
						data.setPeriodStartTime(hour + ":" + minute);

						startTimeCalendar.add(Calendar.HOUR_OF_DAY, hourToAdd);
						startTimeCalendar.add(Calendar.MINUTE, minuteToAdd);

						hour = String.valueOf(startTimeCalendar.get(Calendar.HOUR_OF_DAY));
						if (hour.length() == 1) {
							hour = "0" + hour;
						}
						minute = String.valueOf(startTimeCalendar.get(Calendar.MINUTE));
						if (minute.length() == 1) {
							minute = minute + "0";
						}
						data.setPeriodEndTime(hour + ":" + minute);

						dataList.add(data);
					} else {

						startTimeCalendar.add(Calendar.MINUTE, -duration);
						hour = String.valueOf(breakCalenders.get(i).get(Calendar.HOUR_OF_DAY));
						if (hour.length() == 1) {
							hour = "0" + hour;
						}
						minute = String.valueOf(breakCalenders.get(i).get(Calendar.MINUTE));
						if (minute.length() == 1) {
							minute = minute + "0";
						}
						data.setPeriodStartTime(hour + ":" + minute);
						data.setBreak(true);
						hour = String.valueOf(breakCalenders.get(i + 1).get(Calendar.HOUR_OF_DAY));
						if (hour.length() == 1) {
							hour = "0" + hour;
						}
						minute = String.valueOf(breakCalenders.get(i + 1).get(Calendar.MINUTE));
						if (minute.length() == 1) {
							minute = minute + "0";
						}
						data.setPeriodEndTime(hour + ":" + minute);
						startTimeCalendar.set(Calendar.HOUR_OF_DAY,
								breakCalenders.get(i + 1).get(Calendar.HOUR_OF_DAY));
						startTimeCalendar.set(Calendar.MINUTE, breakCalenders.get(i + 1).get(Calendar.MINUTE));
						dataList.add(data);

						i = i + 2;
					}
				} else {
					hour = String.valueOf(startTimeCalendar.get(Calendar.HOUR_OF_DAY));
					if (hour.length() == 1) {
						hour = "0" + hour;
					}
					minute = String.valueOf(startTimeCalendar.get(Calendar.MINUTE));
					if (minute.length() == 1) {
						minute = minute + "0";
					}
					data.setPeriodStartTime(hour + ":" + minute);

					startTimeCalendar.add(Calendar.HOUR_OF_DAY, hourToAdd);
					startTimeCalendar.add(Calendar.MINUTE, minuteToAdd);

					hour = String.valueOf(startTimeCalendar.get(Calendar.HOUR_OF_DAY));
					if (hour.length() == 1) {
						hour = "0" + hour;
					}
					minute = String.valueOf(startTimeCalendar.get(Calendar.MINUTE));
					if (minute.length() == 1) {
						minute = minute + "0";
					}
					data.setPeriodEndTime(hour + ":" + minute);

					dataList.add(data);

				}

			}
			details.setTimeTableData(dataList);
			return details;

		} catch (ParseException e) {
			throw exceptionHandler.constructAsmsException("11", "Invalid time sent");
		}

	}

	@Override
	public List<ClassSubjects> getSubjectByName(String className, String sectionName, String tenantId)
			throws AsmsException {
		Session session = null;
		String schema = multitenancyDao.getSchema(tenantId);
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from ClassSubjects C where C.sectionObject.classObject.name ='" + className
					+ "' and  C.sectionObject.name='" + sectionName + "'";
			List<ClassSubjects> subjects = session.createQuery(hql).list();
			session.close();
			return subjects;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSubjectByName()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<TeacherDetails> getTeachersBySection(String className, String sectionName, String schema)
			throws AsmsException {
		Session session = null;

		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "select C.teachingObject from TeachingSubjects C where C.classObject.name ='" + className
					+ "' and  C.sectionObject.name='" + sectionName + "'";
			List<User> users = session.createQuery(hql).list();
			session.close();
			List<TeacherDetails> teachers = new ArrayList<TeacherDetails>();
			TeacherDetails details = null;
			for (User u : users) {
				details = new TeacherDetails();
				TeachingStaff t = (TeachingStaff) u;
				details.setName(t.getFirstName() + " " + t.getLastName());
				details.setUserId(t.getSerialNo());
				teachers.add(details);
			}
			return teachers;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSubjectByName()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	// method to create timetable for the first time
	private List<TimeTable> createTimeTables(Class classObject, TimeTableDetails details) {
		TimeTable tt = null;
		List<TimeTableData> ttds = details.getTimeTableData();
		List<Section> sections = classObject.getSectionObjects();
		List<TimeTable> timeTables = new ArrayList<TimeTable>();
		for (Section s : sections) {
			for (TimeTableData ttd : ttds) {

				tt = createTimeTable(tt, ttd, "Monday", s);
				timeTables.add(tt);
				tt = createTimeTable(tt, ttd, "Tuesday", s);
				timeTables.add(tt);
				tt = createTimeTable(tt, ttd, "Wednesday", s);
				timeTables.add(tt);
				tt = createTimeTable(tt, ttd, "Thursday", s);
				timeTables.add(tt);
				tt = createTimeTable(tt, ttd, "Friday", s);
				timeTables.add(tt);
				tt = createTimeTable(tt, ttd, "Saturday", s);
				timeTables.add(tt);

			}

		}
		return timeTables;
	}

	private TimeTable createTimeTable(TimeTable tt, TimeTableData ttd, String day, Section s) {
		tt = new TimeTable();
		tt.setDay(day);
		tt.setSectionObject(s);
		tt.setTeachingObject(null);
		tt.setTimeFrom(ttd.getPeriodStartTime());
		tt.setTimeTo(ttd.getPeriodEndTime());
		if (ttd.isBreak()) {
			tt.setSubjectName("break");
		}
		return tt;
	}

	@Override
	public void TimeTableOnChange(TimeTableOnchangeDetails details, String tenantId) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		String schema = multitenancyDao.getSchema(tenantId);
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from TeachingSubjects C where C.teachingObject.serialNo =?";

			TeachingSubjects ts = (TeachingSubjects) session.createQuery(hql).setParameter(0, details.getTeacherId())
					.uniqueResult();
			if (null == ts) {
				throw exceptionHandler.constructAsmsException(messages.getString("TEACHER_ID_INVALID_CODE"),
						messages.getString("TEACHER_ID_INVALID_MSG"));
			}
			hql = "from TimeTable C where C.timeFrom =? and C.timeTo = ? and C.day = ? and C.sectionObject.name = ? and C.sectionObject.classObject.name = ?";
			TimeTable tt = (TimeTable) session.createQuery(hql).setParameter(0, details.getTimeFrom())
					.setParameter(1, details.getTimeTo()).setParameter(2, details.getDay())
					.setParameter(3, details.getSectionName()).setParameter(4, details.getClassName()).uniqueResult();
			tx = session.beginTransaction();
			tt = (TimeTable) session.load(TimeTable.class, tt.getSerialNo());
			tt.setTeachingObject(ts.getTeachingObject());
			session.update(tt);
			tx.commit();

			session.close();

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "TimeTableOnChange()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherDetails> getTeachersOnChange(String from, String to, String day, String className,
			String section, String tenantId) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		String schema = multitenancyDao.getSchema(tenantId);
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from TeachingSubjects C where C.sectionObject.name =? and C.sectionObject.classObject.name =?";

			List<TeachingSubjects> ts = session.createQuery(hql).setParameter(0, section).setParameter(1, className)
					.list();

			hql = "select C.teachingObject from TimeTable C where C.timeFrom =? and C.timeTo = ? and C.day = ? and C.sectionObject.name = ? and C.sectionObject.classObject.name = ?"
					+ "and C.teachingObject != null";
			List<TeachingStaff> tss = (List<TeachingStaff>) session.createQuery(hql).setParameter(0, from)
					.setParameter(1, to).setParameter(2, day).setParameter(3, section).setParameter(4, className)
					.list();

			List<TeacherDetails> teachers = new ArrayList<>();
			for (TeachingSubjects tso : ts) {
				boolean teacherBusy = false;
				TeacherDetails td = null;
				for (TeachingStaff tsso : tss) {
					if (tso.getTeachingObject().getSerialNo() == tsso.getSerialNo()) {
						teacherBusy = true;
					}
				}
				if (teacherBusy == false) {
					td = new TeacherDetails();
					td.setName(tso.getTeachingObject().getFirstName() + " " + tso.getTeachingObject().getMiddleName()
							+ " " + tso.getTeachingObject().getLastName());
					td.setUserId(tso.getTeachingObject().getSerialNo());
					teachers.add(td);
				}
			}

			session.close();
			return teachers;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getTeachersOnChange()" + "   ", e);
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
	public List<AdditionalSubjects> getAdditionalSubjects(String className, String sectionName, String tenantId)
			throws AsmsException {
		Session session = null;
		String schema = multitenancyDao.getSchema(tenantId);
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from AdditionalSubjects C where C.sectionObject.classObject.name ='" + className
					+ "' and  C.sectionObject.name='" + sectionName + "'";
			List<AdditionalSubjects> subjects = session.createQuery(hql).list();
			session.close();
			return subjects;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSubjectByName()" + "   ", e);
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
	public List<SubjectDetails> getsubjectsAndAdditionalsubjects(String className, String sectionName, String tenantId)
			throws AsmsException {
		Session session = null;
		 
		  String schema = multitenancyDao.getSchema(tenantId);
		    try {
		         session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
		         
		         
		         List<AdditionalSubjects> ads = session.createQuery("from AdditionalSubjects C where C.sectionObject.classObject.name ='" + className
		       + "' and  C.sectionObject.name='" + sectionName + "'").list();

		         List<ClassSubjects> cls = session.createQuery("from ClassSubjects C where C.sectionObject.classObject.name ='" + className
		       + "' and  C.sectionObject.name='" + sectionName + "'").list();
		       
		        
		       List<SubjectDetails> subjects = new ArrayList<SubjectDetails>();
		         
		         for(AdditionalSubjects ad : ads){
		          SubjectDetails sb = new SubjectDetails ();
		          sb.setName(ad.getName());
		   
		  subjects.add(sb);       }
		         for(AdditionalSubjects cs : ads){
		          SubjectDetails sb = new SubjectDetails ();
		          sb.setName( cs.getName());
		  subjects.add(sb);
		  return subjects;
		   }
		        
		   session.close();
		   
		         
		  } catch (Exception e) {
		   if (session.isOpen()) {
		    session.close();
		   }
		   logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
		     + "getSubjectByName()" + "   ", e);
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
}