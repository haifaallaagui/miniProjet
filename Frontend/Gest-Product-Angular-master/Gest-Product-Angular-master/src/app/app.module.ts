import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProduitsComponent } from './produits/produits.component';
import { NewProductComponent } from './new-product/new-product.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { EditProductComponent } from './edit-product/edit-product.component';
import { CategoriesComponent } from './categories/categories.component';
import { AddCategorieComponent } from './add-categorie/add-categorie.component';
import { EditCategorieComponent } from './edit-categorie/edit-categorie.component';
import { CategoryProduitComponent } from './category-produit/category-produit.component';

@NgModule({
  declarations: [
    AppComponent,
    ProduitsComponent,
    NewProductComponent,
    EditProductComponent,
    CategoriesComponent,
    AddCategorieComponent,
    EditCategorieComponent,
    CategoryProduitComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
