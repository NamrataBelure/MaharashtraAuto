package com.JavaMaharashtraAutoService.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.JavaMaharashtraAutoService.Dao.ProductDao;
import com.JavaMaharashtraAutoService.Dao.Products;
import com.JavaMaharashtraAutoService.Model.ProductType;

@RestController
public class ProductsController {
	// Products Controller Start for AksProducts.html

	@RequestMapping(value = "/AddProduct", method = RequestMethod.POST, consumes = "multipart/form-data")
	public @ResponseBody void AddProduct(@ModelAttribute com.JavaMaharashtraAutoService.Model.Products pro,HttpServletResponse response) throws IOException {
		ProductDao obj = new ProductDao();
		obj.AddProduct(pro);
		response.sendRedirect("Inventory.html");
	}

	@CrossOrigin
	@RequestMapping(value = "/GetProductTypes", method = RequestMethod.GET)
	public List<ProductType> GetProductTypes() {
		Products obj = new Products();
		return obj.GetProductTypes();
	}

	@RequestMapping(value = "/AddProductTypes", method = RequestMethod.POST)
	
	public @ResponseBody String  AddProductTypes(@ModelAttribute("ProductType") ProductType prodType, HttpServletResponse response) throws IOException {

		Products obj = new Products();
		return obj.AddProductTypes(prodType);
		
	}

	
	@RequestMapping(value="/GetProducts",method=RequestMethod.GET)
	public List<com.JavaMaharashtraAutoService.Model.Products> GetProductList()
	{
		ProductDao obj=new ProductDao();
		return obj.GetProducts(); 
		
	}
	
}

class JsonResponse {
	private String jsonString;

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

}
