package com.example.productmanagementthymeleaf.service;

import com.example.productmanagementthymeleaf.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{
    private static final Map<Integer, Product> products;

    static {
        products = new HashMap<Integer, Product>();
        products.put(1, new Product(1, "milk", 20000, "good for health", "Vinamilk"));
        products.put(2, new Product(2, "meat", 50000, "good for health", "Farm"));
        products.put(3, new Product(3, "pork", 40000, "good for health", "VN"));
        products.put(4, new Product(4, "orange", 20000, "fresh fruit", "VN"));
        products.put(5, new Product(5, "chicken", 20000, "good for health", "McDonalds"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public ArrayList<Product> findByKeyword(String keyword) {
        ArrayList<Product> results = new ArrayList<Product>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(product);
            }
        }
        return results;
    }

}
