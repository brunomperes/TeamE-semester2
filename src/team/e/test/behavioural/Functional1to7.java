package team.e.test.behavioural;

import static org.junit.Assert.*;

import org.junit.Test;

import team.e.components.db.IDatabase;
import team.e.components.db.mock.Database;
import team.e.components.lecturer.LecturerAccess;
import team.e.components.lecturer.LecturerAccessFactory;
import team.e.components.mycampus.IMyCampus;
import team.e.components.mycampus.stub.MyCampusStub;
import team.e.components.sysfunc.repository.impl.FunctionRepository;
import team.e.components.sysfunc.timetable.Course;

public class Functional1to7{
	private LecturerAccessFactory lecturerAccessFactory = new LecturerAccessFactory();
	private IDatabase mockDatabase = new Database();
	FunctionRepository funcrepo = new FunctionRepository();
	private IMyCampus mycampus = new MyCampusStub(mockDatabase, funcrepo);
	private LecturerAccess lecacc = lecturerAccessFactory.newInstance();
	//Test access to an empty list of courses. Test case seems to fail due to a null pointer exception.
	@Test
	public void testEmptyLecturerAccess() {
		boolean success = false;
		lecacc.setFuncRepo(funcrepo);
		lecacc.importMyCampusCourse("PSD3");
		//System.out.println(mockDatabase.getAll(Course.class));
		//lecacc.importMyCampusCourse("PSD3");
		for(Course course: lecacc.getMyCampusCourses()){
			if(course.getName().equals("PSD3")) success=true;
		}
		assertTrue(success);
	}
}
