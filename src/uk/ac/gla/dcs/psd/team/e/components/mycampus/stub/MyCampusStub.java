package uk.ac.gla.dcs.psd.team.e.components.mycampus.stub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.components.mycampus.IMyCampus;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.mycampus.MyCampusFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Course;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.CourseFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.CourseHasSession;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.IIdentifiable;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.AuthResult;

public class MyCampusStub implements IMyCampus{
	
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Session> sessions  = new ArrayList<Session>();
	private CourseFunctions courseFunctions;
	private IDatabase db;
	
	public MyCampusStub(IDatabase db, IFunctionRepository funcRepo){
		this.db=db;
		courseFunctions=(CourseFunctions) funcRepo.getFunction(Course.class);
		
		courses.add(new Course("CS1", "PSD3", "13TS"));
		courses.add(new Course("CS2", "NS3", "22TJ"));
		//TODO these side effects should be removed.
		db.addAll(courses, Course.class);
		sessions.add(new Session("PSD3-1", "PSD3 Lab", 1, 50, 10, true));
		sessions.add(new Session("PSD3-2", "PSD3 Tutorial", 2, 50, 10, false));
		sessions.add(new Session("NSD3-1", "NS3 Lab", 1, 50, 12, true));
		//TODO these side effects should be removed.
		db.addAll(sessions, Session.class);
		
		CourseHasSession courseSession=new CourseHasSession();
		ArrayList<CourseHasSession> courseHasSessionArray=new ArrayList<CourseHasSession>();
		courseSession.setCourseId("CS1");
		courseSession.setSessionId("PSD3-1");
		courseSession.setId("1");
		courseHasSessionArray.add(courseSession);
		courseSession.setCourseId("CS1");
		courseSession.setSessionId("PSD3-2");
		courseSession.setId("2");
		courseHasSessionArray.add(courseSession);
		courseSession.setCourseId("CS2");
		courseSession.setSessionId("NS3-1");
		courseSession.setId("3");
		courseHasSessionArray.add(courseSession);
		//TODO these side effects should be removed.
		db.addAll(courseHasSessionArray, CourseHasSession.class);
		MyCampusFunctions funkyObject = new MyCampusFunctions(this);
		funcRepo.registerFunction(funkyObject);
	}
	
	public Collection<Course> getCourseList(){
//		List<IIdentifiable> courseList=db.getAll(Course.class);
//		ArrayList<Course> courseArray=new ArrayList<Course>();
//		//System.out.println(courseList.get(0));
//		for(int i=0;i<courseList.size();i++){
//			//System.out.println("nleeeh");
//			//.out.println(courseList.get(i).getClass());
//			courseArray.add((Course)(courseList.get(i)));
//		}
		return courses;
	}
	
	//Returns NULL if no such course is found with the supplied ID
	public Collection<Session> getCourseSessions(String courseID){
		return courseFunctions.getAllSessions();
	}

	@Override
	public AuthResult auth(String username, String password) {
		AuthResult result=null;
		if(username=="admin"||username=="student"||username=="lecturer"){
			result=new AuthResult();
			result.success=true;
			result.userType=username;
		}
		return result;
	}

	
}
