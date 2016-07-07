package com.TRMS.home.controller;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.TRMS.common.CommonConstants;
import com.TRMS.home.dto.LinkDataDto;
import com.TRMS.home.dto.LinkDataResultDto;
import com.TRMS.home.dto.RuleDto;
import com.TRMS.home.service.IExtractService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private IExtractService extractService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) {
		
		logger.info("starting para Html");
		
//		if (logger.isDebugEnabled()) {
//			logger.debug("getWelcome is executed!");
//		}
//		//logs exception
//		logger.error("This is Error message", new Exception("Testing"));
//		Date date = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		dateFormat.format(date);
//		String formattedDate = dateFormat.format(date);
//		model.addAttribute("serverTime", formattedDate );
		
		RuleDto ruleDto = new RuleDto();
		ruleDto.setUrl("http://news.baidu.com/ns");
		ruleDto.setParams(new String[] {"ct","ie","bs","tn","word"});
		ruleDto.setValues(new String[] {"1","utf-8","jsoup","news","jsoup"});
		ruleDto.setRequestMethod(CommonConstants.GET);
		ruleDto.setType(CommonConstants.CLASS);
//		ruleDto.setType(-1);
		ruleDto.setResultTagName("wrapper_l");
//		ruleDto.setResultTagName(null);
		
		List<LinkDataDto> linkDataDtoList = extractService.extract(ruleDto);
		
		LinkDataResultDto linkDataResultDto = new LinkDataResultDto();
		linkDataResultDto.setLinkDataDtoList(linkDataDtoList);;
		
		String result = restTemplate.postForObject("http://localhost:8080/NTRK/saveForRedis", linkDataResultDto, String.class);
		
		model.addAttribute("linkDataResultDto", linkDataResultDto);
		
		return "index";
	}
}
