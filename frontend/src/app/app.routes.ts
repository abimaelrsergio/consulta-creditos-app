import { Routes } from '@angular/router';
import { ConsultaCreditoComponent } from './consulta-credito/consulta-credito.component';

export const routes: Routes = [
    { path: 'consulta', component: ConsultaCreditoComponent },
    { path: '', redirectTo: 'consulta', pathMatch: 'full' }, // redireciona para /consulta
    { path: '**', redirectTo: 'consulta' } // rota curinga para qualquer caminho inválido
];
