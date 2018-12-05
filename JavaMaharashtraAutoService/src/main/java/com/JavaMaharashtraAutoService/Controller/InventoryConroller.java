package com.JavaMaharashtraAutoService.Controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.JavaMaharashtraAutoService.Dao.InventoryDao;
import com.JavaMaharashtraAutoService.Model.Inventory;

@RestController

public class InventoryConroller {
	
	@RequestMapping(value = "/AddInventory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Output AddInventory(@RequestBody List<Inventory> inventory) {
		InventoryDao obj = new InventoryDao();
		Output objOutput = new Output();
		objOutput.setMessage(obj.AddInventory(inventory));
		return objOutput;
	}

	@RequestMapping(value = "/GetSelectedProd", method = RequestMethod.GET)
	public Inventory GetSelectedProd(String proId) {
		InventoryDao obj = new InventoryDao();
		return obj.GetSelectedProd(proId);
	}
}
