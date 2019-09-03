package tiku.admin.course;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import tiku.MyC3P0Factory;
import tiku.db.Admin;
import tiku.admin.login.Permissions;

public class CourseUpdateApi extends AfRestfulApi
{
	static Logger logger = Logger.getLogger(CourseUpdateApi.class);
	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin = new Permissions(this).checkLogin();
		int id = jreq.getInt("id");
		String title = jreq.getString("title");
		
		AfSqlUpdate u = new AfSqlUpdate("course").add2("title", title);
		AfSqlWhere w = new AfSqlWhere().add2("id", id);
		
		String sql = u+" "+w;
		
		logger.debug("更新Course, SQL:" + sql);
		MyC3P0Factory.execute(sql);
		return null;
	}

}
