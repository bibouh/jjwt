package com.codeurfou.jidoublevete.repository;

import com.codeurfou.jidoublevete.entity.Products;
import com.codeurfou.jidoublevete.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products,Long> {
}
