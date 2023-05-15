package kr.or.ddit.service;

import java.io.File;
import java.util.List;

import kr.or.ddit.vo.moduleVO;
import kr.or.ddit.vo.payVO;

public interface ImoduleService {
	public List<moduleVO> selectList() throws Exception;

	public void excelUpload(File destFile);
	
	public payVO selectPay(payVO pay);

	public void insert(payVO pay);

	public int getMovieInfoFromWEB();

}
