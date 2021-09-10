import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  constructor(private http: HttpClient) {
  }

  list<T>(uri: string): Observable<T[]> {
    return this.http.get<T[]>(uri)
  }

  one<T>(uri: string): Observable<T> {
    return this.http.get<T>(uri);
  }

  post<P, R>(uri: string, body: P): Observable<R> {
    return this.http.post<R>(uri, body);
  }

  update<P, R>(uri: string, body: P): Observable<R> {
    return this.http.put<R>(uri, body);
  }

  remove<R>(uri: string): Observable<R> {
    return this.http.delete<R>(uri)
  }
}
