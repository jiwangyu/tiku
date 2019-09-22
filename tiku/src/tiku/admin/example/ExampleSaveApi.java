package tiku.admin.example;

import java.util.Date;

import org.json.JSONObject;

import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;
import af.web.restful.AfRestfulApi;
import tiku.MyC3P0Factory;
import tiku.db.Admin;
import tiku.db.Example;
import tiku.admin.login.Permissions;

public class ExampleSaveApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		Admin admin = new Permissions(this).checkLogin();
		Example row = new Example();
		row.course = jreq.getInt("course");
		row.chapter = jreq.getInt("chapter");
		row.rank = (byte) jreq.getInt("rank");
		row.content = jreq.getString("content");
		row.answer = jreq.getString("answer");
		if (jreq.has("example_id"))
		{
			int example_id = jreq.getInt("example_id");
			update(example_id, row);

		} else
		{
			save(row);
		}
		return null;
	}

	public void save(Example row) throws Exception
	{
		row.setTimeCreated(new Date());
		row.setTimeModified(new Date());
		row.setStatus((byte) 0);

		MyC3P0Factory.insert(row);
	}

	public void update(int example_id, Example row) throws Exception
	{
		AfSqlUpdate u = new AfSqlUpdate("example");
		u.add2("chapter", row.chapter);
		u.add2("rank", (int) row.rank);
		u.add2("content", row.content);
		u.add2("answer", row.answer);

		AfSqlWhere w = new AfSqlWhere().add2("id", example_id);
		String sql = "" + u + w;
		MyC3P0Factory.execute(sql);
	}

}
