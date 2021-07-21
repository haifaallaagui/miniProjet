import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryProduitComponent } from './category-produit.component';

describe('CategoryProduitComponent', () => {
  let component: CategoryProduitComponent;
  let fixture: ComponentFixture<CategoryProduitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CategoryProduitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
