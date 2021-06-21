import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/category/services/category.service';
import { Produit } from '../model/produit.model';
import { ProduitService } from '../services/produit.service';

@Component({
  selector: 'app-add-produit',
  templateUrl: './add-produit.component.html',
})
export class AddProduitComponent implements OnInit {
  newProduit = new Produit();
  message!: string;

  constructor(
    private produitService: ProduitService,
    private categoryServie: CategoryService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {}
  selectedDay: string = '';
  days: any = ['True', 'False'];
  radioChangeHandler(event: any) {
    this.selectedDay = event.target.value;
  }

  addProduit() {
    const user = localStorage.getItem('currentUser');
    let x: number = +user!;
    console.log(x);
    console.log(this.selectedDay);
    if (this.selectedDay == 'true') {
      var boolValue = this.selectedDay.toLowerCase() == 'false';
      console.log(boolValue);
      this.newProduit.disponible = boolValue;
      this.produitService
        .ajouterProduit(this.newProduit, x)
        .subscribe((prod) => {
          console.log(prod);
        });
    } else {
      var boolValue = this.selectedDay.toLowerCase() == 'true';
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
