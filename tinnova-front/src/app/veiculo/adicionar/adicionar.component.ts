import { Component, OnInit } from '@angular/core';
import {AdicionarService} from "./adicionar.service";
import {NbToastrService} from "@nebular/theme";
import {Router} from "@angular/router";

@Component({
  selector: 'ngx-adicionar',
  templateUrl: './adicionar.component.html',
  styleUrls: ['./adicionar.component.scss'],
})
export class AdicionarComponent implements OnInit {

  veiculo = {
    veiculo: '',
    marca: '',
    ano: 2021,
    descricao: '',
  };

  isLoading = false;

  constructor(private adicionarService: AdicionarService,
              private toastService: NbToastrService,
              private route: Router) { }

  ngOnInit(): void {
  }

  createVeiculo() {
    this.isLoading = true;
    this.adicionarService.createVeiculo(this.veiculo).subscribe(data => {
      this.isLoading = false;
      this.toastService.success('Veículo cadastrado com sucesso', data.veiculo + ' foi cadastrado com sucesso');
      this.route.navigate(['veiculo/listar']);
    }, error => {
      this.isLoading = false;
      this.toastService.danger('Erro ao cadastrar o veículo, tento novamente', 'Erro');
    });
  }

}
