package tiku.admin.example;

import org.json.JSONArray;
import org.json.JSONObject;

import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import tiku.MyC3P0Factory;

public class ExampleListApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		int course = jreq.getInt("course"); // 所属课程ID
		int chapter = 0;
		if (jreq.has("chapter"))
			chapter = jreq.getInt("chapter"); // 章节

		AfSqlWhere w = new AfSqlWhere();
		if (course > 0)
			w.add2("a.course", course);
		if (chapter > 0)
			w.add2("a.chapter", chapter);
		String s3 = "select a.id, a.course, b.number, rank, content , b.title as chapterTitle"
				+ " from example a JOIN chapter b " + " ON a.chapter = b.number  AND a.course=b.course " + w
				+ " ORDER BY a.chapter ASC, a.rank ASC "; // 按章节号、难度排序
		//System.out.println("SQL: " + s3);

		// 查询出相关习题
		JSONArray jarray = MyC3P0Factory.executeQuery2JSON(s3);
		return jarray;
	}

}
