package uk.ac.gla.dcs.psd.team.e.test.steps;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.test.ConfiguredFrameworkFactory;

public class BundleBuilder {
	private Framework framework;
	private BundleContext bundleContext;
	//...
	
	final static private Integer EXPECTED_NUMBER_OF_BUNDLES = 7;
	
	final static public String ADMINISTRATOR_BUNDLE = "file:administrator.jar";
	final static public String DB_FAKE_BUNDLE = "file:db-mock.jar";
	final static public String DB_BUNDLE = "file:db.jar";
	final static public String LECTURER_BUNDLE = "file:lecturer.jar";
	final static public String MYCAMPUS_STUB_BUNDLE = "file:mycampus-stub.jar";
	final static public String MYCAMPUS_BUNDLE = "file:mycampus.jar";
	final static public String STUDENT_BUNDLE = "file:student.jar";
	final static public String SYSFUNC_MYCAMPUS_BUNDLE = "file:mycampus-bundle.jar";
	final static public String SYSFUNC_REPOSITORY_IMPL_BUNDLE = "file:sysfunc-repository-impl.jar";
	final static public String SYSFUNC_REPOSITORY_BUNDLE = "file:sysfunc-bundle.jar";
	final static public String SYSFUNC_TIMETABLE_BUNDLE = "file:sysfunc-timetable.jar";
	
	
	private Bundle repositoryImpl;
	private Bundle alarmImpl;
	//..
	
	private IDatabase db;
//	private TemperatureReport temperatureReport;
	
	private String expectedSensorID;
	private Date dateTaken;
	
//	private AlarmDashboard alarmDashboard;
	
//	private AlertCatcher alertCatcher;
	private Bundle monitorImpl;
	
	public BundleBuilder(){
		
	}
		
	public BundleContext buildContext() throws Exception {
		
		// Clean up any resources left behind by failed tests
		this.recursiveDelete(new File("felix-cache"));
		this.recursiveDelete(new File("data/sensordb"));
		
		
		expectedSensorID = "Temp02";
		
		String extraPackages =
			  "uk.ac.gla.dcs.psd.team.e.components.administrator,"
			+ "uk.ac.gla.dcs.psd.team.e.components.db,"
			+ "uk.ac.gla.dcs.psd.team.e.components.lecturer,"
			+ "uk.ac.gla.dcs.psd.team.e.components.mycampus,"
			+ "uk.ac.gla.dcs.psd.team.e.components.student,"
			+ "uk.ac.gla.dcs.psd.team.e.components.sysfunc,"
			+ "uk.ac.gla.dcs.psd.team.e.components.users";
		
		framework = 
			ConfiguredFrameworkFactory.createFelixFramework(
				extraPackages);

		bundleContext = framework.getBundleContext();
		
		//...
		
		Integer actualNumberOfBundles = 
			bundleContext.getBundles().length;
		
		String message = 
			"If cleanly initialised, the framework should "
			+ "only contain  " + 1 + " bundles.";
		
		assertEquals(
			message,
			(Integer) 1,
			actualNumberOfBundles);
		
		return bundleContext;
		
	}
	
