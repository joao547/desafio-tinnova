import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Veiculo} from "../veiculo";

@Injectable()
export class ListarService {
  constructor(private http: HttpClient) {
  }

  getList(): Observable<any> {
    return this.http.get(environment.api_url + '/veiculos');
  }

  deleteVeiculo(id) {
    return this.http.delete(environment.api_url + '/veiculos/' + id);
  }

  updateVeiculo(veiculo: Veiculo) {
    return this.http.put(environment.api_url + '/veiculos/' + veiculo.id, veiculo);
  }
}
