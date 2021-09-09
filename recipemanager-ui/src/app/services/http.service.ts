import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'trace-id': '1234'
  })
};

@Injectable()
export class HttpService {

  baseUrl= "http://localhost:8080/api";

  constructor(private http: HttpClient) {}

  get(url, httpParams ?: HttpParams): Observable < any > {
    if (httpParams) {
      httpOptions['params'] = httpParams;
    }
    return this.http.get(this.baseUrl + url, httpOptions);
  }

  post(url, body): Observable < any > {
    return this.http.post(this.baseUrl + url, body, httpOptions);
  }

  put(url, id: string, body: any): Observable < any > {
    return this.http.put(this.baseUrl + `${url}/${id}`, body, httpOptions); // ...using put request

  }

  getById(url, id: string): Observable < any > {
    return this.http.get(this.baseUrl + `${url}/${id}`, httpOptions); // ...using put request

  }

  deleteById(url, id: string): Observable < any > {
    return this.http.delete(this.baseUrl + `${url}/${id}`, httpOptions); // ...using delete request
  }

}