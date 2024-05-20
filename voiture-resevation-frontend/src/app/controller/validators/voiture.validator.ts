import {Validator, ValidatorItem} from "@bshg/validation";
import {Voiture} from "src/app/controller/entities/voiture";

export class VoitureValidator extends Validator<Voiture> {
  marque = new ValidatorItem<string>(
    () => this.item().marque,
    (value) => this.item().marque = value,
    (value) => {
      this.marque.stringValidators
        ?.required()
        ?.valid()
    }
  )
  coleur = new ValidatorItem<string>(
    () => this.item().coleur,
    (value) => this.item().coleur = value,
    (value) => {
      this.coleur.stringValidators
        ?.required()
        ?.valid()
    }
  )
  categorie = new ValidatorItem<string>(
    () => this.item().categorie,
    (value) => this.item().categorie = value,
    (value) => {
      this.categorie.stringValidators
        ?.required()
        ?.valid()
    }
  )
  nbrPlaces = new ValidatorItem<number>(
    () => this.item().nbrPlaces,
    (value) => this.item().nbrPlaces = value,
    (value) => {
      this.nbrPlaces.numberValidators
        ?.required()
        ?.valid()
    }
  )
  vitesse = new ValidatorItem<number>(
    () => this.item().vitesse,
    (value) => this.item().vitesse = value,
    (value) => {
      this.vitesse.numberValidators
        ?.required()
        ?.valid()
    }
  )

  override validatorItems: ValidatorItem<any>[] = [
    this.marque,
    this.coleur,
    this.categorie,
    this.nbrPlaces,
    this.vitesse,
  ]

  getValidatorItem(fieldName: string): ValidatorItem<any> {
    // @ts-ignore
    return this[fieldName];
  }

  static init(item: () => Voiture): VoitureValidator {
    const validator = new VoitureValidator()
    validator.item = item
    return validator;
  }

}
