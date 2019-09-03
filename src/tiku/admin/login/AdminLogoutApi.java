package tiku.admin.login;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;

public class AdminLogoutApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject arg0) throws Exception
	{
		httpReq.getSession().setAttribute("admin", null);
		return null;
	}

}
