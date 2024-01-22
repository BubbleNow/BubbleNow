import {Component, Host} from '@angular/core';
import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {AppComponent} from "../app.component";
import {OrderPopupComponent} from "../order-popup/order-popup.component";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    NgForOf,
    NgOptimizedImage,
    NgClass,
    OrderPopupComponent,
    NgIf
  ],
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent {
  public parent: AppComponent;
  public active: string;
  public canOrder: boolean;


  constructor(@Host() parent: AppComponent, private http: HttpClient) {
    this.parent = parent;
    this.active = 'milk';
    this.canOrder = false;

  }

  public setActive(ingredient: string) {
    let previousIngredient: string | null = null;
    for (const key of Object.keys(this.parent.selected)) {
      if (key === ingredient) {
        break;
      }
      previousIngredient = key;
    }

    if (!previousIngredient || this.parent.selected[previousIngredient] !== null) {
      this.active = ingredient;
    }
  }

  public selectIngredient(ingredient: string, id: number) {
    this.parent.selected[ingredient] = id;

    this.parent.previewComponent.countPrice(this.parent.selected);

    const next = Object.keys(this.parent.selected).find(key => this.parent.selected[key] === null);
    if (next) {
      this.setActive(next);
    } else {
      this.canOrder = true;
    }
  }

  public refresh() {
    for (let key in this.parent.selected) {
      this.parent.selected[key] = null;
    }
    this.active = 'milk';
    this.parent.orderPopupComponent.isPopupOpen = false;
    this.canOrder = false;
    this.parent.orderPopupComponent.orderNumber = null;
    this.parent.previewComponent.price = 0;
    this.parent.orderPopupComponent.errorMessage = null;
  }


}
