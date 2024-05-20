import {Validator, ValidatorItem} from "@bshg/validation";
import {Contrat} from "src/app/controller/entities/contrat";
import {Administrateur} from 'src/app/controller/entities/administrateur';
import {Client} from 'src/app/controller/entities/client';

export class ContratValidator extends Validator<Contrat> {
  prix = new ValidatorItem<number>(
    () => this.item().prix,
    (value) => this.item().prix = value,
    (value) => {
      this.prix.numberValidators
        ?.required()
        ?.valid()
    }
  )
  code = new ValidatorItem<number>(
    () => this.item().code,
    (value) => this.item().code = value,
    (value) => {
      this.code.numberValidators
        ?.required()
        ?.valid()
    }
  )
  administrateur = new ValidatorItem<Administrateur>(
    () => this.item().administrateur,
    (value) => this.item().administrateur = value,
    (value) => {
      this.administrateur.validators
        ?.errorIf(value?.id == null, "No Administrateur Was Provided!")
        ?.valid()
    }
  )
  client = new ValidatorItem<Client>(
    () => this.item().client,
    (value) => this.item().client = value,
    (value) => {
      this.client.validators
        ?.errorIf(value?.id == null, "No Client Was Provided!")
        ?.valid()
    }
  )

  override validatorItems: ValidatorItem<any>[] = [
    this.prix,
    this.code,
    this.administrateur,
    this.client,
  ]

  getValidatorItem(fieldName: string): ValidatorItem<any> {
    // @ts-ignore
    return this[fieldName];
  }

  static init(item: () => Contrat): ContratValidator {
    const validator = new ContratValidator()
    validator.item = item
    return validator;
  }

}
