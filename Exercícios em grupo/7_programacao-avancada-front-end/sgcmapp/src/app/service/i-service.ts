import { Observable } from "rxjs";
import { PageRequest } from "../model/page-request";
import { PageResponse } from "../model/page-response";

export interface IService<T> {

    apiUrl: string;

    get(termoBusca?: string, pageRequest?: PageRequest): Observable<PageResponse<T>>;
    getById(id: number): Observable<T>;
    save(objeto: T): Observable<T>;
    delete(id: number): Observable<void>;

}
