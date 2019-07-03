package com.niitproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class navigationController 
  {
	@RequestMapping(value="/") 
	public String displayHome() {
	return "index";
  }
@RequestMapping(value="/shopfrontend") 
public String displayHom()
  {
   return "index";
  }

@RequestMapping(value="/Contact") 
public String displayContact() {
return "Footnote";
  }
}