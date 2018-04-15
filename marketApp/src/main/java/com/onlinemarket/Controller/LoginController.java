package com.onlinemarket.Controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlinemarket.Entities.Collaborator;
import com.onlinemarket.Entities.Product;
import com.onlinemarket.Entities.User;
import com.onlinemarket.Repository.CollaboratorRepository;
import com.onlinemarket.Repository.UserRepository;

@Controller
public class LoginController {

	
		@Autowired
		private UserRepository UserRepo;
		
		@Autowired
		private CollaboratorRepository CollRepo;
		
		@GetMapping("/Login")
		public String LoginForm(Model model)
		{
			model.addAttribute("user" , new User());
			return "Login";
		} 
		
		@RequestMapping(value = "/Login", method = RequestMethod.POST)
		public String Login(Model model ,@ModelAttribute User user,HttpServletRequest session)
		{
			//System.out.println("--->>>"+user.getMail());
			String direction="/login";
			System.out.println(user.getMail());
			System.out.println(user.getPassword());
//			System.out.println(user.getType());
			Iterable<User> userIterable=UserRepo.findAll();
			List<User> userList=new ArrayList<User>();
			for(User user1: userIterable) {
				
				if(user1.getMail().equals(user.getMail())&&user1.getPassword().equals(user.getPassword())) {
					System.out.println("/////////// true ");
					System.out.println("->>>>"+user1.getType());
					if(user1.getType().equals("buyer")) {
						direction="/ShowAllProducts";
						session.getSession().setAttribute("user",user1.getUsername());
						session.getSession().setAttribute("id",user1.getId());
						session.getSession().setAttribute("mail",user1.getMail());
						session.getSession().setAttribute("type",user1.getType());
					}
					else if(user1.getType().equals("storeowner")) {
						direction="/StoreOwnerProduct";
						session.getSession().setAttribute("user",user1.getUsername());
						session.getSession().setAttribute("id",user1.getId());
						session.getSession().setAttribute("mail",user1.getMail());
						session.getSession().setAttribute("type",user1.getType());
					}
					else if(user1.getType().equals("Collaborator")) {
						direction="/StoreOwnerProduct";
						session.getSession().setAttribute("user",user1.getUsername());
						session.getSession().setAttribute("id",user1.getId());
						session.getSession().setAttribute("mail",user1.getMail());
						session.getSession().setAttribute("type",user1.getType());
						Iterable<Collaborator> CollIterable=CollRepo.findAll();
						List<Collaborator> CollList=new ArrayList<Collaborator>();
						for(Collaborator Colluser: CollIterable) {
							if(Colluser.getIdCollaborator().equals(user1.getId()))
							{
								session.getSession().setAttribute("idOwner",Colluser.getIdOwner());
								System.out.println("IdOwner ->>>>"+Colluser.getIdOwner());
								break;
							}
						}
						
						
					}
					else if (user1.getType().equals("admin")) {
						direction="/addProduct";
						session.getSession().setAttribute("user",user1.getUsername());
						session.getSession().setAttribute("id",user1.getId());
						session.getSession().setAttribute("mail",user1.getMail());
						session.getSession().setAttribute("type",user1.getType());
					}
				}
				else if(user1.getMail().equals(user.getMail())){
					System.out.println("/////////// false");
					break;
				}
			}
			return direction;
			//model.addAttribute("user" , new User());
			
		}
}
