import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";

@Injectable()
export class AdicionarService {
  constructor(private http: HttpClient) {
  }

  createVeiculo(veiculo): Observable<any> {
    return this.http.post(environment.api_url + '/veiculos', veiculo);
  }
}
