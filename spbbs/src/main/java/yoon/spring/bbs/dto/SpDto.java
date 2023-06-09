package yoon.spring.bbs.dto;

import java.sql.Timestamp;

public class SpDto {
	private int num;
	private int s_group;
	private int s_step;
	private int s_indent;
	private String uname;
	private String upass;
	private String title;
	private String content;
	private int ct;
	private int hit;
	private Timestamp wdate;

	public SpDto() {
	}

	public SpDto(int num, int s_group, int s_step, int s_indent, String uname, String upass, String title,
			String content, int ct, int hit, Timestamp wdate) {
		super();
		this.num = num;
		this.s_group = s_group;
		this.s_step = s_step;
		this.s_indent = s_indent;
		this.uname = uname;
		this.upass = upass;
		this.title = title;
		this.content = content;
		this.ct = ct;
		this.hit = hit;
		this.wdate = wdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getS_group() {
		return s_group;
	}

	public void setS_group(int s_group) {
		this.s_group = s_group;
	}

	public int getS_step() {
		return s_step;
	}

	public void setS_step(int s_step) {
		this.s_step = s_step;
	}

	public int getS_indent() {
		return s_indent;
	}

	public void setS_indent(int s_indent) {
		this.s_indent = s_indent;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCt() {
		return ct;
	}

	public void setCt(int ct) {
		this.ct = ct;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Timestamp getWdate() {
		return wdate;
	}

	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}

}
