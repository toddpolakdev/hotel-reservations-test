import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class WelcomeService {
  constructor(private http: HttpClient) {}
  getThreaded() {
    return this.http.get<{ en: any; fr: any }>(
      'http://localhost:8080/api/welcome/threaded'
    );
  }
}
