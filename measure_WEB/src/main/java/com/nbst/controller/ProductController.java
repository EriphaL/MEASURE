package com.nbst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbst.model.Product;
import com.nbst.service.IProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	/**
	 *       新增产品
	 * @param product
	 * @return
	 */
	@RequestMapping("/add.action")
	@ResponseBody
	public Object addProduct(Product product) {
		return productService.addProduct(product);
	}
	
	//更新
	@RequestMapping("/update.action")
	@ResponseBody
	public Object updateProduct(Product product) {
		
		return productService.updateProduct(product);
	}
	
	//删除
	@RequestMapping("/delete.action")
	@ResponseBody
	public Object deleteProduct(Integer productId) {
		
		return productService.deleteProduct(productId);
	}
	
}
