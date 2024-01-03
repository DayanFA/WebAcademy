export interface IList<T> {

    registros: T[];

    get(termoBusca?: string): void;
    delete(id: number): void;

}
