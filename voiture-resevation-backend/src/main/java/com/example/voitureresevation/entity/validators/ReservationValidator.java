package com.example.voitureresevation.entity.validators;

import com.example.voitureresevation.entity.Reservation;
import com.example.voitureresevation.zutils.validators.Validator;
import com.example.voitureresevation.zutils.validators.ValidatorItem;

import java.time.LocalDate;
import java.util.List;

public class ReservationValidator extends Validator<Reservation> {
    public ReservationValidator(Reservation item) {
        super(item);
    }

    public static void validate(Reservation item) {
        new ReservationValidator(item).validate();
    }

    public ValidatorItem<LocalDate> dateDebut = new ValidatorItem<>(
            "dateDebut",
            () -> this.getItem().getDateDebut(),
            (LocalDate value) -> {
                this.dateDebut.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<LocalDate> dateFin = new ValidatorItem<>(
            "dateFin",
            () -> this.getItem().getDateFin(),
            (LocalDate value) -> {
                this.dateFin.getValidators()
                        .required()
                        .valid();
            }
    );

    @Override
    public List<ValidatorItem<?>> validatorItems() {
        return List.of(
                this.dateDebut,
                this.dateFin
        );
    }
}
