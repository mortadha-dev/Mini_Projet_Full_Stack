import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from '../model/category.model';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
})
export class AddCategoryComponent implements OnInit {
  newCategory = new Category();
  message!: string;
  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}
  ngOnInit(): void {}
  addCategory() {
    this.categoryService
      .ajouterCategory(this.newCategory)
      .subscribe((cat: any) => {
        console.log(cat);
      });
    this.router.navigate(['categories']);
  }
  ngOnDestroy() {}
}
