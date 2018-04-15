package com.onlinemarket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;

@SpringBootApplication
public class MainController {
	public static void getSessions(Model model,HttpServletRequest session) {
		String user = (String)session.getSession().getAttribute("user");
		Integer id = (Integer)session.getSession().getAttribute("id");
		String mail =(String)session.getSession().getAttribute("mail");
		String type = (String)session.getSession().getAttribute("type");
		model.addAttribute("id", id);
		model.addAttribute("user", user);
		model.addAttribute("mail", mail);
		model.addAttribute("type", type);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(MainController.class,args);
	}

}
