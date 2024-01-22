import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Syrup} from "./syrup";
@Injectable({
  providedIn: 'root'
})
export class SyrupService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public getSyrups(): Observable<Syrup[]>{
    return this.http.get<Syrup[]>(this.apiServerUrl + '/api/syrups/');
  }

}
