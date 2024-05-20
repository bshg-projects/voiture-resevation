import {Validator, ValidatorItem} from "@bshg/validation";
import {Reservation} from "src/app/controller/entities/reservation";

export class ReservationValidator extends Validator<Reservation> {
  dateDebut = new ValidatorItem<Date>(
    () => this.item().dateDebut,
    (value) => this.item().dateDebut = value,
    (value) => {
      this.dateDebut.validators
        ?.required()
        ?.valid()
    }
  )
  dateFin = new ValidatorItem<Date>(
    () => this.item().dateFin,
    (value) => this.item().dateFin = value,
    (value) => {
      this.dateFin.validators
        ?.required()
        ?.valid()
    }
  )

  override validatorItems: ValidatorItem<any>[] = [
    this.dateDebut,
    this.dateFin,
  ]

  getValidatorItem(fieldName: string): ValidatorItem<any> {
    // @ts-ignore
    return this[fieldName];
  }

  static init(item: () => Reservation): ReservationValidator {
    const validator = new ReservationValidator()
    validator.item = item
    return validator;
  }

}
