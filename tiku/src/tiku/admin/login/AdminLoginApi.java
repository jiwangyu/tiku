package tiku.admin.login;

import org.json.JSONObject;

import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import tiku.MyC3P0Factory;
import tiku.db.Admin;

public class AdminLoginApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		String username = jreq.getString("username");
		String password = jreq.getString("password");

		AfSqlWhere w = new AfSqlWhere();
		w.add2("username", username);
		String sql = "select * from admin " + w;
		Admin row = (Admin)MyC3P0Factory.get(sql, Admin.class);
		if (row == null)
			throw new Exception("用户名/密码错误！");
		if (!password.equals(row.password))
			throw new Exception("用户名/密码错误！");
		httpReq.getSession().setAttribute("admin", row);
		
		return null;
	}

}
