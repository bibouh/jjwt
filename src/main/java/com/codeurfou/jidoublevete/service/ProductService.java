package com.codeurfou.jidoublevete.service;

import com.codeurfou.jidoublevete.entity.Products;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ProductService {
     List<Products> getProduct();
}
