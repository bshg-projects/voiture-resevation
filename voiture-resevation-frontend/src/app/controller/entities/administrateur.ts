import {Reservation} from 'src/app/controller/entities/reservation';
import {Offre} from 'src/app/controller/entities/offre';
import {Contrat} from 'src/app/controller/entities/contrat';
import {AppUser} from "src/app/controller/auth/entities/app-user";

export class Administrateur extends AppUser {
  nom: string | undefined;
  prenom: string | undefined;
  age: number | undefined;
  localite: string | undefined;
  reservations: Reservation | undefined;
  offres: Offre | undefined;
  contrat: Array<Contrat> | undefined;
}
