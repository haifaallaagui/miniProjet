import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { ProduitService } from '../services/produit.service';
import { Product } from '../model/product.model';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  currentProduit = new Product();
  //router: any;
  constructor(
    private activatedRoute: ActivatedRoute,
    private produitService: ProduitService,
    private router: Router
  ) {}
  ngOnInit() {
    this.produitService
      .OneProduct(this.activatedRoute.snapshot.params.id)
      .subscribe((prod) => {
        this.currentProduit = prod;
      });
  }
  updateProduit() {
    this.produitService
      .updateProduit(
        this.currentProduit,
        this.activatedRoute.snapshot.params.id
      )
      .subscribe(
        (prod) => {
          const user = localStorage.getItem('currentUser');
          let x: number = +user!;
          console.log(x);
          this.router.navigate(['categoryproducts/' + x]);
        },
        (error) => {
          alert('Problème lors de la modification !');
        }
      );
  }
}
