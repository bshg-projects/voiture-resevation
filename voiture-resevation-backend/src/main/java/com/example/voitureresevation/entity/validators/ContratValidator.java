package com.example.voitureresevation.entity.validators;

import com.example.voitureresevation.entity.Contrat;
import com.example.voitureresevation.zutils.validators.Validator;
import com.example.voitureresevation.zutils.validators.ValidatorItem;

import java.util.List;

public class ContratValidator extends Validator<Contrat> {
    public ContratValidator(Contrat item) {
        super(item);
    }

    public static void validate(Contrat item) {
        new ContratValidator(item).validate();
    }

    public ValidatorItem<Double> prix = new ValidatorItem<>(
            "prix",
            () -> this.getItem().getPrix(),
            (Double value) -> {
                this.prix.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<Integer> code = new ValidatorItem<>(
            "code",
            () -> this.getItem().getCode(),
            (Integer value) -> {
                this.code.getValidators()
                        .required()
                        .valid();
            }
    );

    @Override
    public List<ValidatorItem<?>> validatorItems() {
        return List.of(
                this.prix,
                this.code
        );
    }
}
