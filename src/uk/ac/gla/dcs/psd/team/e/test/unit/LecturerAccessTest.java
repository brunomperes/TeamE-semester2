package uk.ac.gla.dcs.psd.team.e.test.unit;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.components.db.mock.Database;
import uk.ac.gla.dcs.psd.team.e.components.lecturer.LecturerAccess;
import uk.ac.gla.dcs.psd.team.e.components.lecturer.LecturerAccessFactory;
import uk.ac.gla.dcs.psd.team.e.components.mycampus.IMyCampus;
import uk.ac.gla.dcs.psd.team.e.components.mycampus.stub.MyCampusStub;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.impl.FunctionRepository;

public class LecturerAccessTest {
	
	private static LecturerAccessFactory lecturerAccessFactory = new LecturerAccessFactory();
	private static IDatabase mockDatabase = new Database();
	private static FunctionRepository funcRepo = new FunctionRepository();
	private static IMyCampus myCampus = new MyCampusStub(mockDatabase, funcRepo);
	private static LecturerAccess lecturerAccess = lecturerAccessFactory.newInstance();
	
	@BeforeClass
	public static void setup() {
		lecturerAccess.setFuncRepo(funcRepo);
		lecturerAccess.setUsername("Tim");
	}
	
	@Test
	public void testEmptyLecturerAccess() {
		lecturerAccess.getMyCampusCourses();
	}

}
