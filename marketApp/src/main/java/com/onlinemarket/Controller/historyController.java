
package com.onlinemarket.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlinemarket.MainController;
import com.onlinemarket.Entities.Product;
import com.onlinemarket.Entities.StoreProductHistory;
import com.onlinemarket.Entities.User;
import com.onlinemarket.Entities.storeOwner;
import com.onlinemarket.Repository.ProductRepository;
import com.onlinemarket.Repository.StoreRepository;
import com.onlinemarket.Repository.UserRepository;
import com.onlinemarket.Repository.historyRepository;

@Controller
public class historyController {

	@Autowired
	private historyRepository historyRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private StoreRepository storeOwnerRepo;
	
	
	@GetMapping("/productHistory")
	public String productPage(Model model)
	{
		Iterable<StoreProductHistory> productIterable=historyRepo.findAll();
		List<StoreProductHistory> productList=new ArrayList<StoreProductHistory>();
		for(StoreProductHistory prod: productIterable) {
			productList.add(prod);
		}
		model.addAttribute("history",productList);
		return "/productHistory";
	} 
	
	
	
	@RequestMapping(value="/productHistory/get/{id}",method = RequestMethod.GET)
	public String ShowAllProductsGetAdd(Model model,@PathVariable("id") Integer id, HttpServletRequest session) {
		StoreProductHistory prod = historyRepo.findOne(id);
		
		MainController.getSessions(model, session);
		Integer ID=(Integer)session.getSession().getAttribute("id");
		
		int index=0;
		Iterable<storeOwner>storeOwnerIterable=storeOwnerRepo.findAll();
		List<storeOwner> storeOwnerList=new ArrayList<storeOwner>();
		for(storeOwner owner: storeOwnerIterable)
		{
			if(owner.getIdOwner()==ID&&owner.getIdProduct()== prod.getProductId())
			{
				index=owner.getId();
				break;
			}
		}
		storeOwner oldOne = storeOwnerRepo.findOne(index);

		if(prod.getAction().equals("add"))
		{
			Product product = productRepo.findOne(prod.getProductId());
			product.setNumberOfProducts(prod.getPreviousAmount());
			productRepo.save(product);
			
			storeOwner storeowner = new storeOwner() ;
			Iterable<storeOwner> storeOwnerIterable2=storeOwnerRepo.findAll();
			for(storeOwner owner: storeOwnerIterable2) {
				
				if(owner.getProdHistoryID().equals(id))
				{
					storeowner = owner;
					break;
				}
			}
						
			storeOwnerRepo.delete(storeowner.getId());
			historyRepo.delete(prod.getId()); 
			
			
		}
		else if (prod.getAction().equals("delete"))
		{
			Product product = productRepo.findOne(prod.getProductId());//
			product.setNumberOfProducts(product.getNumberOfProducts()-prod.getPreviousAmount());//
			productRepo.save(product);//
			
			oldOne.setIdOwner(prod.getStoreOwnerId());
			oldOne.setIdProduct(prod.getProductId());
			//oldOne.setProdHistoryID(prod.getId());
			oldOne.setQuantity(prod.getPreviousAmount());
			
			storeOwnerRepo.save(oldOne);
								
			historyRepo.delete(prod.getId()); //
		}
		else if (prod.getAction().equals("update"))
		{
			Product product = productRepo.findOne(prod.getProductId());
			product.setNumberOfProducts(product.getNumberOfProducts() + (prod.getNextAmount() - prod.getPreviousAmount()));
			productRepo.save(product);
			
			oldOne.setIdOwner(prod.getStoreOwnerId());
			oldOne.setIdProduct(prod.getProductId());
			//oldOne.setProdHistoryID(prod.getId());
			oldOne.setQuantity(prod.getPreviousAmount());
			
			storeOwnerRepo.save(oldOne);
								
			historyRepo.delete(prod.getId()); //
		}
		
		Iterable<StoreProductHistory> productIterable=historyRepo.findAll();
		List<StoreProductHistory> productList=new ArrayList<StoreProductHistory>();
		for(StoreProductHistory prod2: productIterable) {
			productList.add(prod2);
		}
		model.addAttribute("history",productList);
		
		return "/productHistory";
		
	}
	
//	
//	@RequestMapping(value="/productHistor/get/{id}",method = RequestMethod.GET)
//	public String ShowAllProductsGetDelete(Model model,@PathVariable("id") Integer id) {
//		StoreProductHistory prod = historyRepo.findOne(id);//
//		Product product = productRepo.findOne(prod.getProductId());//
//		product.setNumberOfProducts(product.getNumberOfProducts()-prod.getPreviousAmount());//
//		productRepo.save(product);//
//		
//		storeOwner storeowner = new storeOwner() ;
//		storeowner.setIdOwner(prod.getStoreOwnerId());
//		storeowner.setIdProduct(prod.getProductId());
//		storeowner.setProdHistoryID(prod.getId());
//		storeowner.setQuantity(prod.getPreviousAmount());
//		
//		storeOwnerRepo.save(storeowner);
//							
//		historyRepo.delete(prod.getId()); //
//		
//		return "/productHistory";
//	}
	/*@RequestMapping(value="/productHistory/get/{id}",method = RequestMethod.POST)
	public String ShowAllProducts(Model model,@PathVariable("id") Integer id, HttpServletRequest session) {
		System.out.println("testing2 ");
		StoreProductHistory prod = historyRepo.findOne(id);
		//System.out.println("get next amount "+ prod.getProductId()); 
		Product product = productRepo.findOne(prod.getProductId());
		System.out.println("product = " + product.getName() );
		
		storeOwner storeowner = storeOwnerRepo.findOne(prod.getStoreOwnerId());
		System.out.println("get previous amount = "+prod.getPreviousAmount());
		product.setNumberOfProducts(prod.getPreviousAmount());
		productRepo.save(product);
		
		Iterable<StoreProductHistory> productIterable=historyRepo.findAll();
		List<StoreProductHistory> productList=new ArrayList<StoreProductHistory>();
		for(StoreProductHistory prod2: productIterable) {
			productList.add(prod2);
		}
		model.addAttribute("history",productList);
		storeOwnerRepo.delete(storeowner.getId());
		historyRepo.delete(prod.getId()); 
		
		return "productHistory";
	}
	*/
}