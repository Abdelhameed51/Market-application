package com.onlinemarket.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinemarket.MainController;
import com.onlinemarket.Entities.Brand;
import com.onlinemarket.Entities.Buyer;
import com.onlinemarket.Entities.Product;
import com.onlinemarket.Entities.StoreProductHistory;
import com.onlinemarket.Entities.User;
import com.onlinemarket.Entities.storeOwner;
import com.onlinemarket.Repository.BuyerRepository;
import com.onlinemarket.Repository.ProductRepository;
import com.onlinemarket.Repository.StoreRepository;
import com.onlinemarket.Repository.UserRepository;
import com.onlinemarket.Repository.brandRepository;
import com.onlinemarket.Repository.historyRepository;

@Controller
public class productController {
	@Autowired
	private ProductRepository repo;
	@Autowired
	private brandRepository brandrepo;
	@Autowired
	private BuyerRepository BuyerRepo;
	@Autowired
	private StoreRepository storeRepo;
	@Autowired 
	private UserRepository UserRepo;
	@Autowired 
	private historyRepository  historyRepo;
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
public String ShowProducts(Model model) {
		Iterable<Product> productIterable=repo.findAll();
		List<Product> productList=new ArrayList<Product>();
		Iterable<User> userIterable=UserRepo.findAll();
		List<User> userList=new ArrayList<User>();
		for(Product prod: productIterable) {
			productList.add(prod);
		}

		/*for(User us: userIterable)
		{
			userList.add(us);
		}
		int finalUserIndex = userList.size()-1;
		//System.out.println( "tttt " + userList.size());
		User user= userList.get(finalUserIndex);
		int productIndex = productList.size()-1;
		Product product = productList.get(productIndex);
		model.addAttribute("productData", product);
		model.addAttribute("userData", user);*/

		model.addAttribute("product", new Product());
		model.addAttribute("products",productList);
		
		
		return "addProduct";
	}

	//@GetMapping("/addProduct")
//	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
//	public String ShowProducts(Model model) {
//		Iterable<Product> productIterable=repo.findAll();
//		List<Product> productList=new ArrayList<Product>();
//		for(Product prod: productIterable) {
//			productList.add(prod);
//		}
//		model.addAttribute("product", new Product());
//		model.addAttribute("products",productList);
//		
//		
//		return "addProduct";
//	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(Model model,@ModelAttribute Product product) {
	
		if(!product.getName().isEmpty()&&!product.getBrandName().isEmpty()&&!product.getCategory().isEmpty()&&!product.getPrice().isEmpty()) {
			repo.save(product);
			model.addAttribute("product", new Product());
		}
		
