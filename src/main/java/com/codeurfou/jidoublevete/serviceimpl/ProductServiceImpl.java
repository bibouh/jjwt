package com.codeurfou.jidoublevete.serviceimpl;

import com.codeurfou.jidoublevete.entity.Products;
import com.codeurfou.jidoublevete.repository.ProductRepo;
import com.codeurfou.jidoublevete.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Override
    public List<Products> getProduct() {
        return productRepo.findAll();
    }
}
