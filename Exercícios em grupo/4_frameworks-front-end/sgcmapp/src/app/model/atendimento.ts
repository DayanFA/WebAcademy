import { Convenio } from "./convenio";
import { Paciente } from "./paciente";
import { Profissional } from "./profisisonal";

export type Atendimento = {
    id: number;
    data: string;
    hora: string;
    status: string;
    convenio: Convenio | null;
    paciente: Paciente;
    profissional: Profissional;
}