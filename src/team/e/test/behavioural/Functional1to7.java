package team.e.test.behavioural;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import team.e.components.administrator.AdminAccess;
import team.e.components.administrator.AdminAccessFactory;
import team.e.components.db.IDatabase;
import team.e.components.db.mock.Database;
import team.e.components.lecturer.LecturerAccess;
import team.e.components.lecturer.LecturerAccessFactory;
import team.e.components.mycampus.IMyCampus;
import team.e.components.mycampus.stub.MyCampusStub;
import team.e.components.student.StudentAccess;
import team.e.components.student.StudentAccessFactory;
import team.e.components.sysfunc.mycampus.MyCampusFunctions;
import team.e.components.sysfunc.repository.impl.FunctionRepository;
import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.CourseFunctions;
import team.e.components.sysfunc.timetable.Session;
import team.e.components.sysfunc.timetable.SessionFunctions;
import team.e.components.sysfunc.timetable.TimetableSlot;
import team.e.components.sysfunc.timetable.TimetableSlotFunctions;

public class Functional1to7 {
	private static LecturerAccessFactory lecturerAccessFactory = new LecturerAccessFactory();
	private static IDatabase mockDatabase = new Database();
	private static FunctionRepository funcRepo = new FunctionRepository();
	private static IMyCampus myCampus = new MyCampusStub(mockDatabase, funcRepo);
	private static LecturerAccess lecturerAccess = lecturerAccessFactory.newInstance();
	private static AdminAccess adminAccess =  new AdminAccessFactory().newInstance();
	private static StudentAccess studentAccess =  new StudentAccessFactory().newInstance();
	private static Session aSession;
	private static MyCampusFunctions myCampusFunctions = new MyCampusFunctions(myCampus);
	private static CourseFunctions courseFunctions = new CourseFunctions(mockDatabase);
	private static SessionFunctions sessionFunctions = new SessionFunctions(mockDatabase);
	private static TimetableSlotFunctions timetableSlotFunctions = new TimetableSlotFunctions(mockDatabase);

	// Test access to an empty list of courses. Test case seems to fail due to a
	// null pointer exception.

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

		aSession = new Session("PSD3-L1", "Lecture", 1, 50, 10, true);
	}

	@Test
	public void functionalRequirement1() {
		boolean success = false;
		lecturerAccess.importMyCampusCourse("PSD3");
		for (Course course : lecturerAccess.getMyCampusCourses()) {
			if (course.getName().equals("PSD3"))
				success = true;
		}
		assertTrue(success);
	}

	@Test
	public void functionalRequirement2() {
		boolean success = false;
		
		lecturerAccess.addSessionToCourse(aSession, "PSD3");
		
		//Verifies there is a result on database for iterating
		List<Session> courseSessions = (List<Session>) courseFunctions.getCourseSessions("PSD3");
		assertTrue(courseSessions != null);
		
		for (Session sessionOfCourse : courseSessions) {
			if (sessionOfCourse.getId().equals(aSession.getId()))
				success = true;
		}
		assertTrue(success);
	}

	@Test
	/**
	 * Checks if all the sessions in the DB have a
	 */
	public void functionalRequirement4() {
		boolean success = false;
		int frequency = 2;

		lecturerAccess.importMyCampusCourse("PSD3");
		lecturerAccess.addSessionToCourse(aSession, "PSD3");

		assertTrue(lecturerAccess.setSessionFrequency("PSD3-L1", frequency));
		
		success = sessionFunctions.getSession(aSession.getId()).getFrequency() == frequency;
		
		assertTrue(success);
	}

	@Test
	public void functionalRequirement8() {
		lecturerAccess.addSessionToCourse(aSession, "PSD3");
		
		mockDatabase.add(new TimetableSlot("TS1", new Date(), null, ""), TimetableSlot.class);
		
		assertTrue(adminAccess.assignRoomToSlot("BO 503", "TS1"));
		
		//Verifies there is a result on database for iterating
		TimetableSlot t = (TimetableSlot) mockDatabase.get("TS1", TimetableSlot.class);
		assertTrue(t != null);
		assertTrue(t.getLocation().equals("BO 503"));
	}

	@Test
	public void functionalRequirement11() {
		boolean success = false;
		
		mockDatabase.add(new TimetableSlot("TS1", new Date(), null, ""), TimetableSlot.class);
		
		success = studentAccess.bookTimetableSlot("TS1");
		
		timetableSlotFunctions.findTimeTableSlotForStudent(studentAccess.getUsername());
		
		assertTrue(success);
	}

	@Test
	public void functionalRequirement12() {
		boolean success = false;
		lecturerAccess.setFuncRepo(funcRepo);
		lecturerAccess.addSessionToCourse(new Session("PSD3-L1", "Lecture", 1,
				50, 10, true), "PSD3");
		// System.out.println(mockDatabase.getAll(Course.class));
		// lecacc.importMyCampusCourse("PSD3");
		for (Course course : lecturerAccess.getMyCampusCourses()) {
			if (course.getName().equals("PSD3"))
				success = true;
		}
		assertTrue(success);
	}

	@Test
	public void functionalRequirement13() {
		boolean success = false;
		lecturerAccess.importMyCampusCourse("PSD3");
		for (Course course : lecturerAccess.getMyCampusCourses()) {
			if (course.getName().equals("PSD3"))
				success = true;
		}
		assertTrue(success);
	}
	
	@Test
	public void functionalRequirement14() {
		boolean success = false;
		lecturerAccess.setFuncRepo(funcRepo);
		lecturerAccess.addSessionToCourse(new Session("PSD3-L1", "Lecture", 1,
				50, 10, true), "PSD3");
		// System.out.println(mockDatabase.getAll(Course.class));
		// lecacc.importMyCampusCourse("PSD3");
		for (Course course : lecturerAccess.getMyCampusCourses()) {
			if (course.getName().equals("PSD3"))
				success = true;
		}
		assertTrue(success);
	}

}