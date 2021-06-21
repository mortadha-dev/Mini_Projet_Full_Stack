import { Injectable } from '@angular/core';
import { Produit } from '../model/produit.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root',
})
export class ProduitService {
  apiURL: string = 'http://localhost:8085/stage/products/';
  constructor(private http: HttpClient) {}

  listeProduit(): Observable<Produit[]> {
    return this.http.get<Produit[]>(this.apiURL + 'showproducts');
  }
  ajouterProduit(prod: Produit, id: number): Observable<Produit> {
    const user = localStorage.getItem('currentUser');
    let x: number = +user!;
    console.log(x);
    return this.http.post<Produit>(
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
  OneProduct(id: number): Observable<Produit> {
    const url = `${this.apiURL + 'findproduct'}/${id}`;
    return this.http.get<Produit>(url);
  }
  updateProduit(prod: Produit, id: number): Observable<Produit> {
    return this.http.put<Produit>(
      `${this.apiURL + 'updateproduct'}/${id}`,
      prod,
      httpOptions
    );
  }
}
