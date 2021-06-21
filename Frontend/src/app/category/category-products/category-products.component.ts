import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from 'src/app/produit/model/produit.model';
import { ProduitService } from 'src/app/produit/services/produit.service';
import { Category } from '../model/category.model';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-category-products',
  templateUrl: './category-products.component.html',
})
export class CategoryProductsComponent implements OnInit {
  produits!: Produit[];
  category!: Category;
  newProduit = new Produit();
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
  supprimerProduit(p: Produit) {
    let conf = confirm('Etes-vous sûr ?');
    if (conf)
      if (p.id!)
        this.productService.supprimerProduit(p.id).subscribe(() => {
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
