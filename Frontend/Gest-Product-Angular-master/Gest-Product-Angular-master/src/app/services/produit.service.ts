import { Injectable } from '@angular/core';
import {  Product } from '../model/product.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root',
})
export class ProduitService {
  apiURL: string = 'http://localhost:8090/miniProjet/products/';
  constructor(private http: HttpClient) {}

  listeProduit(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiURL + 'showproducts');
  }
  ajouterProduit(prod: Product, id: number): Observable<Product> {
    const user = localStorage.getItem('currentUser');
    let x: number = +user!;
    console.log(x);
    return this.http.post<Product>(
      `${this.apiURL + 'AddProduct'}/${x}`,
      prod,
      httpOptions
    );
  }
  supprimerProduit(id: number) {
    const url = `${this.apiURL + 'deleteproduct'}/${id}`;
    return this.http.delete(url, httpOptions);
  }
  exportPdfProducts(id: number): Observable<Blob> {
    return this.http.get(`${this.apiURL + 'exportpdf'}/${id}`, {
      responseType: 'blob',
    });
  }
  exportExcelProducts(id: number): Observable<Blob> {
    return this.http.get(`${this.apiURL + 'exportexcel'}/${id}`, {
      responseType: 'blob',
    });
  }
  OneProduct(id: number): Observable<Product> {
    const url = `${this.apiURL + 'findproduct'}/${id}`;
    return this.http.get<Product>(url);
  }
  updateProduit(prod: Product, id: number): Observable<Product> {
    return this.http.put<Product>(
      `${this.apiURL + 'updateproduct'}/${id}`,
      prod,
      httpOptions
    );
  }
}
