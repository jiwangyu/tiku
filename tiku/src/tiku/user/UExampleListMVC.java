package tiku.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import af.sql.AfSqlWhere;
import af.web.AfHttpReqParams;
import af.web.AfWebException;
import af.web.mvc.AfSimpleMVC;
import tiku.MyC3P0Factory;
import tiku.db.Chapter;
import tiku.db.Course;
import tiku.db.Example;

@WebServlet("/list.do")
public class UExampleListMVC extends AfSimpleMVC
{

	@Override
	protected String execute(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Object> model)
			throws Exception
	{
		int courseId = Integer.valueOf(req.getParameter("course"));
		int chapterNum = Integer.valueOf(req.getParameter("chapter"));
		AfHttpReqParams reqParams = new AfHttpReqParams(req);
		int start = reqParams.getInt("start", 0);
		if (courseId == 0)
			throw new AfWebException(404, "无此课程,course=" + courseId);
		if (true)
		{
			// 查询课程信息,此处可以做性能优化
			String s1 = "select * from course where id=" + courseId;
			Course row = (Course) MyC3P0Factory.get(s1, Course.class);
			model.put("course", row);
			if (row == null)
				throw new AfWebException(404, "无此课程, course=" + courseId);
		}
		if (true)
		{
			// 查询章节信息,此处可以做性能优化
			AfSqlWhere where = new AfSqlWhere();
			where.add2("course", courseId).add2("number", chapterNum);
			String s1 = "select * from chapter " + where;
			Chapter row = (Chapter) MyC3P0Factory.get(s1, Chapter.class);
			model.put("chapter", row);
			if (row == null)
				throw new AfWebException(404, "无此章节, course=" + courseId +"course="+chapterNum);
		}

		if (true)
		{
			AfSqlWhere where = new AfSqlWhere();
			where.add2("course", courseId);
			where.add2("chapter", chapterNum);
			if(start > 0) where.add("id > " + start);
			int limit = 5;
			String sql = "select * from example" + where + " order by id ASC LIMIT 5";
			String sql2 = "select * from example" + where + " order by id ASC LIMIT 6";
			System.out.println(sql);
			// List<Example> rows = MyC3P0Factory.executeQuery(sql,
			// Example.class);
			// model.put("example_list", rows);
			List<Map> rows = MyC3P0Factory.executeQuery2Map(sql);
			List<Map> rows2 = MyC3P0Factory.executeQuery2Map(sql2);
			for (Map row : rows)
			{
				String content = (String) row.get("content");
				String contentHtml = text2Html(content);
				row.put("contentHtml", contentHtml);

				String answer = (String) row.get("answer");
				String answerHtml = text2Html(answer);
				row.put("answerHtml", answerHtml);
			}
			int next = 0;
			if(rows2.size() > limit)
			{	
				Map lastRow = (Map)rows.get(rows.size()-1);
				next = (Integer)lastRow.get("id");
			}
			model.put("start", start); // 本页的开始条件 
			model.put("next", next); // 用于翻到下一页，但是如果本次行数<limit，说明已经到底了
			model.put("example_list", rows);
			model.put("courseId", courseId); 
			model.put("chapterId", chapterNum);
			
		}

		return "/example_list.html";
	}

	// 把纯文本转成HTML
	private String text2Html(String text)
	{
		// 换行 <br>
		// 空格 &nbsp;
		// 制表位 &nbsp;&nbsp;&nbsp;&nbsp;
		text = text.replace("\n", "<br>").replace(" ", "&nbsp;").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		return "<p>" + text + "</p>";
	}

}
