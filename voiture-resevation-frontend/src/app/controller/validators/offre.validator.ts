import {Validator, ValidatorItem} from "@bshg/validation";
import {Offre} from "src/app/controller/entities/offre";

export class OffreValidator extends Validator<Offre> {
  code = new ValidatorItem<string>(
    () => this.item().code,
    (value) => this.item().code = value,
    (value) => {
      this.code.stringValidators
        ?.required()
        ?.valid()
    }
  )

  override validatorItems: ValidatorItem<any>[] = [
    this.code,
  ]

  getValidatorItem(fieldName: string): ValidatorItem<any> {
    // @ts-ignore
    return this[fieldName];
  }

  static init(item: () => Offre): OffreValidator {
    const validator = new OffreValidator()
    validator.item = item
    return validator;
  }

}
