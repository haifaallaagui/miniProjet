import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { Category} from "../model/Category.model";
import { CategoryService } from '../services/category.service';


@Component({
  selector: 'app-add-categorie',
  templateUrl: './add-categorie.component.html',
  styleUrls: ['./add-categorie.component.css']
})
export class AddCategorieComponent implements OnInit {

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

