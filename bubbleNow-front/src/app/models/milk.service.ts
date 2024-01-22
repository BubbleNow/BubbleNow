import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Milk} from "./milk";
@Injectable({
  providedIn: 'root'
})
export class MilkService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public getMilks(): Observable<Milk[]>{
    return this.http.get<Milk[]>(this.apiServerUrl + '/api/milks/');
  }

}
