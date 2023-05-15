package kr.or.ddit.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class newsVO {
	
	private  int newsId;
	private String newsTitle;
	private Date newsStart;
	private String newsField;
	private int newsHit;
	private String newsContent;

}
