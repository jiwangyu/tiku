package tiku.admin.course;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import tiku.MyC3P0Factory;
import tiku.db.Admin;
import tiku.db.Course;
import tiku.admin.login.Permissions;

public class CourseAddApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin = new Permissions(this).checkLogin();
		Course row = new Course();
		row.setTitle(jreq.getString("title"));
		MyC3P0Factory.insert(row);
		return new JSONObject(row);
	}

}
