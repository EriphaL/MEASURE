package com.nbst.service;
import com.nbst.model.Product;

public interface IProductService {
	//新增产品
	Object addProduct(Product product);	
	//更新产品
	Object updateProduct(Product product);
	//删除产品
	Object deleteProduct(Integer productId);
	//查询所有产品
	Object findAllProduct();
}
