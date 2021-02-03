package com.example.mapper;


import com.example.DemoApplication;
import com.example.entity.Product;
import com.example.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ProductMapperTest {


    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductService productService;


    @Test
    public void insertProductTest(){
        Product product = Product.builder().productName("phone").productNum(1).build();
        productMapper.insert(product);
    }


    @Test
    public void getAllProduct(){
        Product product = Product.builder().productName("phone").productNum(1).build();
        productService.save(product);
    }

}
