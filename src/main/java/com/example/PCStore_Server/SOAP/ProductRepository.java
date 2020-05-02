package com.example.PCStore_Server.SOAP;

import io.spring.guides.gs_producing_web_service.Product;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository {
    private static final Map<Integer, Product> products = new HashMap<>();

    @PostConstruct
    public void initData() {
        Product headphones = new Product();
        headphones.setIdProduct(1);
        headphones.setName("Наушники");
        headphones.setPrice(600);


        products.put(headphones.getIdProduct(), headphones);

        Product laptop = new Product();
        laptop.setIdProduct(2);
        laptop.setName("Ноутбук");
        laptop.setPrice(15000);



        products.put(laptop.getIdProduct(), laptop);

        Product monitor = new Product();
        monitor.setIdProduct(3);
        monitor.setName("Монитор");
        monitor.setPrice(7000);


        products.put(monitor.getIdProduct(), monitor);


    }

    public Product findProduct(String name) {
        Assert.notNull(name, "The Product's name must not be null");
        for (Product product:products.values()
        ) {
            if(product.getName().toLowerCase().equals(name.toLowerCase()))
            {
                return product;
            }
        }

        return  null;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        for (Product product:products.values()
        ) {
            productList.add(product);
        }
        return productList;
    }

    public void addProduct(int id_product, String name, int price)
    {
        Product newProduct = new Product();
        newProduct.setIdProduct(id_product);
        newProduct.setName(name);
        newProduct.setPrice(price);

        products.put(newProduct.getIdProduct(),newProduct);
    }

    public void editProduct(String oldName,String newName, int newPrice)
    {
        for (Product productObj:products.values()
        ) {
            if(productObj.getName().toLowerCase().equals(oldName.toLowerCase()))
            {
                productObj.setName(newName) ;
                productObj.setPrice(newPrice);

                products.put(productObj.getIdProduct(), productObj);
            }
        }


    }

    public String deleteProduct(int id_product)
    {


        if (products.containsKey(id_product))
        {
            products.remove(id_product);
            return "Product "+id_product+" deleted";
        }
        return  "Product "+ id_product+" does not exists";

    }
}
