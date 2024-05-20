import {Contrat} from 'src/app/controller/entities/contrat';

export class Client {
  id!: number;
  nom: string | undefined;
  prenom: string | undefined;
  age: number | undefined;
  localite: string | undefined;
  adresse: string | undefined;
  datePromis: Date | undefined;
  contrat: Array<Contrat> | undefined;
}
