package com.example.demo.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.product;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<product, Long> {

    @Query(value = "SELECT * from product Where category_id=:idb ", nativeQuery = true)
    List<product> showProductsInCat(@Param("idb") long idb);


}

