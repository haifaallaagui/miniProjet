package com.example.demo.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import com.example.demo.excption.ResourceNotFoundException;
import com.example.demo.models.product;
import com.example.demo.repos.CategoryRepository;
import com.example.demo.service.ExportProductService;
import com.example.demo.service.ProductService;
import com.example.demo.models.Category;
import com.example.demo.repos.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
class ProductController {


    private final ProduitRepository productRepository;
    private final  ProductService productService;
    private final CategoryRepository categoryRepository;


    @Autowired
    ProductController(ProductService productService, CategoryRepository categoryRepository, ProduitRepository productRepository)
    {
        this.productService = productService;
        this.categoryRepository=categoryRepository ;
        this.productRepository = productRepository ;
    }



    @CrossOrigin(origins = "http://localhost:8090")
    @PostMapping("/AddProduct/{catid}")
    public void addProduct(@RequestBody product product, @PathVariable("catid") long catitd) {
        Category cat = categoryRepository.findById(catitd).orElseThrow(() -> new ResourceNotFoundException("category not found with id :" + catitd));
        product.setCategory(cat);

       // System.out.println(product.getCategory().getNom());
        if(!product.isDisponble()){
            product.setQuantite(0);
        }
        productService.addProduct(product);

    }

    @GetMapping("/showproducts")
    public List<product> showProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/findbycatid/{catid}")
    public List<product> showProductsInCat(@PathVariable("catid") long catid) {

        return productRepository.showProductsInCat(catid);
    }

    @GetMapping("/findproduct/{id}")
    public product showProduct(@PathVariable("id") Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
    }

    @DeleteMapping("/deleteproduct/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
    }


    @PutMapping("/updateproduct/{id}")
    public void updateProduct(@RequestBody product product, @PathVariable("id") long id) {
        product prod = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product not found with id :" + id));
        prod.setDate_creation(prod.getDate_creation());
        prod.setDisponble(product.isDisponble());
        prod.setQuantite(product.getQuantite());
        prod.setNom(product.getNom());
        productService.updateProduct(prod);

    }

    @GetMapping("/exportpdf/{catid}")
    public ResponseEntity<InputStreamResource> exportTermsPdf(@PathVariable("catid") long catid) {
        List<product> products = productRepository.showProductsInCat(catid);
        ByteArrayInputStream bais = ExportProductService.productsPDFExport(products);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    @GetMapping("/exportexcel/{catid}")
    public ResponseEntity<InputStreamResource> exportTermsExcel(@PathVariable("catid") long catid) throws IOException {
        List<product> products = productRepository.showProductsInCat(catid);
        ByteArrayInputStream bais = ExportProductService.productsExcelExport(products);
        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=products.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
    }

}

    /*


    @Autowired
    private ProductService productService;

    @GetMapping("/Products.html")
    public String showExampleView(Model model)
    {
        List<product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "/Products.html";
    }
    @GetMapping("/")
    public String showAddProduct()
    {

        return "/addProduct.html";
    }

    @PostMapping("/addP")
    public String saveProduct(@RequestParam("pname") String nom,
                              @RequestParam("quantite") int quantite)
    {
        productService.saveProductToDB( nom, quantite);
        return "redirect:/Products.html";
    }

    @GetMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") int id)
    {

        productService.deleteProductById(id);
        return "redirect:/Products.html";
    }

    @PostMapping("/changeName")
    public String changeProduit(@RequestParam("id") int id, @RequestParam("newPname") String nom,@RequestParam("quantite") int  quantite)

    {
        productService.editProduct(id, nom,quantite);
        return "redirect:/listProducts.html";
    }



}
 */