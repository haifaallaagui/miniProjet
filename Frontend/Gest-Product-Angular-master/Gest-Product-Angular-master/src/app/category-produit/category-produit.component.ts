import { Component, OnInit } from '@angular/core';
import  {Product} from '../model/product.model';
import { Category } from '../model/Category.model';
import { CategoryService } from '../services/category.service';
import { ProduitService } from '../services/produit.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-category-produit',
  templateUrl: './category-produit.component.html',
  styleUrls: ['./category-produit.component.css']
})
export class CategoryProduitComponent implements OnInit {

  produits!: Product[];
  category!: Category;
  newProduit = new Product();
  message!: string;

  constructor(
    private categoryService: CategoryService,
    private productService: ProduitService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    //this.produits = produitService.listeProduit();
  }
  ngOnInit(): void {
    localStorage.setItem('currentUser', this.activatedRoute.snapshot.params.id);
    const user = localStorage.getItem('currentUser');
    let x: number = +user!;
    console.log(x);
    this.categoryService
      .listeProduitInCat(this.activatedRoute.snapshot.params.id)
      .subscribe((prods) => {
        console.log(prods);
        this.produits = prods;
        console.log('prods');
      });
  }
  supprimerProduit(p: Product) {
    let conf=confirm("Etes-vous de supprimer cet element?")    
    if (conf)
      if (p.id_prod!)
        this.productService.supprimerProduit(p.id_prod).subscribe(() => {
          console.log('produit supprimé');
        });
    //this.router.navigate(['produits']).then(() => {
    window.location.reload();
  }

  exportPdfProducts() {
    this.productService
      .exportPdfProducts(this.activatedRoute.snapshot.params.id)
      .subscribe((x) => {
        const blob = new Blob([x], { type: 'application/pdf' });

        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
          window.navigator.msSaveOrOpenBlob(blob);
          return;
        }
        const data = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = data;
        link.download = 'products.pdf';
        link.dispatchEvent(
          new MouseEvent('click', {
            bubbles: true,
            cancelable: true,
            view: window,
          })
        );
        setTimeout(function () {
          window.URL.revokeObjectURL(data);
          link.remove();
        }, 100);
      });
  }

  exportExcelProducts() {
    this.productService
      .exportPdfProducts(this.activatedRoute.snapshot.params.id)
      .subscribe((x) => {
        const blob = new Blob([x], {
          type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;',
        });

        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
          window.navigator.msSaveOrOpenBlob(blob);
          return;
        }
        const data = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = data;
        link.download = 'products.xlsx';
        link.dispatchEvent(
          new MouseEvent('click', {
            bubbles: true,
            cancelable: true,
            view: window,
          })
        );
        setTimeout(function () {
          window.URL.revokeObjectURL(data);
          link.remove();
        }, 100);
      });
  }

}
