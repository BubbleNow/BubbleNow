import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Addition} from "./addition";
@Injectable({
  providedIn: 'root'
})
export class AdditionService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public getAdditions(): Observable<Addition[]>{
    return this.http.get<Addition[]>(this.apiServerUrl + '/api/additions/');
  }

}
