package tiku.user;

import java.util.List;

import org.json.JSONArray;

import af.web.jsx.AfJsxCreator;
import tiku.MyC3P0Factory;
import tiku.db.Course;

public class CourseListJSX extends AfJsxCreator
{

	@Override
	public Object execute() throws Exception
	{
		String s1 = "select * from course ORDER BY id ASC";
		List<Course> course_list = MyC3P0Factory.executeQuery(s1, Course.class);
		return new JSONArray(course_list);
	}

}
