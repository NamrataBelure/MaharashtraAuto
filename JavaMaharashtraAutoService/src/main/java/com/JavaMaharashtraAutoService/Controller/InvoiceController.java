package com.JavaMaharashtraAutoService.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.JavaMaharashtraAutoService.Dao.InvoiceDao;
import com.JavaMaharashtraAutoService.Dao.ProductDao;
import com.JavaMaharashtraAutoService.Model.Invoice;
import com.JavaMaharashtraAutoService.Model.InvoiceProductDetails;
import com.JavaMaharashtraAutoService.Model.Products;

@RestController
public class InvoiceController {

	@RequestMapping(value = "/GetInvoiceData", method = RequestMethod.GET)
	public String GetInvoiceData() {

		InvoiceDao obj = new InvoiceDao();
		return obj.GetInvoiceData();
	}

	@RequestMapping(value = "/GetProductList", method = RequestMethod.GET)
	public List<Products> GetProducts() {
		ProductDao obj = new ProductDao();
		return obj.GetProducts();
	}

	@RequestMapping(value = "/GetSelectedProduct", method = RequestMethod.GET)
	public Products GetSelectedProduct(String proId) {
		ProductDao obj = new ProductDao();
		return obj.GetSelectedProduct(proId);
	}

	@RequestMapping(value = "/AddInvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Output AddInvoice(@RequestBody Invoice inv) {
		InvoiceDao obj = new InvoiceDao();
		Output objOutput=new Output();
		objOutput.setMessage(obj.AddInvoice(inv));
		return objOutput;
	}
	
	@RequestMapping(value = "/GetInvoice", method = RequestMethod.GET)
	public List<Invoice> GetInvoice(String startDate,String endDate) {
		InvoiceDao obj = new InvoiceDao();
		/*Date invstartDate=new Date();
		Date invendDate=new Date();
		try {
			invstartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			invendDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return obj.GetInvoice(startDate,endDate);
	}

	@RequestMapping(value = "/GetInvoiceProDetails", method = RequestMethod.GET)
	public List<InvoiceProductDetails> GetInvoiceProductDetails(String invNo) {
		InvoiceDao obj = new InvoiceDao();
		return obj.GetInvoiceProductDetails(invNo);
	}

	
	@RequestMapping(value = "/GetInvoiceByInvNo", method = RequestMethod.GET)
	public Invoice GetInvoiceByInvNo(String invNo) {
		InvoiceDao obj = new InvoiceDao();		
		return obj.GetInvoiceByInvNo(invNo);
	}

}

class Output
{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
