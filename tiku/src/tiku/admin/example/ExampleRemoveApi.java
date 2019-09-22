package tiku.admin.example;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import tiku.MyC3P0Factory;
import tiku.db.Admin;
import tiku.admin.login.Permissions;

public class ExampleRemoveApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin = new Permissions(this).checkLogin();
		int id = jreq.getInt("id");
		String sql = "delete from example where id=" + id;
		MyC3P0Factory.execute(sql);

		return null;
	}

}
