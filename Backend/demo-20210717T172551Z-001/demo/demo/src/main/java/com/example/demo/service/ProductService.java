package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;
import java.sql.Date ;
import java.text.SimpleDateFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.product;
import com.example.demo.repos.ProduitRepository;

@Service
public class ProductService  {
    @Autowired
    private final ProduitRepository productRepository ;

    @Autowired
    ProductService(ProduitRepository productRepository){
        this.productRepository=productRepository ;
    }

    public void addProduct(product product){
        var timestamp = new Timestamp(System.currentTimeMillis());
        product.setDate_creation(timestamp);
        product.setDate_modif(null);
        System.out.println((product.getCategory().getNom()));
        productRepository.save(product);
    }
    public void updateProduct(product product){

        var timestamp = new Timestamp(System.currentTimeMillis());
        product.setDate_modif(timestamp);
        productRepository.save(product);
    }
}








/*
    public void  saveProductToDB(String nom, int quantite)
    {
        Date date_creation;
        Date  date_modif = null;
        boolean disponble = false;

        product p = new product();
        if( quantite != 0){
            disponble = true;
        }
        p.setDate_creation( new Timestamp(System.currentTimeMillis()));

        p.setDate_modif(date_modif) ;
        p.setNom(nom);
        p.setQuantite(quantite);

        productRepo.save(p);
    }
    public List<product> getAllProduct()
    {
        return productRepo.findAll();
    }
    public void deleteProductById(int id_prod)
    {
        productRepo.deleteById((long) id_prod);
    }
    public void editProduct(int id_prod ,String nom, int quantite)
    {   boolean dip;

        product p = new product();
        p = productRepo.findById((long) id_prod).get();
        p.setNom(nom);
        p.setQuantite(quantite);
        p.setDate_modif(new Timestamp(System.currentTimeMillis()));
        if ( quantite == 0){
            dip = false;
            p.setDisponble(dip);
        }
        productRepo.save(p);
    }

*/
