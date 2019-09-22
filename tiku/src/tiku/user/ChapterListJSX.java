package tiku.user;

import java.util.List;

import org.json.JSONArray;

import af.web.jsx.AfJsxCreator;
import tiku.MyC3P0Factory;
import tiku.db.Chapter;

public class ChapterListJSX extends AfJsxCreator
{

	@Override
	public Object execute() throws Exception
	{
		// 取得所有章节
		String s2 = "select * from chapter ORDER BY course ASC, number ASC";
		List<Chapter> chapter_list = MyC3P0Factory.executeQuery(s2, Chapter.class);
		return new JSONArray(chapter_list);
	}

}
