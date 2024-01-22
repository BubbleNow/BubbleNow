import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Kind} from "./kind";
@Injectable({
  providedIn: 'root'
})
export class KindService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public getKinds(): Observable<Kind[]>{
    return this.http.get<Kind[]>(this.apiServerUrl + '/api/kinds/');
  }

}
