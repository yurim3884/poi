package kr.or.ddit.vo;

import kr.or.ddit.dounload.ExcelColumn;
import lombok.Data;

@Data
public class moduleVO {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}
	
	@ExcelColumn(headerName = "아이디")
	private String id;
	@ExcelColumn(headerName = "이름")
	private String name;
	@ExcelColumn(headerName = "성별")
	private String gender;
	@ExcelColumn(headerName = "비밀번호")
	private String password;
	@ExcelColumn(headerName = "GOOD")
	private String good;
	
	//다운로드 시 vo에 추가해주고 @ExcelColumn어노테이션 추가해주세요
	

}
