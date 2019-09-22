package tiku.db; 


import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField; 

// 本类由 POJO生成器 自动生成于 2019-08-10 16:36:52


public class Example 
{ 
 
	public Integer id ; 
	public String title ; 
	public Integer creator ; 
	public Integer course ; 
	public Integer chapter ; 
	public Integer section ; 
	public Byte rank ; 
	public String digest ; 
	public String content ; 
	public String hint ; 
	public String answer ; 
	public Byte status ; 
	public Byte priority ; 
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date timeCreated ; 
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date timeModified ; 


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
	public void setCreator(Integer creator)
	{
		this.creator=creator;
	}
	public Integer getCreator()
	{
		return this.creator;
	}
	public void setCourse(Integer course)
	{
		this.course=course;
	}
	public Integer getCourse()
	{
		return this.course;
	}
	public void setChapter(Integer chapter)
	{
		this.chapter=chapter;
	}
	public Integer getChapter()
	{
		return this.chapter;
	}
	public void setSection(Integer section)
	{
		this.section=section;
	}
	public Integer getSection()
	{
		return this.section;
	}
	public void setRank(Byte rank)
	{
		this.rank=rank;
	}
	public Byte getRank()
	{
		return this.rank;
	}
	public void setDigest(String digest)
	{
		this.digest=digest;
	}
	public String getDigest()
	{
		return this.digest;
	}
	public void setContent(String content)
	{
		this.content=content;
	}
	public String getContent()
	{
		return this.content;
	}
	public void setHint(String hint)
	{
		this.hint=hint;
	}
	public String getHint()
	{
		return this.hint;
	}
	public void setAnswer(String answer)
	{
		this.answer=answer;
	}
	public String getAnswer()
	{
		return this.answer;
	}
	public void setStatus(Byte status)
	{
		this.status=status;
	}
	public Byte getStatus()
	{
		return this.status;
	}
	public void setPriority(Byte priority)
	{
		this.priority=priority;
	}
	public Byte getPriority()
	{
		return this.priority;
	}
	public void setTimeCreated(Date timeCreated)
	{
		this.timeCreated=timeCreated;
	}
	public Date getTimeCreated()
	{
		return this.timeCreated;
	}
	public void setTimeModified(Date timeModified)
	{
		this.timeModified=timeModified;
	}
	public Date getTimeModified()
	{
		return this.timeModified;
	}

} 
 