		Iterable<Product> productIterable=repo.findAll();
		List<Product> productList=new ArrayList<Product>();
		for(Product prod: productIterable) {
			productList.add(prod);
		}
		model.addAttribute("products",productList);
		model.addAttribute("product", new Product());
		return "addProduct";
	}
	
	@GetMapping("/addBrand")
	public String ShowBrands(Model model) {
		Iterable<Brand> brandIterable=brandrepo.findAll();
		List<Brand> brandList=new ArrayList<Brand>();
		for(Brand B: brandIterable) {
			brandList.add(B);
		}
		model.addAttribute("brands",brandList);
		model.addAttribute("brand", new Brand());
		
		return "addBrand";
	}
	
	@RequestMapping(value = "/addBrand", method = RequestMethod.POST)
	public String addBrand(Model model,@ModelAttribute Brand brand) {
	
		if(!brand.getBrandName().isEmpty()&&!brand.getBrandCategory().isEmpty()) {
			brandrepo.save(brand);
			model.addAttribute("brand", new Brand());
		}
		
		Iterable<Brand> productIterable=brandrepo.findAll();
		List<Brand> productList=new ArrayList<Brand>();
		for(Brand prod: productIterable) {
			productList.add(prod);
		}
		model.addAttribute("brands",productList);
		return "addBrand";
	}
	@RequestMapping(value="/productdetails/get/{id}",method = RequestMethod.GET)
	public String buyerProduct(Model model,@PathVariable("id") Integer id) {
		Product product = repo.findOne(id);
		product.setCounter(product.getCounter()+1);
		repo.save(product);
		model.addAttribute("product",product);
		model.addAttribute("Buyer",new Buyer());
		 System.out.println("-->>"+id);
		//model.addAttribute("storeOwner", new storeOwner());
		return "productdetails";
	}
	
	@RequestMapping(value = "/productdetails/get/{id}", method = RequestMethod.POST)
	public String buyProduct(Model model,@PathVariable("id") Integer id,HttpServletRequest session,@ModelAttribute Buyer buyer) {
		MainController.getSessions(model, session);
		Product product = repo.findOne(id);
		
		Buyer temp=new Buyer();
		Integer ID=(Integer) session.getSession().getAttribute("id");
		temp.setIdBuyer(ID);
		temp.setIdProduct(id);
		
		if(!buyer.getAddress().isEmpty()&&buyer.getAmounts()<=product.getNumberOfProducts())
		{
			temp.setAddress(buyer.getAddress());
			temp.setAmounts(buyer.getAmounts());
			BuyerRepo.save(temp);
			return "ShowAllProduct";
		}
		System.out.println("-->>"+id+"->>>"+ID);
		return "productdetails";
	}
	@RequestMapping(value="/productDetailsStoreOwner/get/{id}",method = RequestMethod.GET)
	public String getProductStoreOwner(Model model,@PathVariable("id") Integer id,HttpServletRequest session) {
		Product product = repo.findOne(id);
		product.setCounter(product.getCounter()+1);
		repo.save(product);
		model.addAttribute("product",product);
		model.addAttribute("Store",new storeOwner());
		 System.out.println("-->>"+id);
		//model.addAttribute("storeOwner", new storeOwner());
		return "productDetailsStoreOwner";
	}
	
	@RequestMapping(value = "/productDetailsStoreOwner/get/{id}", method = RequestMethod.POST)
	public String getProduct(Model model,@PathVariable("id") Integer id,HttpServletRequest session,@ModelAttribute storeOwner store) {
		System.out.println("============getProduct============");
		Product product = repo.findOne(id);
		MainController.getSessions(model, session);
		String type=(String) session.getSession().getAttribute("type");
		Integer ID=(Integer)session.getSession().getAttribute("id");
		if(type.equals("storeowner"))
		{
			ID=(Integer)session.getSession().getAttribute("id");
		}
		else if(type.equals("Collaborator"))
		{
			ID=(Integer)session.getSession().getAttribute("idOwner");
		}
	
		
		
		storeOwner temp=new storeOwner();
		temp.setIdOwner(ID);
		temp.setIdProduct(id);
		
		StoreProductHistory history = new StoreProductHistory();
		
		if(store.getQuantity()<=product.getNumberOfProducts())
		{
			
			temp.setQuantity(store.getQuantity());
			
			history.setProductId(id);
			history.setStoreOwnerId(ID);
			history.setPreviousAmount(product.getNumberOfProducts());
			history.setNextAmount(temp.getQuantity());
			history.setAction("add");
			historyRepo.save(history);
			int updateQuantity=product.getNumberOfProducts()-store.getQuantity();
			product.setNumberOfProducts(updateQuantity);
			repo.save(product);
			int prodHistID = history.getId();
			temp.setProdHistoryID(prodHistID);
			storeRepo.save(temp);
			return "/productDetailsStoreOwner";
		}
		System.out.println("-->>"+id+"->>>"+ID);
		return "/productDetailsStoreOwner";
	}
	
	
	@RequestMapping(value="/updateProduct/update/{id}",method = RequestMethod.GET)
	public String updateProductStoreOwner(Model model,@PathVariable("id") Integer id,HttpServletRequest session) {
		Product product = repo.findOne(id);
		
		MainController.getSessions(model, session);
		String type=(String) session.getSession().getAttribute("type");
		Integer ID=(Integer)session.getSession().getAttribute("id");
		if(type.equals("storeowner"))
		{
			ID=(Integer)session.getSession().getAttribute("id");
		}
		else if(type.equals("Collaborator"))
		{
			ID=(Integer)session.getSession().getAttribute("idOwner");
		}

		
		Iterable<storeOwner>storeOwnerIterable=storeRepo.findAll();
		List<storeOwner> storeOwnerList=new ArrayList<storeOwner>();
		for(storeOwner owner: storeOwnerIterable) {
			if(owner.getIdOwner()==ID&&owner.getIdProduct()==id)
			{
				//product.setNumberOfProducts(owner.getQuantity());
				break;
			}
		}
		model.addAttribute("product",product);
		model.addAttribute("Store",new storeOwner());
//		 System.out.println("-->>"+id);
		//model.addAttribute("storeOwner", new storeOwner());
		return "updateProduct";
	}
	
	@RequestMapping(value = "/updateProduct/update/{id}", method = RequestMethod.POST)
	public String updateProductStoreOwner(Model model,@PathVariable("id") Integer id,HttpServletRequest session,@ModelAttribute storeOwner store) {
		System.out.println("============Update============");
		Product product = repo.findOne(id);
		
		storeOwner temp=new storeOwner();
		temp.setQuantity(store.getQuantity());
		MainController.getSessions(model, session);
		String type=(String) session.getSession().getAttribute("type");
		Integer ID=(Integer)session.getSession().getAttribute("id");
		if(type.equals("storeowner"))
		{
			ID=(Integer)session.getSession().getAttribute("id");
		}
		else if(type.equals("Collaborator"))
		{
			ID=(Integer)session.getSession().getAttribute("idOwner");
		}
		
////////////////////**********/////////here
		int index=0;
		Iterable<storeOwner>storeOwnerIterable=storeRepo.findAll();
		List<storeOwner> storeOwnerList=new ArrayList<storeOwner>();
		for(storeOwner owner: storeOwnerIterable)
		{
			if(owner.getIdOwner()==ID&&owner.getIdProduct()==id)
			{
				index=owner.getId();
				break;
			}
		}
		
		storeOwner oldOne = storeRepo.findOne(index);
		
		int neededQuantity = temp.getQuantity() - oldOne.getQuantity();
		
		if(neededQuantity <= product.getNumberOfProducts())
		{
			product.setNumberOfProducts(product.getNumberOfProducts() - neededQuantity);
			
			//here, add a row in history
			StoreProductHistory historyProduct = new StoreProductHistory();
			historyProduct.setAction("update");
			historyProduct.setNextAmount(temp.getQuantity());//new quantity
			historyProduct.setPreviousAmount(oldOne.getQuantity()); // old quantity
			historyProduct.setProductId(id);
			historyProduct.setStoreOwnerId(ID);
			historyRepo.save(historyProduct);
			
			oldOne.setQuantity(temp.getQuantity());//update quantity in store owner table
			storeRepo.save(oldOne);
	//////////////////////****************///////////
			
			//product.setNumberOfProducts(store.getQuantity());
			model.addAttribute("product",product);
			model.addAttribute("Store",new storeOwner());
			//System.out.println(store.getQuantity());
			return "updateProduct";
		}
		model.addAttribute("product",product);
		model.addAttribute("Store",new storeOwner());
		System.out.println("-->>"+id+"->>>"+ID);
		return "updateProduct";
	}
	
	
	@RequestMapping(value="/deleteProduct/delete/{id}",method = RequestMethod.GET)
	public String deleteProductStoreOwner(Model model,@PathVariable("id") Integer id,HttpServletRequest session) {
		System.out.println("============delete_GET============");
		Product product = repo.findOne(id);
		
		MainController.getSessions(model, session);
		String type=(String) session.getSession().getAttribute("type");
		Integer ID=(Integer)session.getSession().getAttribute("id");
		if(type.equals("storeowner"))
		{
			ID=(Integer)session.getSession().getAttribute("id");
		}
		else if(type.equals("Collaborator"))
		{
			ID=(Integer)session.getSession().getAttribute("idOwner");
		}
		
		
		StoreProductHistory history = new StoreProductHistory();
		int index=0;
		Iterable<storeOwner>storeOwnerIterable=storeRepo.findAll();
		List<storeOwner> storeOwnerList=new ArrayList<storeOwner>();
		for(storeOwner owner: storeOwnerIterable) {
			if(owner.getIdOwner()==ID&&owner.getIdProduct()==id)
			{
				int newQuantity=product.getNumberOfProducts()+owner.getQuantity();
				product.setNumberOfProducts(newQuantity);
				repo.save(product);
				
				history.setAction("delete");
				history.setProductId(id);
				history.setStoreOwnerId(ID);
				history.setPreviousAmount(owner.getQuantity());
				history.setNextAmount(0);//not used
				historyRepo.save(history);
				
				//product.setNumberOfProducts(owner.getQuantity());
				index=owner.getId();
				break;
			}
		}
		//////////////******//////////////here
		storeOwner storeO=new storeOwner();
		storeO=storeRepo.findOne(index);
		storeO.setQuantity(0);
		storeRepo.save(storeO);
		/////////////************/////////
		//////////////////////////////////////////////////////////////////////	
		storeOwnerIterable=storeRepo.findAll();
		List<Product> productList=new ArrayList<Product>();
		
		for(storeOwner row: storeOwnerIterable) {
			if(row.getIdOwner()==ID) {
				//ownerList.add(row);
				productList.add(repo.findOne(row.getIdProduct()));
			}
		}
		model.addAttribute("products",productList);
		//////////////////////////////////////////////////////////////////////////
		//System.out.println(store.getQuantity());
		return "/StoreOwnerProduct";
	}
	
