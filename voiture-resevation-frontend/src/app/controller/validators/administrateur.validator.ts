import {Validator, ValidatorItem} from "@bshg/validation";
import {Administrateur} from "src/app/controller/entities/administrateur";
import {Reservation} from 'src/app/controller/entities/reservation';
import {Offre} from 'src/app/controller/entities/offre';

export class AdministrateurValidator extends Validator<Administrateur> {
  public username = new ValidatorItem<string>(
    () => this.item().username,
    (value) => this.item().username = value,
    (value) => {
      this.username.stringValidators
        ?.required()
        ?.valid()
    }
  )
  password = new ValidatorItem<string>(
    () => this.item().password,
    (value) => this.item().password = value,
    (value) => {
      this.password.stringValidators
        ?.required()
        ?.min(6)
        ?.valid()
    }
  )

  nom = new ValidatorItem<string>(
    () => this.item().nom,
    (value) => this.item().nom = value,
    (value) => {
      this.nom.stringValidators
        ?.required()
        ?.valid()
    }
  )
  prenom = new ValidatorItem<string>(
    () => this.item().prenom,
    (value) => this.item().prenom = value,
    (value) => {
      this.prenom.stringValidators
        ?.required()
        ?.valid()
    }
  )
  age = new ValidatorItem<number>(
    () => this.item().age,
    (value) => this.item().age = value,
    (value) => {
      this.age.numberValidators
        ?.required()
        ?.valid()
    }
  )
  localite = new ValidatorItem<string>(
    () => this.item().localite,
    (value) => this.item().localite = value,
    (value) => {
      this.localite.stringValidators
        ?.required()
        ?.valid()
    }
  )
  reservations = new ValidatorItem<Reservation>(
    () => this.item().reservations,
    (value) => this.item().reservations = value,
    (value) => {
      this.reservations.validators
        ?.errorIf(value?.id == null, "No Reservations Was Provided!")
        ?.valid()
    }
  )
  offres = new ValidatorItem<Offre>(
    () => this.item().offres,
    (value) => this.item().offres = value,
    (value) => {
      this.offres.validators
        ?.errorIf(value?.id == null, "No Offres Was Provided!")
        ?.valid()
    }
  )

  override validatorItems: ValidatorItem<any>[] = [
    this.username,
    this.password,
    this.nom,
    this.prenom,
    this.age,
    this.localite,
    this.reservations,
    this.offres,
  ]

  override getValidatorItem(fieldName: string): ValidatorItem<any> {
    // @ts-ignore
    return this[fieldName];
  }

  static init(item: () => Administrateur): AdministrateurValidator {
    const validator = new AdministrateurValidator()
    validator.item = item
    return validator;
  }

}
