package com.onlinemarket.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.onlinemarket.MainController;
import com.onlinemarket.Entities.Collaborator;
import com.onlinemarket.Entities.User;
import com.onlinemarket.Repository.CollaboratorRepository;
import com.onlinemarket.Repository.UserRepository;
@Controller
public class registerationController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CollaboratorRepository CollRepo;
	
	@GetMapping("/registration")
	public String ShowMembers(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(Model model,@ModelAttribute User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getMail());
		if(user.getUsername().isEmpty()||user.getMail().isEmpty()||user.getType().isEmpty()||user.getPassword().isEmpty()) {
			return "registration";
		}
		userRepo.save(user);
		model.addAttribute("user", new User());
		return "Login";
	}
	
	@GetMapping("/addCollaborator")
	public String addCollaborator(Model model,HttpServletRequest session) {
		MainController.getSessions(model, session);
		Integer ID=(Integer) session.getSession().getAttribute("id");
		Iterable<Collaborator> CollIterable=CollRepo.findAll();
		List<Collaborator> collList=new ArrayList<Collaborator>();
		List<User> userList=new ArrayList<User>();
		for(Collaborator user: CollIterable) 
		{
			if(user.getIdOwner().equals(ID))
			{
				userList.add(userRepo.findOne(user.getIdCollaborator()));
			}
		}
		model.addAttribute("Users",userList);
		model.addAttribute("user", new User());
		return "addCollaborator";
	}
	
	@PostMapping("/addCollaborator")
	public String addCollaborator(Model model,@ModelAttribute User user,HttpServletRequest session) {
		MainController.getSessions(model, session);
		Integer ID=(Integer) session.getSession().getAttribute("id");
		System.out.println(user.getUsername());
		System.out.println(user.getMail());
		if(user.getUsername().isEmpty()||user.getMail().isEmpty()||user.getPassword().isEmpty()) {
			return "addCollaborator";
		}
		user.setType("Collaborator");
		userRepo.save(user);
		Collaborator UserColl=new Collaborator();
		UserColl.setIdOwner(ID);
		int IdCol=user.getId();
		UserColl.setIdCollaborator(IdCol);
		CollRepo.save(UserColl);
		System.out.println(ID+"<<<>>>"+IdCol);
		
		MainController.getSessions(model, session);
		//Integer ID=(Integer) session.getSession().getAttribute("id");
		Iterable<Collaborator> CollIterable=CollRepo.findAll();
		List<Collaborator> collList=new ArrayList<Collaborator>();
		List<User> userList=new ArrayList<User>();
		for(Collaborator CollUser: CollIterable) 
		{
			if(CollUser.getIdOwner().equals(ID))
			{
				userList.add(userRepo.findOne(CollUser.getIdCollaborator()));
			}
		}
		model.addAttribute("Users",userList);
		model.addAttribute("user", new User());
		return "addCollaborator";
	}
}
