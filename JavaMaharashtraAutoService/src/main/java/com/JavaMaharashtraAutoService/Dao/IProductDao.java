package com.JavaMaharashtraAutoService.Dao;

import java.util.List;

import com.JavaMaharashtraAutoService.Model.Products;

public interface IProductDao 
{
	String AddProduct(Products prod);
	String UpdateProduct(Products prod);
	String DeleteProduct();
	List<Products> GetProducts();

}
