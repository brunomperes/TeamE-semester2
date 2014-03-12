package uk.ac.gla.dcs.psd.team.e.test.behavioural;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;

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
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.*;
import uk.ac.gla.dcs.psd.team.e.test.ConfiguredFrameworkFactory;
import uk.ac.gla.dcs.psd.team.e.test.steps.BundleBuilder;

public class FunctionalTests {
	private static LecturerAccessFactory lecturerAccessFactory = new LecturerAccessFactory();
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
	
	private static BundleContext bundleContext;
	private static Framework framework;
	
public static BundleContext buildContext() throws Exception {
		
		// Clean up any resources left behind by failed tests
		FunctionalTests.recursiveDelete(new File("felix-cache"));
		
		String extraPackages =
				"uk.ac.gla.dcs.psd.team.e.components.db,"
				+ "uk.ac.gla.dcs.psd.team.e.components.mycampus,"
				+ "uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository";
		
		framework = 
			ConfiguredFrameworkFactory.createFelixFramework(
				extraPackages);

		bundleContext = framework.getBundleContext();
		
		//...
		
		Integer actualNumberOfBundles = 
			bundleContext.getBundles().length;
		
		String message = 
			"If cleanly initialised, the framework should "
			+ "only contain  " + 11 + " bundles.";
		
		return bundleContext;
		
	}

	public static void installAndStart(String bundleFile) throws Exception{
		
		Bundle installedBundle = 
			bundleContext.installBundle(
					bundleFile);
		
		installedBundle.start();
				
	}

	private static void recursiveDelete(File file){
		if (!file.exists()) return;
		if (file.isDirectory()){
			for (File subFile :file.listFiles())
				recursiveDelete(subFile);
		}
		file.delete();
	}

	@BeforeClass
	public static void setup() throws Exception {
		
		bundleContext = buildContext();
		
		installAndStart(BundleBuilder.DB_FAKE_BUNDLE);
		installAndStart(BundleBuilder.SYSFUNC_REPOSITORY_BUNDLE);
		installAndStart(BundleBuilder.SYSFUNC_TIMETABLE_BUNDLE);
		installAndStart(BundleBuilder.SYSFUNC_REPOSITORY_IMPL_BUNDLE);
		installAndStart(BundleBuilder.USER_BUNDLE);
		installAndStart(BundleBuilder.MYCAMPUS_STUB_BUNDLE);
		installAndStart(BundleBuilder.SYSFUNC_MYCAMPUS_BUNDLE);
		installAndStart(BundleBuilder.ADMINISTRATOR_BUNDLE);
		installAndStart(BundleBuilder.LECTURER_BUNDLE);	
		installAndStart(BundleBuilder.STUDENT_BUNDLE);
		
		
		ServiceReference<IDatabase> dbReference = bundleContext
				.getServiceReference(IDatabase.class);
		
		IDatabase adminAccess2 = bundleContext.getService(dbReference);
	
		
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

		exampleSession = new Session("PSD3-L1", "Lecture", 1, 50, 10, true, "PSD3");
		exampleSession2 = new Session("PSD3-L2", "Lecture", 1, 50, 10, true, "PSD3");
		exampleSession3 = new Session("PSD3-L3", "Lecture", 1, 50, 10, false, "PSD3");
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
		
		lecturerAccess.addSessionToCourse(exampleSession, "PSD3");
		
		//Verifies there is a result on database for iterating
		List<Session> courseSessions = (List<Session>) courseFunctions.getCourseSessions("PSD3");
		assertTrue(courseSessions != null);
		
		for (Session sessionOfCourse : courseSessions) {
			if (sessionOfCourse.getId().equals(exampleSession.getId()))
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
		lecturerAccess.addSessionToCourse(exampleSession, "PSD3");

		assertTrue(lecturerAccess.setSessionFrequency("PSD3-L1", frequency));
		
		success = sessionFunctions.getSession(exampleSession.getId()).getFrequency() == frequency;
		
		assertTrue(success);
	}

	@Test
	public void functionalRequirement8() {
		mockDatabase.add(new Course("PSD3", "Tim"), Course.class);
		exampleSession=new Session("PSD3id", "PDS3 Lecture", 1, 60, 12, true, "PSD3");
		lecturerAccess.addSessionToCourse(exampleSession, "PSD3");
		
		mockDatabase.add(new TimetableSlot("TS1", new Date(), "BO720", "",exampleSession.getCourseID(),1), TimetableSlot.class);
		
		assertTrue(adminAccess.assignRoomToSlot("BO 503", "TS1"));
		
		//Verifies there is a result on database for iterating
		TimetableSlot t = (TimetableSlot) mockDatabase.get("TS1", TimetableSlot.class);
		assertTrue(t != null);
		assertTrue(t.getLocation().equals("BO 503"));
	}

	@Test
	public void functionalRequirement11() {
		boolean success = false;
		
		mockDatabase.add(new Course("PSD3","TS"), Course.class);
		mockDatabase.add(new Session("PSD301","PSD3 Lecture",1,60,12,true,"PSD3"), Course.class);
		mockDatabase.add(new TimetableSlot("TS1", new Date(), "BO720", "","PSD3", 1), TimetableSlot.class);
		
		success = studentAccess.bookTimetableSlot("TS1");
		
		timetableSlotFunctions.findTimeTableSlotForStudent(studentAccess.getUsername());
		
		assertTrue(success);
	}

	@Test
	public void functionalRequirement12() {
		
		mockDatabase.add(new Course("PSD3", "Tim"), Course.class);
		lecturerAccess.addSessionToCourse(exampleSession, "PSD3");
		lecturerAccess.addSessionToCourse(exampleSession2, "PSD3");
		lecturerAccess.addSessionToCourse(exampleSession3, "PSD3");
		
		List<Session> compulsoryUnbookedSessionsBEFORE = (List<Session>) studentAccess.getCompulsoryUnbookedSessions();
		
		mockDatabase.add(new StudentHasCourse("PSD3-Adam", "PSD3", studentAccess.getUsername()) , StudentHasCourse.class);
		mockDatabase.add(new TimetableSlot("SLOT1", new Date(), "Hunterian Art Galley", "Jeremy","PSD3",1) , TimetableSlot.class);
		studentAccess.bookTimetableSlot("SLOT1");
		
		List<Session> compulsoryUnbookedSessionsAFTER = (List<Session>) studentAccess.getCompulsoryUnbookedSessions();
		
		assertTrue(compulsoryUnbookedSessionsAFTER.size() == compulsoryUnbookedSessionsBEFORE.size());
	}
	
	@Test
	public void functionalRequirement14() {
			
		mockDatabase.add(new Course("PSD3", "Tim"), Course.class);
		lecturerAccess.addSessionToCourse(exampleSession, "PSD3");
		
		mockDatabase.add(new TimetableSlot("SLOT1", new Date(), "Hunterian Art Galley", "Jeremy","PSD3",1) , TimetableSlot.class);
		mockDatabase.add(new TimetableSlot("SLOT2", new Date(), "Hunterian Art Galley", "Jeremy","PSD3",1) , TimetableSlot.class);
		
		List<TimetableSlot> queryResult = lecturerAccess.getTimetableSlotsForSession(exampleSession.getId());
		
		assertTrue(queryResult != null);
		assertTrue(queryResult.size() == 2);
	}

}