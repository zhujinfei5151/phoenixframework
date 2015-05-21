package org.phoenix.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart")
public class DwrController {
	
	@RequestMapping("/mychart")
	public String getCaseStatistics(){
		
		return "chart/dwr03";
	}
}
