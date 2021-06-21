import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../model/category.model';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
})
export class UpdateCategoryComponent implements OnInit {
  currentCategory = new Category();
  //router: any;
  constructor(
    private activatedRoute: ActivatedRoute,
    private categoryService: CategoryService,
    private router: Router
  ) {}
  ngOnInit() {
    this.categoryService
      .OneCategory(this.activatedRoute.snapshot.params.id)
      .subscribe((cat) => {
        this.currentCategory = cat;
      });
  }
  updateCategory() {
    this.categoryService
      .updateCategory(
        this.currentCategory,
        this.activatedRoute.snapshot.params.id
      )
      .subscribe(
        (cat) => {
          this.router.navigate(['categories']);
        },
        (error) => {
          alert('Problème lors de la modification !');
        }
      );
  }
}