//	@RequestMapping(value = "/deleteProduct/delete/{id}", method = RequestMethod.POST)
//	public String deleteProductStoreOwner(Model model,@PathVariable("id") Integer id,HttpServletRequest session,@ModelAttribute storeOwner store) {
//		System.out.println("============delete_POST============");
//		Product product = repo.findOne(id);
//		MainController.getSessions(model, session);
//		String type=(String) session.getSession().getAttribute("type");
//		Integer ID=(Integer)session.getSession().getAttribute("id");
//		if(type.equals("storeowner"))
//		{
//			ID=(Integer)session.getSession().getAttribute("id");
//		}
//		else if(type.equals("Collaborator"))
//		{
//			ID=(Integer)session.getSession().getAttribute("idOwner");
//		}
//		if(store.getQuantity()<=product.getNumberOfProducts())
//		{
//			int index=0;
//			Iterable<storeOwner>storeOwnerIterable=storeRepo.findAll();
//			List<storeOwner> storeOwnerList=new ArrayList<storeOwner>();
//			for(storeOwner owner: storeOwnerIterable) {
//				if(owner.getIdOwner()==ID&&owner.getIdProduct()==id)
//				{
//					int newQuantity=product.getNumberOfProducts()+owner.getQuantity();
//					product.setNumberOfProducts(newQuantity);
//					repo.save(product);
//					product.setNumberOfProducts(owner.getQuantity());
//					index=owner.getId();
//					break;
//				}
//			}
//			store=storeRepo.findOne(index);
//			storeRepo.delete(store);
//			product.setNumberOfProducts(store.getQuantity());
//			model.addAttribute("product",product);
//			model.addAttribute("Store",new storeOwner());
//			//System.out.println(store.getQuantity());
//			return "/StoreOwnerProduct";
//		}
//		return "/StoreOwnerProduct";
//	}
	
