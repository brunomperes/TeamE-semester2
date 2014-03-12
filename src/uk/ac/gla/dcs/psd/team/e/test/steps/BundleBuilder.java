package uk.ac.gla.dcs.psd.team.e.test.steps;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.test.ConfiguredFrameworkFactory;
import uk.ac.gla.dcs.psd.team.e.test.behavioural.FunctionalTests;

public class BundleBuilder {
	final static public String ADMINISTRATOR_BUNDLE = "file:administrator.jar";
	final static public String DB_FAKE_BUNDLE = "file:db-fake.jar";
	final static public String LECTURER_BUNDLE = "file:lecturer.jar";
	final static public String MYCAMPUS_STUB_BUNDLE = "file:mycampus-stub.jar";
	final static public String STUDENT_BUNDLE = "file:student.jar";
	final static public String SYSFUNC_REPOSITORY_IMPL_BUNDLE = "file:sysfunc-repository-impl.jar";
	final static public String SYSFUNC_REPOSITORY_BUNDLE = "file:sysfunc-repository.jar"; 
	final static public String SYSFUNC_TIMETABLE_BUNDLE = "file:sysfunc-timetable.jar";
	final static public String SYSFUNC_MYCAMPUS_BUNDLE = "file:sysfunc-mycampus.jar";
	final static public String USER_BUNDLE = "file:users.jar";
	
	private BundleContext bundleContext;
	private Framework framework;
	
	public BundleBuilder() throws BundleException{
		// Clean up any resources left behind by failed tests
		recursiveDelete(new File("felix-cache"));
		
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
			+ "only contain  " + 1 + " bundles.";
		
		assertEquals(
				message,
				(Integer) 1,
				actualNumberOfBundles);
	}
	
	public void installAndStart(String bundleFile) throws Exception{
		Bundle installedBundle = 
			bundleContext.installBundle(
					bundleFile);
		
		installedBundle.start();		
	}
	
	public void tearDown() throws Exception{			
		framework.stop();		
		framework.waitForStop(0);
		
		//Clean up resources that might have been created during testing.
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
