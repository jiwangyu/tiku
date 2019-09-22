package tiku.admin.course;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import af.web.mvc.AfSimpleMVC;
import tiku.MyC3P0Factory;
import tiku.db.Course;

@WebServlet("/course.sudo")
public class CourseMVC extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		String sql = "select * from course order by id ASC";
		List<Course> rows = MyC3P0Factory.executeQuery(sql, Course.class);
		model.put("course_list", rows);
		return "/course.jhtml";
	}

}
