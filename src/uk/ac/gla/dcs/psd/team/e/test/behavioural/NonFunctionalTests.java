package uk.ac.gla.dcs.psd.team.e.test.behavioural;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.gla.dcs.psd.team.e.components.administrator.AdminAccess;
import uk.ac.gla.dcs.psd.team.e.components.administrator.AdminAccessFactory;
import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.components.db.fake.Database;
import uk.ac.gla.dcs.psd.team.e.components.lecturer.LecturerAccess;
import uk.ac.gla.dcs.psd.team.e.components.lecturer.LecturerAccessFactory;
import uk.ac.gla.dcs.psd.team.e.components.mycampus.IMyCampus;
import uk.ac.gla.dcs.psd.team.e.components.mycampus.stub.MyCampusStub;
import uk.ac.gla.dcs.psd.team.e.components.student.StudentAccess;
import uk.ac.gla.dcs.psd.team.e.components.student.StudentAccessFactory;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.mycampus.MyCampusFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.impl.FunctionRepository;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Course;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.CourseFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.IIdentifiable;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.SessionFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.SessionHasTimetableSlot;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.TimetableSlot;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.TimetableSlotFunctions;
import uk.ac.gla.dcs.psd.team.e.components.users.UserAccess;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.AuthResult;

public class NonFunctionalTests {
	private static LecturerAccessFactory lecturerAccessFactory = new LecturerAccessFactory();
	private static StudentAccessFactory studentAccessFactory = new StudentAccessFactory();
	private static IDatabase mockDatabase = new Database();
	private static FunctionRepository funcRepo = new FunctionRepository();
	private static IMyCampus myCampus = new MyCampusStub(funcRepo);
	private static LecturerAccess lecturerAccess = lecturerAccessFactory.newInstance();
	private static AdminAccess adminAccess =  new AdminAccessFactory().newInstance();
	private static StudentAccess studentAccess =  new StudentAccessFactory().newInstance();
	private static MyCampusFunctions myCampusFunctions = new MyCampusFunctions(myCampus);
	private static CourseFunctions courseFunctions = new CourseFunctions(mockDatabase);
	private static SessionFunctions sessionFunctions = new SessionFunctions(mockDatabase);
	private static TimetableSlotFunctions timetableSlotFunctions = new TimetableSlotFunctions(mockDatabase);

	@BeforeClass
	public static void setup() {
		funcRepo.registerFunction(myCampusFunctions);
		funcRepo.registerFunction(courseFunctions);
		funcRepo.registerFunction(sessionFunctions);
		funcRepo.registerFunction(timetableSlotFunctions);
		
		lecturerAccess.setFuncRepo(funcRepo);
		adminAccess.setFuncRepo(funcRepo);
		studentAccess.setFuncRepo(funcRepo);
		
		studentAccess.setUsername("Adam");
		adminAccess.setUsername("root");
		lecturerAccess.setUsername("Tim");		
	}

	@Test
	public void nonFunctionalSecurity0True(){
		AuthResult result = myCampus.auth("admin", "1234");
		assertTrue(result.success);
	}
	
	@Test
	public void nonFunctionalSecurity0False(){
		AuthResult result = myCampus.auth("me", "1234");
		assertEquals("Test returned a null", null, result);
	}
	
	@Test
	public void nonFunctionalPerformance0(){
		int before = mockDatabase.getAll(Course.class).size();
		for(int i=1; i<=100; i++)
			mockDatabase.add(new Course(Integer.toString(i), Integer.toString(i)), Course.class);
		List<IIdentifiable> courseList = mockDatabase.getAll(Course.class);
		assertEquals("Test supports 100 courses", 100, courseList.size() - before);
	}
	
	@Test
	public void nonFunctionalPerformance1(){
		mockDatabase.add(new Course("NewCourse", "NewCourse"), Course.class);
		for (int i=1; i<=10; i++) {
			courseFunctions.addSessionToCourse(new Session(Integer.toString(i), Integer.toString(i), 1, 50, 12, true), "NewCourse");
		}
		Collection<Session> sessionList = courseFunctions.getCourseSessions("NewCourse");
		assertEquals("Test supports 10 courses", 10, sessionList.size());
	}
	
	@Test
	public void nonFunctionalPerformance3(){
		mockDatabase.add(new Session("OurSession", "OurSession", 1, 50, 12, true), Session.class);
		for (int i=1; i<=20; i++) {
			TimetableSlot timetableSlot = new TimetableSlot(Integer.toString(i), new Date(), "Bo103", Integer.toString(i) + "5562",null,1);
			mockDatabase.add(timetableSlot, TimetableSlot.class);
			mockDatabase.add(new SessionHasTimetableSlot(Integer.toString(i), "OurSession", Integer.toString(i)), SessionHasTimetableSlot.class);
		}
		List<IIdentifiable> timetableSlotList = mockDatabase.getAll(SessionHasTimetableSlot.class);
		int count = 0;
		for (IIdentifiable timetableSlot: timetableSlotList){
			if (((SessionHasTimetableSlot) timetableSlot).getSessionId().equals("OurSession")){
				count++;
			}
		}
		assertEquals("System supports 20 timetable slots per session", 20, count);
	}

	@Test
	public void nonFunctionalPerformance4(){
		ArrayList<UserAccess> userAccess = new ArrayList<UserAccess>();
		userAccess.add(adminAccess);
		for(int i=1; i<=10; i++)
			userAccess.add(lecturerAccessFactory.newInstance());
		for(int i=1; i<=100; i++)
			userAccess.add(studentAccessFactory.newInstance());
		assertEquals("System supports 100 concurrent users", 111, userAccess.size());
	}
	
}