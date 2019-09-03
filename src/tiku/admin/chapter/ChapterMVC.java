package tiku.admin.chapter;

import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import af.web.mvc.AfSimpleMVC;
import tiku.MyC3P0Factory;
import tiku.db.Chapter;
import tiku.db.Course;

@WebServlet("/chapter.sudo")
public class ChapterMVC extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		int id = Integer.valueOf(req.getParameter("id"));
		String sql = "select * from course where id=" + id;
		Course row = (Course) MyC3P0Factory.get(sql, Course.class);

		model.put("course", row);
		model.put("courseJ", new JSONObject(row));

		if (true)
		{
			String s = "select * from chapter where course=" + id + " ORDER BY number ASC";
			// String s = "select * from chapter where course=" + id + " ORDER
			// BY number ASC";
			List chapters = MyC3P0Factory.executeQuery(s, Chapter.class);
			model.put("chaptersJ", new JSONArray(chapters));
		}
		return "/chapter.jhtml";
	}

}
