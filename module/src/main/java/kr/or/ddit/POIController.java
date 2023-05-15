package kr.or.ddit;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.dounload.SimpleExcelFile;
import kr.or.ddit.service.ImoduleService;
import kr.or.ddit.vo.moduleVO;

@Controller
@RequestMapping("/")
public class POIController {
	
	//컬럼 이름 바꿔줘야한다
	final String[] colNames = {
			"아이디", "이름", "성별", "비밀번호"
		};
	
	@Inject
	private ImoduleService moduleService;
	
	@RequestMapping (value="/list" ,method = RequestMethod.GET)
	public String selectAll(Model model)  throws Exception{
		List<moduleVO> listAll  = moduleService.selectList();
		model.addAttribute("listAll",listAll);
		return "/list";
	}
	

//	@RequestMapping(value = "/poiExcel", method = RequestMethod.POST)
//	public void poiTest(Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
//
//		List<moduleVO> list  = moduleService.selectList();
//
//		Workbook wb = new HSSFWorkbook(); // 엑셀파일 객체 생성
//		Sheet sheet = wb.createSheet("테스트 시트"); // 시트 생성 ( 첫번째 시트이며, 시트명은 테스트 시트 )
//
//		CellStyle style = wb.createCellStyle(); // 셀 스타일 생성
//		Font font = wb.createFont(); // 폰트 스타일 생성
//
//		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 글자 진하게
//		font.setFontHeight((short) (16 * 18)); // 글자 크기
//		font.setFontName("맑은 고딕"); // 글씨체
//
//		// 헤더 만들기
//		
//		Row headerRow = sheet.createRow(3); // 네번째줄 생성
//		
//		for (int i = 0; i < colNames.length; i++) {
//		Cell headerCell = headerRow.createCell(i);
//		headerCell.setCellValue(colNames[i]);
//		}
//
//		CellStyle dataStyle = wb.createCellStyle(); // 데이터스타일은 테두리를 만들어보자
//
//		dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 오른쪽 테두리
//		dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 왼쪽 테두리
//		dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 상단 테두리
//		dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 하단 테두리
//
//		// 데이터 삽입하기
//		int rowNum = 1; // 네번째줄이 헤더니까 그 밑에서부터 데이터 삽입
//		int cellNum = 0;
//		Row dataRow = null; // for문을 돌려주기위해.
//		Cell dataCell = null;
//		
//		list.forEach(vo->vo.getId());
//		for(moduleVO vo : list) {
//			vo.getGender();
//			vo.getGood();
//		}
//		
//		for (int i = 0; i < list.size(); i++) {
//			cellNum = 0;
//			dataRow = sheet.createRow(rowNum++); // for문 돌면서 행 1줄씩 추가
//
//			dataCell = dataRow.createCell(cellNum++); // 열 한줄씩 추가
//			dataCell.setCellValue(list.get(i).getId()); // 첫번째칸은 순번이기때문에
//
//			dataCell = dataRow.createCell(cellNum++); // 두번째 열은 이름이니까
//			dataCell.setCellValue(list.get(i).getName()); // list에 저장된 이름 출력
//			
//			dataCell = dataRow.createCell(cellNum++); // 세번째 열은 설별이니까
//			dataCell.setCellValue(list.get(i).getGender()); // list에 저장된 이름 출력
//			
//			dataCell = dataRow.createCell(cellNum); // 네번째 열은 비밀번호이니까
//			dataCell.setCellValue(list.get(i).getPassword()); // list에 저장된 이름 출력
//			
//		}
//		/* 엑셀 파일 생성 */
//		response.setContentType("ms-vnd/excel");
//		response.setHeader("Content-Disposition", "attachment;filename=poiTest.xls");
//		wb.write(response.getOutputStream());
//
//	}
//	
//	@RequestMapping (value="/upload" ,method = RequestMethod.GET)
//	public String upload()  throws Exception{
//		return "/upload";
//	}
	
	@ResponseBody
    @RequestMapping(value = "/excelUpload", method = RequestMethod.POST ,headers = ("content-type=multipart/*"))
        public ModelAndView excelUploadAjax(MultipartFile testFile, MultipartHttpServletRequest request) throws  Exception{
        
        System.out.println("업로드 진행");
        
        MultipartFile excelFile = request.getFile("excelFile");
        
        if(excelFile == null || excelFile.isEmpty()) {
            throw new RuntimeException("엑셀파일을 선택해 주세요");
        }
        
        File destFile = new File("C:\\upload\\"+excelFile.getOriginalFilename());
        
        try {
            //내가 설정한 위치에 내가 올린 파일을 만들고 
            excelFile.transferTo(destFile);
        }catch(Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
        //업로드를 진행하고 다시 지우기
        moduleService.excelUpload(destFile);
        
        destFile.delete();
        
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/list");
        
        return view;
    }
	
	@RequestMapping("/free/excelDown")
	public void excelDownload(HttpServletResponse response) throws Exception{
		//엑셀에 실제로 담을 데이터  페이징 없이 전체 데이터를  list로 
		List<moduleVO> list=moduleService.selectList();
		//다운로드 시 vo에 추가해주고 @ExcelColumn어노테이션 추가해주세요
		
		SimpleExcelFile simpleExcelFile= new SimpleExcelFile("모듈테스트", moduleVO.class, list);
		//생성자에서 sheet이름, columnName, Body생성
		 
		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=freeBoard.xlsx");
		simpleExcelFile.write(response.getOutputStream());
	}

}