	public Bundle installAndStart(String bundleFile) throws Exception{
		return null;
//		Bundle installedBundle = 
//			bundleContext.installBundle(
//					bundleFile);
//		
//		installedBundle.start();
//		
//		return installedBundle;
		//..
//		ServiceReference<TemperatureQuery>
//			temperatureQueryReference = 
//				bundleContext.getServiceReference(
//					TemperatureQuery.class);
//		
//		ServiceReference<TemperatureReport> 
//			temperatureReportReference = 
//				bundleContext.getServiceReference(
//					TemperatureReport.class);
//		
//		temperatureQuery = 
//			bundleContext.getService(temperatureQueryReference);
//		
//		temperatureReport = 
//			bundleContext.getService(temperatureReportReference);
	}
//	
//	@Given("an alarm component")
//	public void givenAnAlarmComponent() throws Exception {
//		
//		alarmImpl = 
//			bundleContext.installBundle(
//				"file:alarm-impl.jar");
//
//		alarmImpl.start();
//		
//		ServiceReference<AlarmDashboard> alarmDashboardReference = 
//			bundleContext.getServiceReference(AlarmDashboard.class);
//		
//		alarmDashboard = 
//			bundleContext.getService(alarmDashboardReference);
//	}
//	
//	@Given("a faulty temperature monitor component")
//	public void givenAFaultyTemperatureMonitorComponent() throws Exception {
//		monitorImpl = 
//			bundleContext.installBundle("file:monitor.jar");
//		
//		monitorImpl.start();
//	}
//	
//	@When("a temperature of $temperature is reported")
//	public void aTemperatureOfIsRecorded(Double temperature){
//
//		Temperature expectedTemperature = 
//			new Temperature(TemperatureMetric.CELSIUS,temperature);
//		
//		dateTaken = new Date();
//		
//		Reading<Temperature> expectedReading = 
//			new Reading<Temperature>(
//				dateTaken, expectedSensorID, expectedTemperature);
//
//		temperatureReport.recordTemperatureReading(
//			expectedReading);
//
//	}
//	
//	@Then("the most recent recording is $temperature")
//	public void theMostRecentRecordingIs(Double temperature){
//		
//		Temperature expectedTemperature = 
//			new Temperature(TemperatureMetric.CELSIUS,temperature);
//				
//		Reading<Temperature> expectedReading = 
//			new Reading<Temperature>(
//				dateTaken,expectedSensorID,expectedTemperature);
//
//		
//		List<Reading<Temperature>> readings = 
//			temperatureQuery.
//				getMostRecentTemperatureReadings(expectedSensorID, 2);
//				
//		Reading<Temperature> actualReading = readings.get(0);
//		assertEquals(expectedReading, actualReading);		
//		
//	}
//	
//	@When("the alarm is configured to issue an alert if $window readings"
//		+ " lower than $lowerCelsius or higher than $higherCelsius are received")
//	public void whenTheAlarmIsConfiguredToIssueAnAlertIfReadingsLowerThanOrHigherThanAreReceived(
//		Integer window, Double lowerCelsius, Double higherCelsius) {
//		
//		Temperature low  = 
//			new Temperature(TemperatureMetric.CELSIUS, 15.5);
//		Temperature high =
//			new Temperature(TemperatureMetric.CELSIUS, 16.5);
//		
//		TemperatureRange range =
//			new TemperatureRange(low, high);
//		
//		AlertCondition condition = 
//			new TemperatureRangeAlertCondition(range, expectedSensorID, 5);
//		
//		alarmDashboard.registerAlertCondition(condition);
//	}
//	
//	@When("an alertcatcher is added to the alarm")
//	public void whenAnAlertcatcherIsAddedToTheAlarm() {
//		alertCatcher = new AlertCatcher();
//		
//		alarmDashboard.registerAlertListener(alertCatcher);
//
//	}
//		
//	@When("$numberOfReadings temperature readings of $temperature are reported")
//	public void whenNumberOfReadingsTemperatureReadingsOfTemperatureAreReported(
//		Integer numberOfReadings, Double temperature) {
//	  Integer count = 0;
//	  
//	  while (count < numberOfReadings){
//		  this.aTemperatureOfIsRecorded(temperature);
//		  count ++;
//	  }
//		  
//	}
//	
//	@Then("an alert is received")
//	public void thenAnAlertIsReceived() throws InterruptedException {
//	  Alert alert = alertCatcher.retrieveOldestAlert();
//	  assertNotNull(alert);
//	}
	
	public void tearDown () throws Exception{
						
		framework.stop();		
		framework.waitForStop(0);
		
		//Clean up resources that might have been created during testing.

		recursiveDelete(new File("data/sensordb"));
		
		recursiveDelete(new File("felix-cache"));
	}
	
	private void recursiveDelete(File file){
		if (!file.exists()) return;
		if (file.isDirectory()){
			for (File subFile :file.listFiles())
				recursiveDelete(subFile);
		}
		file.delete();
	}
}
