import {Component, Host} from '@angular/core';
import {NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {AppComponent} from "../app.component";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-order-popup',
  standalone: true,
  imports: [
    NgForOf,
    NgOptimizedImage,
    NgClass,
    NgIf
  ],
  templateUrl: './order-popup.component.html'
})
export class OrderPopupComponent {
  public parent: AppComponent;
  public isPopupOpen: boolean = false;
  public orderNumber: any;
  public errorMessage: any;

  constructor(@Host() parent: AppComponent, private http: HttpClient) {
    this.parent = parent;
    this.orderNumber = null;
    this.errorMessage = null;
  }

  public sendOrder() {
    this.isPopupOpen = true;
    let orderData = this.parent.selected;

    const body = {
      addition: {id: orderData['addition']},
      syrup: {id: orderData['syrup']},
      milk: {id: orderData['milk']},
      size: {id: orderData['size']},
      kind: {id: orderData['kind']}
    };

    const url = 'http://localhost:8080/api/orders/create';

    this.http.post(url, body).subscribe({
      next: (response) => {
        this.orderNumber = response;
      },
      error: (error: HttpErrorResponse) => {
        this.errorMessage = error;
      }
    })
  }

}
