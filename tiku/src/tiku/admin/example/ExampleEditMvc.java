package tiku.admin.example;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import af.web.mvc.AfSimpleMVC;
import tiku.MyC3P0Factory;
import tiku.db.Chapter;
import tiku.db.Course;

@WebServlet("/example_edit.sudo")
public class ExampleEditMvc extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		int courseId = Integer.valueOf(req.getParameter("course"));
		String s1 = "select * from course where id=" + courseId;
		Course course = (Course) MyC3P0Factory.get(s1, Course.class);

		if (course == null)
			throw new Exception("无此课程" + courseId);

		String s2 = "select * from chapter where course=" + courseId + " order by course ASC, number ASC";
		List<Chapter> chapter_list = MyC3P0Factory.executeQuery(s2, Chapter.class);

		model.put("course", course);
		model.put("chapter_list", chapter_list);

		return "/example_edit.jhtml";
	}

}
