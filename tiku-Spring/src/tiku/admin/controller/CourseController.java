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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import tiku.db.Course;
import tiku.db.MyBatis;
import tiku.util.AfRestData;

@Controller
@RequestMapping("/course")
public class CourseController
{
	@GetMapping("/query")
	public String query(Model model)
	{
		List<Course> rows = new ArrayList<>();
		try (SqlSession session = MyBatis.factory.openSession())
		{
			rows = session.selectList("course.list");
		}
		model.addAttribute("course_list", rows);
		return "course"; // 模板路径 /WEB-INF/template/query.html
	}

	@PostMapping("/add.sudo")
	@ResponseBody
	public View add(@RequestBody JSONObject json) throws Exception
	{
		Course row = JSON.toJavaObject(json, Course.class);

		try (SqlSession session = MyBatis.factory.openSession())
		{
			session.insert("course.add", row);
			session.commit(true);
		}
		return new AfRestData(row);
	}

	@PostMapping("/remove.sudo")
	@ResponseBody
	public View remove(@RequestBody JSONObject json) throws Exception
	{
		Course row = JSON.toJavaObject(json, Course.class);
		try (SqlSession session = MyBatis.factory.openSession())
		{
			session.delete("course.delete", row);
			session.commit(true);
		}
		return new AfRestData(row);
	}

	@PostMapping("/get.sudo")
	@ResponseBody
	public View get(@RequestBody JSONObject json) throws Exception
	{
		Course row = JSON.toJavaObject(json, Course.class);
		try (SqlSession session = MyBatis.factory.openSession())
		{
			row = session.selectOne("course.get", row);
		}
		return new AfRestData(row);
	}

	@PostMapping("/update.sudo")
	@ResponseBody
	public View update(@RequestBody JSONObject json) throws Exception
	{
		Course row = JSON.toJavaObject(json, Course.class);
		try (SqlSession session = MyBatis.factory.openSession())
		{
			session.update("course.update", row);
			session.commit(true);
		}
		return new AfRestData(null);
	}
}
