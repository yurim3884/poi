package kr.or.ddit;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.service.ImoduleService;
import kr.or.ddit.vo.payVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pay")
public class payController {

	@Inject
	private ImoduleService Service;

	@GetMapping("/pay")
	public String pay() {
		return "/test";
	}

	@RequestMapping(value = "/payss", method = RequestMethod.POST)
	public String crudRegisterForm(payVO pay, Model model) {
		log.info("crudRegisterForm()");
		System.out.println(pay);
		Service.insert(pay);
		return "redirect:/pay/pay";
	}
	
	@GetMapping("/parsing")
	public String parsing() {
		return "/parsingTest";
	}


}
