package tiku.admin.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import af.web.restful.AfRestfulApi;
import af.web.restful.AfRestfulException;
import tiku.db.Admin;


public class Permissions
{
	HttpSession session;
	
	public Permissions(HttpSession session)
	{		
		this.session = session;
	}
	public Permissions(AfRestfulApi api)
	{		
		this.session = api.httpReq.getSession();
	}	
	public Permissions(HttpServletRequest req)
	{		
		this.session = req.getSession();
	}		
	
	public Admin checkLogin () throws Exception
	{
		Admin admin = (Admin) session.getAttribute("admin");
		if( admin == null)
			throw new AfRestfulException(-90, "未登录");
		return admin;
	}
	
}
