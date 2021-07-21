package com.example.demo.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Category;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
