package com.example.voitureresevation.entity.validators;

import com.example.voitureresevation.entity.Administrateur;
import com.example.voitureresevation.zutils.validators.Validator;
import com.example.voitureresevation.zutils.validators.ValidatorItem;

import java.util.List;

public class AdministrateurValidator extends Validator<Administrateur> {
    public AdministrateurValidator(Administrateur item) {
        super(item);
    }

    public static void validate(Administrateur item) {
        new AdministrateurValidator(item).validate();
    }

    public ValidatorItem<String> nom = new ValidatorItem<>(
            "nom",
            () -> this.getItem().getNom(),
            (String value) -> {
                this.nom.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<String> prenom = new ValidatorItem<>(
            "prenom",
            () -> this.getItem().getPrenom(),
            (String value) -> {
                this.prenom.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<Integer> age = new ValidatorItem<>(
            "age",
            () -> this.getItem().getAge(),
            (Integer value) -> {
                this.age.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<String> localite = new ValidatorItem<>(
            "localite",
            () -> this.getItem().getLocalite(),
            (String value) -> {
                this.localite.getValidators()
                        .required()
                        .valid();
            }
    );

    @Override
    public List<ValidatorItem<?>> validatorItems() {
        return List.of(
                this.nom,
                this.prenom,
                this.age,
                this.localite
        );
    }
}
