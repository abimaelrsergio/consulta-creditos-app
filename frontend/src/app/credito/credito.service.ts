import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CreditoDto {
  numeroCredito: string;
  numeroNfse: string;
  dataConstituicao: string;
  valorIssqn: number;
  tipoCredito: string;
  simplesNacional: string;
  aliquota: number;
  valorFaturado: number;
  valorDeducao: number;
  baseCalculo: number;
}

@Injectable({
  providedIn: 'root'
})
export class CreditoService {
  private apiUrl = 'http://localhost:8080/api/creditos';

  constructor(private http: HttpClient) {}

  getByNumeroCredito(numero: string): Observable<CreditoDto> {
    return this.http.get<CreditoDto>(`${this.apiUrl}/credito/${numero}`);
  }

  getByNumeroNfse(nfse: string): Observable<CreditoDto[]> {
    return this.http.get<CreditoDto[]>(`${this.apiUrl}/${nfse}`);
  }
}
