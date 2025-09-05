import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PresentationTimeResponse {
  et: string;
  mt: string;
  utc: string;
  message: string;
  instantUtcIso: string;
}

@Injectable({ providedIn: 'root' })
export class PresentationService {
  constructor(private http: HttpClient) {}

  getEtMtUtc(
    etStartIsoLocal: string,
    baseURL: string
  ): Observable<PresentationTimeResponse> {
    const params = new HttpParams().set('etStart', etStartIsoLocal);

    return this.http.get<PresentationTimeResponse>(
      `${baseURL}/api/presentation/time`,
      {
        params,
      }
    );
  }
}
