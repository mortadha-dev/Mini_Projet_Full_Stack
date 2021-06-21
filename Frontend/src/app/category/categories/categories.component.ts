import { Component, OnInit } from '@angular/core';
import { Category } from '../model/category.model';
import { CategoryService } from '../services/category.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
})
export class CategoriesComponent implements OnInit {
  categories!: Category[];
  nom: any;
  p: number = 1;

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryService.listeCategory().subscribe((cats) => {
      console.log(cats);
      this.categories = cats;
    });
  }
  supprimerCategory(p: Category) {
    let conf = confirm('Etes-vous sûr ?');
    if (conf)
      if (p.id!)
        this.categoryService.supprimerCategory(p.id).subscribe(() => {
          console.log('Category supprimé');
        });
    this.router.navigate(['categories']).then(() => {
      window.location.reload();
    });
  }
  Search() {
    if (this.nom == '') {
      this.ngOnInit();
    } else {
      this.categories = this.categories.filter((res) => {
        return res.nom?.toLowerCase().match(this.nom.toLowerCase());
      });
    }
  }
}
