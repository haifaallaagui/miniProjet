import { Component, OnInit } from '@angular/core';
import { ProduitService } from '../services/produit.service';
import {Router} from "@angular/router";
import {Category} from "../model/Category.model";
import { Product } from '../model/product.model';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent implements OnInit {


  produits!: Product[];
  activatedRoute: any;

  constructor(private produitService: ProduitService, private router: Router) {
   
  }

  ngOnInit(): void {
    this.produitService.listeProduit().subscribe((prods) => {
      console.log(prods);
      this.produits = prods;
    });
  }
  supprimerProduit(p: Product) {
    let conf=confirm("Etes-vous de supprimer cet element?")
    if (conf)
      if (p.id_prod!)
        this.produitService.supprimerProduit(p.id_prod).subscribe(() => {
          console.log('produit supprimé');
        });
    this.router.navigate(['produits']).then(() => {
      window.location.reload();
    });
  }
  exportPdfProducts() {
    this.produitService
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
    this.produitService
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

/*
  products:any;
  size:number=5;
  currentPage:number=0
  totalPages:number=0
  pages:Array<number>;
  categories: any;
  category:Category
  productService: any;
  activatedRoute: any;
  constructor(private service:CatalogueService, private router:Router) { }

  ngOnInit() {

    this.service.findAllCategory().subscribe(data =>{
      this.categories=data ;
    },error=>{
      console.log(error);
    })

  }
  onGetProducts() {
    this.service.getProducts(this.currentPage, this.size).subscribe(data =>{
            this.totalPages=data["page"].totalPages;
            this.pages= new Array<number>(this.totalPages);
            this.products=data ;
     },error=>{
      console.log(error);
    })
  }

  onTapeKeyword (value){
    this.service.doSearchByKeyWord(value.keyword,this.currentPage,this.size).subscribe(data =>{
      this.products=data ;
    },error=>{
      console.log(error);
    })
  }

  onProductPage(i: number) {
    this.currentPage=i;
    this.onGetProducts()
  }

  onDeleteProduct(p) {
    let conf=confirm("Etes-vous de supprimer cet element?")
    if (conf){
      this.service.deleteResource(p._links.self.href).subscribe( success=>{
        this.onGetProducts();
      },erro=>{
        console.log(erro)
      });

    }
  }

  onChangeSelect(value) {
    this.service.findAllByCategory(value.id,this.currentPage,this.size).subscribe(data =>{
      this.products=data ;
    },error=>{
      console.log(error);
    })
  }


  getCategory(href:string){
    return this.service.getCategoryOfProduct(href).subscribe(c =>{
      this.category=c
      console.log(this.category);
      return "ok";
    });
  }

  onEditProduct(p) {
    let url =  p._links.self.href;
    return this.router.navigateByUrl("/edit-product/"+ btoa(url));
  }
  */
  

}
