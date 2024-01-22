import {Component, Host} from '@angular/core';
import {AppComponent} from "../app.component";
import {HttpClient, HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-preview',
  standalone: true,
  imports: [],
  templateUrl: './preview.component.html'
})
export class PreviewComponent {
  public parent: AppComponent;
  public price: any;
  constructor(@Host() parent: AppComponent, private http: HttpClient) {
    this.parent = parent;
    this.price = 0;
  }

  public countPrice(orderData: any){

    const url = 'http://localhost:8080/api/orders/get-price';
    let params = new HttpParams();
    if (orderData.milk) {
      params = params.set('milk', orderData.milk)
    }
    if (orderData.addition) {
      params = params.set('addition', orderData.addition)
    }
    if (orderData.size) {
      params = params.set('size', orderData.size)
    }

    this.http.get(url, {params}).subscribe(response => {
      this.price = response;
    })
  }



}
