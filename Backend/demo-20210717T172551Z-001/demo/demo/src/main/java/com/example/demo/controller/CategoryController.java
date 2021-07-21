package com.example.demo.controller;

import com.example.demo.excption.ResourceNotFoundException;
import com.example.demo.models.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.repos.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")

public class CategoryController {

    private final CategoryService categoryService ;

    private final CategoryRepository categoryRepository ;


    @Autowired
    public CategoryController(CategoryService categoryService,CategoryRepository categoryRepository){
        this.categoryService=categoryService ;
        this.categoryRepository=categoryRepository ;
    }

    @PostMapping("/AddCategory")
    public void addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }

    @GetMapping("/showcategories")
    public List<Category> showCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/findcategory/{id}")
    public Optional<Category> showCategory(@PathVariable("id") Long id){
        var oui= categoryRepository.findById(id).isPresent() ;

        System.out.println(oui);
        if (oui)
        {
            return categoryRepository.findById(id);
        }
        return Optional.empty();
    }

    @DeleteMapping("/deletecategory/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryRepository.deleteById(id);
    }

    @PutMapping("/updatecategory/{id}")
    public void updateCategory(@RequestBody Category category, @PathVariable("id")long id){
        Category category1 = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product not found with id :" + id));

        category1.setDate_creation(category1.getDate_creation());
        category1.setQt(category.getQt());
        category1.setNom(category.getNom());

        categoryService.updateCategory(category1);
    }



















/*
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/Categorys.html")
    public String showExampleView(Model model)
    {
        List<Category> categorys = categoryService.getAllCategory();
        model.addAttribute("categorys", categorys);
        return "/Categorys.html";
    }
    @GetMapping("/")
    public String showAddCategory()
    {

        return "/addCategory.html";
    }

    @PostMapping("/addC")
    public String saveCategory(
                              @RequestParam("nom") String nom,
                              @RequestParam("qt") int qt)
    {
        categoryService.saveCategoryToDB( nom, qt);
        return "redirect:/categorys.html";
    }

    @GetMapping("/deleteCat/{id}")
    public String deleteCategory(@PathVariable("id") int id)
    {

        categoryService.deleteCategoryById(id);
        return "redirect:/Categorys.html";
    }

    @PostMapping("/edit_category")
    public String edit_category(@RequestParam("id") int id,
                              @RequestParam("newCname") String nom,@RequestParam("quantite") int qt)

    {

        categoryService.edit_category(id, nom,qt);
        return "redirect:/Categorys.html";
    }
*/


}