//	private StoreRepository Storerepo;
//	@GetMapping("/ShowAllProduct2")
//	public String ShowProductsOWNER(Model model) {
//		model.addAttribute("product", new storeOwner());
//		return "ShowAllProduct2";
//	}
//	
//	@RequestMapping(value = "/ShowAllProduct2", method = RequestMethod.POST)
//	public String addProductOWNER(Model model,@ModelAttribute storeOwner product) {
//		System.out.println(">>>>>>>>>>>"+product.getIdProduct());
//		if(product.getIdProduct()!=-1) {
//			Storerepo.save(product);
//			model.addAttribute("product", new storeOwner());
//			
//		}
//		
//		return "ShowAllProduct2";
//	}
//	@RequestMapping(value="/showAllProducts2", params={"submit"})
//	public String removeRow(
//	        final storeOwner storeOwner, final BindingResult bindingResult, 
//	        final HttpServletRequest req) {
//	    System.out.println(">>>>>>>>>>>>>>>>>");
//	    return "ShowAllProducts2";
//	}
//	@GetMapping("/showAllProducts2")
//	public String PrintHello(Model model ,@ModelAttribute Product product){
//		//System.out.println(product.getId());
//	   // model.setViewName("showAllProducts2");
//		//model.addAttribute("product", new Product());
//		model.addAttribute("product", new Product()); //assume SomeBean has a property called datePlanted
//	    return "showAllProducts2";
//	}
//	@RequestMapping(value = "/showAllProducts2", method = RequestMethod.POST)
//	public String postPrintHello(Model model ,@ModelAttribute Product product){
//
//        System.out.println("name:" + product.getName()); 
//	   // model.setViewName("StoreOwnerProduct");
//	    return "StoreOwnerProduct";
//	}
}
	
	
	

