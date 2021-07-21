import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../model/product.model";
import { ProduitService } from '../services/produit.service';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {
  newProduit = new Product();
  message!: string;

  constructor(
    private produitService: ProduitService,
    private categoryServie: CategoryService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {}

  addProduit() {
    const user = localStorage.getItem('currentUser');
    let x: number = +user!;
    console.log(x);
    
    if (this.newProduit.quantite != 0) {
      var boolValue = false;
      console.log(boolValue);
      this.newProduit.disponible = boolValue;
      this.produitService
        .ajouterProduit(this.newProduit, x)
        .subscribe((prod) => {
          console.log(prod);
        });
    } else {
      var boolValue = true;
      console.log(boolValue);
      this.newProduit.disponible = boolValue;
      this.produitService
        .ajouterProduit(this.newProduit, x)
        .subscribe((prod) => {
          console.log(prod);
        });
    }
    this.router.navigate(['categoryproducts/' + x]);
  }

  ngOnDestroy() {}
}
function elseif(arg0: boolean) {
  throw new Error('Function not implemented.');
}



