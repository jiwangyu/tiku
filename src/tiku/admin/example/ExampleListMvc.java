package tiku.admin.example;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import af.web.mvc.AfSimpleMVC;
import tiku.MyC3P0Factory;
import tiku.db.Chapter;
import tiku.db.Course;

@WebServlet("/example.sudo")
public class ExampleListMvc extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		String s1 = "select * from course order by id ASC";
		List<Course> course_list = MyC3P0Factory.executeQuery(s1, Course.class);
		model.put("course_listJ", new JSONArray(course_list));

		String s2 = "select * from chapter order by course ASC, number ASC";
		List<Chapter> chapter_list = MyC3P0Factory.executeQuery(s2, Chapter.class);
		model.put("chapter_listJ", new JSONArray(chapter_list));

		return "/example.jhtml";
	}

}
