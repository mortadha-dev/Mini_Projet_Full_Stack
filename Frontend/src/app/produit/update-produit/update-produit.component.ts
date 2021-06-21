import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from '../model/produit.model';
import { ProduitService } from '../services/produit.service';

@Component({
  selector: 'app-update-produit',
  templateUrl: './update-produit.component.html',
  styles: [],
})
export class UpdateProduitComponent implements OnInit {
  currentProduit = new Produit();
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
