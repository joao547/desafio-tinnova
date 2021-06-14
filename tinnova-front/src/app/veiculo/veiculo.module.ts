import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VeiculoRoutingModule } from './veiculo-routing.module';
import { VeiculoComponent } from './veiculo.component';
import { ThemeModule } from '../@theme/theme.module';
import {
  NbButtonModule,
  NbCardModule,
  NbIconModule,
  NbInputModule,
  NbMenuModule, NbSpinnerModule,
  NbToastrModule,
  NbToastrService
} from '@nebular/theme';
import { ListarComponent } from './listar/listar.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { AdicionarComponent } from './adicionar/adicionar.component';
import {FormsModule} from "@angular/forms";
import {config} from "rxjs";
import {AdicionarService} from "./adicionar/adicionar.service";
import {ListarService} from "./listar/listar.service";


@NgModule({
  declarations: [VeiculoComponent, ListarComponent, AdicionarComponent],
  imports: [
    CommonModule,
    VeiculoRoutingModule,
    ThemeModule,
    NbMenuModule,
    NbCardModule,
    NbIconModule,
    NbInputModule,
    Ng2SmartTableModule,
    NbButtonModule,
    FormsModule,
    NbToastrModule.forRoot(),
    NbSpinnerModule,
  ],
  providers: [
    AdicionarService,
    ListarService
  ],
})
export class VeiculoModule { }
