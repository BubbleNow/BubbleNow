import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Size} from "./size";
@Injectable({
  providedIn: 'root'
})
export class SizeService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public getSizes(): Observable<Size[]>{
    return this.http.get<Size[]>(this.apiServerUrl + '/api/sizes/');
  }

}
