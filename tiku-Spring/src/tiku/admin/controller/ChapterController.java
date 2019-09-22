package tiku.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tiku.db.Chapter;
import tiku.db.Course;
import tiku.db.MyBatis;
import tiku.util.AfRestData;

@Controller
@RequestMapping("/chapter")
public class ChapterController
{
	@GetMapping("/query")
	public String query(Model model, @RequestParam("id") Integer course_id)
	{
		Course row = new Course();
		row.id = course_id;
		List<Chapter> rows = new ArrayList();
		try (SqlSession session = MyBatis.factory.openSession())
		{
			row = session.selectOne("course.get", row);
			rows = session.selectList("chapter.list", course_id);
		}
		JSONObject courseJ = (JSONObject) JSON.toJSON(row);
		JSONArray chaptersJ = (JSONArray) JSON.toJSON(rows);
		model.addAttribute("course", courseJ);
		model.addAttribute("chapters", chaptersJ);
		return "chapter";
	}

	@PostMapping("/save.sudo")
	@ResponseBody
	public View save(@RequestBody JSONObject json) throws Exception
	{
		int course = json.getIntValue("course");
		JSONArray chapter_list = (JSONArray) json.get("chapter_list");
		try (SqlSession session = MyBatis.factory.openSession())
		{
			session.delete("chapter.delete", course);
			session.commit(true);
			for(int i=0;i<chapter_list.size();i++){
				String title = chapter_list.getString(i);
				Chapter row = new Chapter();
				row.setTitle( title );
				row.setCourse( course);
				row.setNumber( i + 1); 
				session.insert("chapter.save", row);
				session.commit(true);
			}
		}
		return new AfRestData(null);
	}

}
