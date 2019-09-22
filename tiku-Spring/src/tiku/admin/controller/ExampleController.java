package tiku.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import tiku.db.Example;
import tiku.db.MyBatis;
import tiku.util.AfRestData;

@Controller
@RequestMapping("/example")
public class ExampleController
{
	@GetMapping("/query")
	public String query(Model model)
	{
		List<Course> course_list = new ArrayList();
		List<Chapter> chapter_list = new ArrayList();
		try (SqlSession session = MyBatis.factory.openSession())
		{
			course_list = session.selectList("course.list");
			chapter_list = session.selectList("example.getChapter");
		}
		JSONArray course_listJ = (JSONArray) JSON.toJSON(course_list);
		JSONArray chapter_listJ = (JSONArray) JSON.toJSON(chapter_list);
		model.addAttribute("course_listJ", course_listJ);
		model.addAttribute("chapter_listJ", chapter_listJ);
		return "example";
	}

	@PostMapping("/list.sudo")
	@ResponseBody
	public View add(@RequestBody JSONObject json) throws Exception
	{
		int course = json.getIntValue("course");
		int chapter = json.getIntValue("chapter");
		List example_list = new ArrayList<>();
		try (SqlSession session = MyBatis.factory.openSession())
		{
			Map<String, Object> params = new HashMap<>();
			params.put("course", course);
			params.put("chapter", chapter);
			example_list = session.selectList("example.getExample", params);
		}
		JSONArray example_listJ = (JSONArray) JSON.toJSON(example_list);
		return new AfRestData(example_listJ);
	}

	@PostMapping("/remove.sudo")
	@ResponseBody
	public View remove(@RequestBody JSONObject json) throws Exception
	{
		int id = json.getIntValue("id");
		try (SqlSession session = MyBatis.factory.openSession())
		{
			session.delete("example.remove");
			session.commit();
		}
		return new AfRestData(null);
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam("course") Integer courseId)
	{
		Course course = new Course();
		List<Chapter> chapter_list = new ArrayList();

		try (SqlSession session = MyBatis.factory.openSession())
		{
			course = session.selectOne("getCourse", courseId);
			chapter_list = session.selectList("getChapter2", courseId);
		}
		JSONObject courseJ = (JSONObject) JSON.toJSON(course);
		JSONArray chaptersJ = (JSONArray) JSON.toJSON(chapter_list);
		model.addAttribute("course", courseJ);
		model.addAttribute("chapter_list", chaptersJ);

		return "example_edit";
	}

	@PostMapping("/save.sudo")
	@ResponseBody
	public View save(@RequestBody JSONObject json) throws Exception
	{
		Example row = JSON.toJavaObject(json,Example.class);
		
		return new AfRestData(null);
	}
}
