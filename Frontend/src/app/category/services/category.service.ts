import { Injectable } from '@angular/core';
import { Category } from '../model/category.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Produit } from 'src/app/produit/model/produit.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  apiURL: string = 'http://localhost:8085/stage/categories';
  constructor(private http: HttpClient) {}

  listeCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(this.apiURL + '/showcategories');
  }
  ajouterCategory(cat: Category): Observable<Category> {
    return this.http.post<Category>(
      this.apiURL + '/AddCategory',
      cat,
      httpOptions
    );
  }
  supprimerCategory(id: number) {
    const url = `${this.apiURL + '/deletecategory'}/${id}`;
    return this.http.delete(url, httpOptions);
  }
  listeProduitInCat(id: number): Observable<Produit[]> {
    const url = `${'http://localhost:8085/stage/products/findbycatid'}/${id}`;
    return this.http.get<Produit[]>(url, httpOptions);
  }
  OneCategory(id: number): Observable<Category> {
    const url = `${this.apiURL + '/findcategory'}/${id}`;
    return this.http.get<Category>(url);
  }
  updateCategory(cat: Category, id: number): Observable<Category> {
    return this.http.put<Category>(
      `${this.apiURL + '/updatecategory'}/${id}`,
      cat,
      httpOptions
    );
  }
}
