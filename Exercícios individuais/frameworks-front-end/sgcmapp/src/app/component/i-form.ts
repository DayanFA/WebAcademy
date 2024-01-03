import { NgForm } from "@angular/forms";

export interface IForm<T> {
    registro: T;
    save(form: NgForm): void;
}
