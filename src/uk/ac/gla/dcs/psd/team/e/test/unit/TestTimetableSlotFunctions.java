package uk.ac.gla.dcs.psd.team.e.test.unit;
import org.junit.BeforeClass;

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
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.CourseFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.SessionFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.TimetableSlotFunctions;

public class TestTimetableSlotFunctions
{
	private static LecturerAccessFactory lecturerAccessFactory = new LecturerAccessFactory();
	private static StudentAccessFactory studentAccessFactory = new StudentAccessFactory();
	private static IDatabase mockDatabase = new Database();
	private static FunctionRepository funcRepo = new FunctionRepository();
	private static IMyCampus myCampus = new MyCampusStub(funcRepo);
	private static LecturerAccess lecturerAccess = lecturerAccessFactory.newInstance();
	private static AdminAccess adminAccess =  new AdminAccessFactory().newInstance();
	private static StudentAccess studentAccess =  new StudentAccessFactory().newInstance();
	private static Session exampleSession;
	private static Session exampleSession2;
	private static Session exampleSession3;
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

		exampleSession = new Session("PSD3-L1", "Lecture", 1, 50, 10, true);
		exampleSession2 = new Session("PSD3-L2", "Lecture", 1, 50, 10, true);
		exampleSession3 = new Session("PSD3-L3", "Lecture", 1, 50, 10, false);
		
		mockDatabase.add(exampleSession, Session.class);
		
	}
	/*
	 * 
	 * These tests become feasible once we create some new instances in the database
	 *   
	 * 
	@Test
	public void addExistingStudentToExistingSlot()
	{
		String studentID = "ID"; //existing student ID in the database
		String slotID = "ID"; //existing slot ID in the database;
		boolean expected = "TRUE";
		boolean result = addStudentToSlot(studentID, slotID);
		assertEquals("Assign existing student to existing slot", expected, result);
	}
	
	@Test
	public void addNonExistingStudentToExistingSlot()
	{
		String studentID = "ID"; //student ID not in the database
		String slotID = "ID"; //existing slot ID in the database;
		boolean expected = "FALSE";
		boolean result = addStudentToSlot(studentID, slotID);
		assertEquals("Assign a student not in the database to existing slot", expected, result);
	}
	
	@Test
	public void addExistingStudentToNonExistingSlot()
	{
		String studentID = "ID"; //existing student ID in the database
		String slotID = "ID"; //slot ID not in the database;
		boolean expected = "FALSE";
		boolean result = addStudentToSlot(studentID, slotID);
		assertEquals("Assign existing student to a slot not in the database", expected, result);
	}
	
	@Test
	public void addNonExistingStudentToNonExistingSlot()
	{
		String studentID = "ID"; //student ID not in the database
		String slotID = "ID"; //slot ID not in the database;
		boolean expected = "FALSE";
		boolean result = addStudentToSlot(studentID, slotID);
		assertEquals("Assign a student not in the database to a slot not in the database", expected, result);
	}
	
	@Test
	public void addExistingRoomToExistingSlot()
	{
		String roomID = "ID"; //existing room ID in the database
		String slotID = "ID"; //existing slot ID in the database;
		boolean expected = "TRUE";
		boolean result = assignRooomToSlot(roomID, slotID);
		assertEquals("Assign existing room to existing slot", expected, result);
	}
	
	@Test
	public void addNonExistingRoomToExistingSlot()
	{
		String roomID = "ID"; //room ID not in the database
		String slotID = "ID"; //existing slot ID in the database;
		boolean expected = "FALSE";
		boolean result = assignRooomToSlot(roomID, slotID);
		assertEquals("Assign room not in the database to existing slot", expected, result);
	}
	
	@Test
	public void addExistingRoomToNonExistingSlot()
	{
		String roomID = "ID"; //existing room ID in the database
		String slotID = "ID"; //slot ID not in the database;
		boolean expected = "FALSE";
		boolean result = assignRooomToSlot(roomID, slotID);
		assertEquals("Assign existing room to slot not in the database", expected, result);
	}
	
	@Test
	public void addNonExistingRoomToNonExistingSlot()
	{
		String roomID = "ID"; //room ID not in the database
		String slotID = "ID"; //slot ID not in the database;
		boolean expected = "FALSE";
		boolean result = assignRooomToSlot(roomID, slotID);
		assertEquals("Assign room not in the database to slot not in the database", expected, result);
	}
	*/
}	