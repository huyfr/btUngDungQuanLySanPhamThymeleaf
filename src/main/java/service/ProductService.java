package service;

import model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public boolean save(Product product);

    public Product findById(int id);

    public void update(int id, Product product);

    public void remove (int id);
}
