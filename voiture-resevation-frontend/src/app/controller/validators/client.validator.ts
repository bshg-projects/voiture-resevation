import {Validator, ValidatorItem} from "@bshg/validation";
import {Client} from "src/app/controller/entities/client";

export class ClientValidator extends Validator<Client> {
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
  adresse = new ValidatorItem<string>(
    () => this.item().adresse,
    (value) => this.item().adresse = value,
    (value) => {
      this.adresse.stringValidators
        ?.required()
        ?.valid()
    }
  )
  datePromis = new ValidatorItem<Date>(
    () => this.item().datePromis,
    (value) => this.item().datePromis = value,
    (value) => {
      this.datePromis.validators
        ?.required()
        ?.valid()
    }
  )

  override validatorItems: ValidatorItem<any>[] = [
    this.nom,
    this.prenom,
    this.age,
    this.localite,
    this.adresse,
    this.datePromis,
  ]

  getValidatorItem(fieldName: string): ValidatorItem<any> {
    // @ts-ignore
    return this[fieldName];
  }

  static init(item: () => Client): ClientValidator {
    const validator = new ClientValidator()
    validator.item = item
    return validator;
  }

}
