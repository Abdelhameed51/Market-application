package com.onlinemarket.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinemarket.MainController;
import com.onlinemarket.Entities.Product;
import com.onlinemarket.Entities.storeOwner;
import com.onlinemarket.Repository.ProductRepository;
import com.onlinemarket.Repository.StoreRepository;
@Controller
public class showProduct {
	@Autowired
	private ProductRepository repo;
	@Autowired
	private StoreRepository storeRepo;
	@RequestMapping("/addProduct")
	public String ShowProducts(Model model) {
		Iterable<Product> productIterable=repo.findAll();
		List<Product> productList=new ArrayList<Product>();
		for(Product prod: productIterable) {
			productList.add(prod);
		}
		model.addAttribute("products",productList);
		return "addProduct";
	}
	@RequestMapping("/ShowAllProducts")
	public String ShowAllProducts(Model model) {
		Iterable<Product> productIterable=repo.findAll();
		List<Product> productList=new ArrayList<Product>();
		for(Product prod: productIterable) {
			productList.add(prod);
		}
		model.addAttribute("product",productList);
		return "ShowAllProducts";
	}
	//@RequestMapping("/StoreOwnerProduct")
	@RequestMapping(value="/StoreOwnerProduct",method = RequestMethod.GET)
	public String storeownerProducts(Model model,HttpServletRequest session) {
		storeOwner temp=new storeOwner();
		Integer ID=(Integer) session.getSession().getAttribute("id");
		String type= (String) session.getSession().getAttribute("type");
		//Integer ID=(Integer)session.getSession().getAttribute("id");
		if(type.equals("storeowner"))
		{
			ID=(Integer)session.getSession().getAttribute("id");
		}
		else if(type.equals("Collaborator"))
		{
			ID=(Integer)session.getSession().getAttribute("idOwner");
		}
		Iterable<storeOwner> storeOwnerIterable=storeRepo.findAll();
		List<storeOwner> ownerList=new ArrayList<storeOwner>();
		
		List<Product> productList=new ArrayList<Product>();
		
		for(storeOwner row: storeOwnerIterable) {
			if(row.getIdOwner()==ID) {
				//ownerList.add(row);
				productList.add(repo.findOne(row.getIdProduct()));
			}
		}
		model.addAttribute("products",productList);
		return "/StoreOwnerProduct";
	}
	@RequestMapping(value="/ShowAllProducts2",method = RequestMethod.GET)
	public String ShowAllProducts2(Model model) {
		Iterable<Product> productIterable=repo.findAll();
		List<Product> productList=new ArrayList<Product>();
		for(Product prod: productIterable) {
			productList.add(prod);
		}
		model.addAttribute("product",productList);
		model.addAttribute("storeOwner", new storeOwner());
		return "ShowAllProducts2";
	}
	@RequestMapping(value="/ShowAllProducts2",method = RequestMethod.POST)
	public String ShowProducts2(Model model) {
		Iterable<Product> productIterable=repo.findAll();
		List<Product> productList=new ArrayList<Product>();
		for(Product prod: productIterable) {
			productList.add(prod);
		}
		model.addAttribute("product",productList);
		model.addAttribute("storeOwner", new storeOwner());
		return "/ShowAllProducts2";
	}
//	@RequestMapping(value="/showAllProducts2/get/{id}",method = RequestMethod.GET)
//	public String Show2(Model model,@PathVariable("id") Integer id,HttpServletRequest session) {
//		Iterable<Product> productIterable=repo.findAll();
//		List<Product> productList=new ArrayList<Product>();
//		for(Product prod: productIterable) {
//			productList.add(prod);
//		}
//		model.addAttribute("product",productList);
//		storeOwner temp=new storeOwner();
//		Integer ID=(Integer) session.getSession().getAttribute("id");
//		temp.setIdOwner(ID);
//		temp.setIdProduct(id);
//		storeRepo.save(temp);
//		System.out.println("-->>"+id+" <<<>>> "+ID+" <<>> "+temp.getIdOwner());
//		return "ShowAllProducts2";
//	}
//	@RequestMapping(value="/showAllProducts2/get/{id}", method = RequestMethod.POST)
//    public String getproduct (Model model,@PathVariable("id") Integer id,HttpServletRequest session) {
//		MainController.getSessions(model, session);
//		storeOwner temp=new storeOwner();
//		Integer ID=(Integer) session.getSession().getAttribute("id");
//		temp.setIdOwner(ID);
//		temp.setIdProduct(id);
//		storeRepo.save(temp);
//		System.out.println("-->>"+id+"->>>"+ID);
//       
//       return "ShowAllProducts2";
//    }
//	

//	@RequestMapping(value="/productdetails/get/{id}",method = RequestMethod.GET)
//	public String showProductDetails(Model model,@PathVariable("id") Integer id) {
//		Product product = repo.findOne(id);
//		product.setCounter(product.getCounter()+1);
//		repo.save(product);
//		model.addAttribute("product",product);
//		 System.out.println("-->>"+id);
//		//model.addAttribute("storeOwner", new storeOwner());
//		return "productdetails";
//	}

//	@RequestMapping(value = "/ShowAllProducts2/{id}", method = RequestMethod.POST)
//	public String postPrintHello(Model model ,@ModelAttribute Product product,@ModelAttribute storeOwner storeOwner,@PathVariable("id") Integer id){
//
//		System.out.println("---->>"+id);
//	    return "ShowAllProducts2";
//	}
}
