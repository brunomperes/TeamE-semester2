package team.e.test.unit;

import static org.junit.Assert.*;
import org.junit.Test;

import team.e.components.lecturer.LecturerAccess;

public class LecturerAccessTest {
	
	
	//Test access to an empty list of courses. Test case seems to fail due to a null pointer exception.
	@Test
	public void testEmptyLecturerAccess() {
		LecturerAccess lecacc = new LecturerAccess();
		lecacc.getMyCampusCourses();
	}

}
