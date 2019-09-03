package tiku.admin.chapter;

import org.json.JSONArray;
import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import tiku.MyC3P0Factory;
import tiku.db.Admin;
import tiku.db.Chapter;
import tiku.admin.login.Permissions;

public class ChapterSaveApi extends AfRestfulApi
{
	
	
	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin = new Permissions(this).checkLogin();
		int course = jreq.getInt("course");
		JSONArray chapter_list = jreq.getJSONArray("chapter_list");
		
		if(true){
			String sql = "delete from chapter where course="+course;
			MyC3P0Factory.execute(sql);
		}
		
		for(int i=0;i<chapter_list.length();i++){
			String title = chapter_list.getString(i);
			Chapter row = new Chapter();
			row.setTitle( title );
			row.setCourse( course);
			row.setNumber( i + 1); 
			MyC3P0Factory.insert( row );
		}
		return null;
	}

}
