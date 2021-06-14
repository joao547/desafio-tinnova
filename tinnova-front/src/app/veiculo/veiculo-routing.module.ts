import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdicionarComponent } from './adicionar/adicionar.component';
import { ListarComponent } from './listar/listar.component';

import { VeiculoComponent } from './veiculo.component';

const routes: Routes = [
  {
    path: '',
    component: VeiculoComponent,
    children: [
      {
        path: '',
        component: ListarComponent
      },
      {
        path: 'listar',
        component: ListarComponent
      },
      {
        path: 'adicionar',
        component: AdicionarComponent
      }
    ]
  }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VeiculoRoutingModule { }
