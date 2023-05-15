package kr.or.ddit.mapper;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.moduleVO;
import kr.or.ddit.vo.payVO;

public interface ModuleMapper {

	public List<moduleVO> selectAll() throws Exception;

	public void insertExcel(Map<String, Object> paramMap);

	public payVO selectPay(payVO pay);

	public void insertPay(payVO pay); 
	
	

}
