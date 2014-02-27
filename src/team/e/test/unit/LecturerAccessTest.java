package team.e.test.unit;

import static org.junit.Assert.*;
import org.junit.Test;

import team.e.components.lecturer.LecturerAccess;

public class LecturerAccessTest {
	
	@Test
	public void testEmptyLecturerAccess() {
		LecturerAccess lecacc = new LecturerAccess();
		lecacc.getMyCampusCourses();
	}

}
