package kr.or.ddit.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import kr.or.ddit.mapper.ModuleMapper;
import kr.or.ddit.upload.ExcelRead;
import kr.or.ddit.upload.ExcelReadOption;
import kr.or.ddit.vo.moduleVO;
import kr.or.ddit.vo.payVO;

@Service
public class ModuleServiceImpl implements ImoduleService {

	@Inject
	private ModuleMapper mapper;

	@Override
	public List<moduleVO> selectList() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

	@Override
	public void excelUpload(File destFile) {
		 ExcelReadOption excelReadOption = new ExcelReadOption();
        
        //파일경로 추가
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        
        //추출할 컬럼명 추가
        excelReadOption.setOutputColumns("A", "B", "C","D","E");
        
        //시작행
        excelReadOption.setStartRow(1);
        
        List<Map<String, String>> excelContent  = ExcelRead.read(excelReadOption);
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("excelContent", excelContent);
        
        try {
        	mapper.insertExcel(paramMap);
        }catch(Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public payVO selectPay(payVO pay) {
		// TODO Auto-generated method stub
		pay.setMemId("1");
		pay.setProdNo("1");
		
		return mapper.selectPay(pay);
	}

	@Override
	public void insert(payVO pay) {
		mapper.insertPay(pay);
		
	}

}
