import { Component, OnInit } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { SmartTableData } from '../../@core/data/smart-table';
import {ListarService} from "./listar.service";
import {NbToastrService} from "@nebular/theme";
import {Veiculo} from "../veiculo";


@Component({
  selector: 'ngx-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {

  settings = {
    noDataMessage: 'Nao ha nenhum dado pra exibir',
    actions: {
      position: 'right',
      columnTitle: 'Açoes',
      add: false,
    },
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
      confirmSave: true,
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      veiculo: {
        title: 'Veículo',
        type: 'string',
      },
      marca: {
        title: 'Marca',
        type: 'string',
      },
      descricao: {
        title: 'Descrição',
        type: 'string',
      },
      vendido: {
        title: 'Vendido',
        type: 'boolean',
      },
      ano: {
        title: 'Ano',
        type: 'number',
      },
    },
  };

  source: LocalDataSource = new LocalDataSource();
  isLoading = false;

  constructor(private listarService: ListarService,
              private toastService: NbToastrService) {
  }

  ngOnInit(): void {
    this.isLoading = true;
    this.listarService.getList().subscribe(data => {
      this.isLoading = false;
      this.source.load(data);
    }, error => {
      this.isLoading = false;
      this.toastService.danger('Erro ao listar os veículos, tento novamente', 'Erro');
    });
  }

  onDeleteConfirm(event): void {
    const veiculo = event.data;
    if (window.confirm('Você deseja mesmo deletar o ' + veiculo.veiculo + ' ?')) {
      this.isLoading = true;
      this.listarService.deleteVeiculo(veiculo.id).subscribe(data => {
        this.isLoading = false;
        this.toastService.success(veiculo.veiculo + ' deletado com sucesso', 'Veículo foi deletado');
      }, error => {
        this.isLoading = false;
        this.toastService.danger('Erro ao deletar o veículo, tento novamente', 'Erro');
        event.confirm.reject();
      })
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }

  onEditConfirm(event) {
    this.isLoading = true;
    const veiculo: Veiculo = event.data;
    const newveiculo: Veiculo = event.newData;
    if (this.verifyModificated(veiculo, newveiculo)) {
      this.listarService.updateVeiculo(newveiculo).subscribe(data => {
        this.toastService.success(veiculo.veiculo + ' editado com sucesso', 'Veículo foi editado');
        event.confirm.resolve();
      }, error => {
        this.toastService.danger('Erro ao deletar o veículo, tento novamente', 'Erro');
        event.confirm.reject();
      });
    }
    else {
      this.toastService.warning('Não houve nenhuma modificação', 'Sem modificação');
    }
    this.isLoading = false;
  }

  verifyModificated(veiculo: Veiculo, newVeiculo: Veiculo) {
    if (veiculo.veiculo !== newVeiculo.veiculo)
      return true;
    if (veiculo.marca !== newVeiculo.marca)
      return true;
    if (veiculo.ano !== newVeiculo.ano)
      return true;
    if (veiculo.descricao !== newVeiculo.descricao)
      return true;
    if (veiculo.vendido !== newVeiculo.vendido)
      return true;
    return false;
  }
}
