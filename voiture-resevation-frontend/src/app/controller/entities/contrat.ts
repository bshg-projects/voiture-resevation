import {Administrateur} from 'src/app/controller/entities/administrateur';
import {Client} from 'src/app/controller/entities/client';

export class Contrat {
  id!: number;
  prix: number | undefined;
  code: number | undefined;
  administrateur: Administrateur | undefined;
  client: Client | undefined;
}
