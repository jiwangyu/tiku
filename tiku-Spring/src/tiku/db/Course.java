package tiku.db; 


import java.util.Date; 

// 本类由 POJO生成器 自动生成于 2019-08-10 16:36:52


public class Course 
{ 
 
	public Integer id ; 
	public String title ; 

	public Course()
	{
		
	}
	
	public Course(int id, String title)
	{
		this.id = id;
		this.title=title;
		
	}
	public void setId(Integer id)
	{
		this.id=id;
	}
	public Integer getId()
	{
		return this.id;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	public String getTitle()
	{
		return this.title;
	}

} 
 