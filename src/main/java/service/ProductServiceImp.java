package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductServiceImp implements ProductService {

    private static HashMap<Integer, Product> productHashMap;

    static {
        productHashMap = new HashMap<>();
        productHashMap.put(1, new Product(1, "iphone 5", 15.23, "Cellphone"));
        productHashMap.put(2, new Product(2, "iphone 6", 16.23, "Cellphone"));
        productHashMap.put(3, new Product(3, "iphone 7", 17.23, "Cellphone"));
        productHashMap.put(4, new Product(4, "iphone 8", 18.23, "Cellphone"));
        productHashMap.put(5, new Product(5, "iphone 11", 20.23, "Cellphone"));
        productHashMap.put(6, new Product(6, "iphone 5s", 16.03, "Cellphone"));
        productHashMap.put(7, new Product(7, "iphone 6 plus", 17.03, "Cellphone"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList(productHashMap.values());
    }

    @Override
    public boolean save(Product product) {
        boolean flag;
        if(productHashMap.containsValue(product)){
            flag = false;
        }else{
            productHashMap.put(product.getId(), product);
            flag = true;
        }
        return flag;
    }

    @Override
    public Product findById(int id) {
        return productHashMap.get(id);
}

    @Override
    public void update(int id, Product product) {
        productHashMap.put(id, product);
    }

    @Override
    public void remove(int id) {
        productHashMap.remove(id);
    }
}
