package com.example.voitureresevation.entity.validators;

import com.example.voitureresevation.entity.Offre;
import com.example.voitureresevation.zutils.validators.Validator;
import com.example.voitureresevation.zutils.validators.ValidatorItem;

import java.util.List;

public class OffreValidator extends Validator<Offre> {
    public OffreValidator(Offre item) {
        super(item);
    }

    public static void validate(Offre item) {
        new OffreValidator(item).validate();
    }

    public ValidatorItem<String> code = new ValidatorItem<>(
            "code",
            () -> this.getItem().getCode(),
            (String value) -> {
                this.code.getValidators()
                        .required()
                        .valid();
            }
    );

    @Override
    public List<ValidatorItem<?>> validatorItems() {
        return List.of(
                this.code
        );
    }
}
