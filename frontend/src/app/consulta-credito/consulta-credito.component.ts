import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CreditoService, CreditoDto } from '../credito/credito.service';

@Component({
  selector: 'app-consulta-credito',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './consulta-credito.component.html',
  styleUrl: './consulta-credito.component.css'
})
export class ConsultaCreditoComponent {
  numeroNfse = '';
  numeroCredito = '';
  creditos: CreditoDto[] = [];
  creditoDetalhado: CreditoDto | null = null;
  erro: string | null = null;

  constructor(private creditoService: CreditoService) {}

  consultarPorNfse() {
    this.erro = null;
    this.creditoDetalhado = null;
    this.creditos = [];

    this.creditoService.getByNumeroNfse(this.numeroNfse).subscribe({
      next: (res) => this.creditos = res,
      error: (err) => this.erro = 'NFS-e não encontrada ou inválida.'
    });
  }

  consultarPorNumeroCredito() {
    this.erro = null;
    this.creditoDetalhado = null;
    this.creditos = [];

    this.creditoService.getByNumeroCredito(this.numeroCredito).subscribe({
      next: (res) => this.creditoDetalhado = res,
      error: (err) => this.erro = 'Crédito não encontrado ou inválido.'
    });
  }
}