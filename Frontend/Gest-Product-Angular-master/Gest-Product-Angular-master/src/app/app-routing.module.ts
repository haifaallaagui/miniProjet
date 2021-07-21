import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProduitsComponent} from "./produits/produits.component";
import {NewProductComponent} from "./new-product/new-product.component";
import {EditProductComponent} from "./edit-product/edit-product.component";
import{ EditCategorieComponent}from "./edit-categorie/edit-categorie.component";
import {CategoriesComponent}from "./categories/categories.component";
import{ AddCategorieComponent} from "./add-categorie/add-categorie.component";
import { CategoryProduitComponent } from './category-produit/category-produit.component';


const routes: Routes = [
  { path: 'produits', component: ProduitsComponent },
  { path: 'add-produit', component: NewProductComponent },
  { path: 'updateProduit/:id', component: EditProductComponent },
  { path: 'categories', component: CategoriesComponent },
  { path: 'add-category', component: AddCategorieComponent },
  { path: 'categoryproducts/:id', component: CategoryProduitComponent },
  { path: 'exportpdf', component: CategoryProduitComponent },
  { path: 'updateCategory/:id', component: EditCategorieComponent },

  { path: '', redirectTo: 'produits', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
