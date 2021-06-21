import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './category/add-category/add-category.component';
import { CategoriesComponent } from './category/categories/categories.component';
import { CategoryProductsComponent } from './category/category-products/category-products.component';
import { UpdateCategoryComponent } from './category/update-category/update-category.component';
import { AddProduitComponent } from './produit/add-produit/add-produit.component';
import { ProduitsComponent } from './produit/produits/produits.component';
import { UpdateProduitComponent } from './produit/update-produit/update-produit.component';

const routes: Routes = [
  { path: 'produits', component: ProduitsComponent },
  { path: 'add-produit', component: AddProduitComponent },
  { path: 'updateProduit/:id', component: UpdateProduitComponent },
  { path: 'categories', component: CategoriesComponent },
  { path: 'add-category', component: AddCategoryComponent },
  { path: 'categoryproducts/:id', component: CategoryProductsComponent },
  { path: 'exportpdf', component: CategoryProductsComponent },
  { path: 'updateCategory/:id', component: UpdateCategoryComponent },

  { path: '', redirectTo: 'produits', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
