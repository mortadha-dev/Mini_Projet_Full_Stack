import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ProduitsComponent } from './produit/produits/produits.component';
import { AddProduitComponent } from './produit/add-produit/add-produit.component';
import { UpdateProduitComponent } from './produit/update-produit/update-produit.component';
import { HttpClientModule } from '@angular/common/http';
import { CategoriesComponent } from './category/categories/categories.component';
import { AddCategoryComponent } from './category/add-category/add-category.component';
import { CategoryProductsComponent } from './category/category-products/category-products.component';
import { SearchComponent } from './search/search.component';
import { UpdateCategoryComponent } from './category/update-category/update-category.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination';
import { AdminmenuComponent } from './adminmenu/adminmenu.component';

@NgModule({
  declarations: [
    AppComponent,
    ProduitsComponent,
    AddProduitComponent,
    UpdateProduitComponent,
    CategoriesComponent,
    AddCategoryComponent,
    CategoryProductsComponent,
    SearchComponent,
    UpdateCategoryComponent,
    AdminmenuComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
