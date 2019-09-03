package tiku.admin.example;

import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import tiku.MyC3P0Factory;
import tiku.db.Example;

public class ExampleLoadApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		
		int id = jreq.getInt("example_id");
		String sql = "select * from example where id=" + id;
		Example row = (Example) MyC3P0Factory.get(sql, Example.class);
		
		return new JSONObject(row);
	}

}
