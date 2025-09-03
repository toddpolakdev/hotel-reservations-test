import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class WelcomeService {
  private baseUrl = 'http://localhost:8080/api/welcome';

  constructor(private http: HttpClient) {}

  getWelcome(lang: string): Observable<string> {
    return this.http.get(this.baseUrl, {
      params: { lang },
      responseType: 'text',
    });
  }
}
