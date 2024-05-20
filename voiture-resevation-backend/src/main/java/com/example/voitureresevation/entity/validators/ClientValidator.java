package com.example.voitureresevation.entity.validators;

import com.example.voitureresevation.entity.Client;
import com.example.voitureresevation.zutils.validators.Validator;
import com.example.voitureresevation.zutils.validators.ValidatorItem;

import java.time.LocalDate;
import java.util.List;

public class ClientValidator extends Validator<Client> {
    public ClientValidator(Client item) {
        super(item);
    }

    public static void validate(Client item) {
        new ClientValidator(item).validate();
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
    public ValidatorItem<String> adresse = new ValidatorItem<>(
            "adresse",
            () -> this.getItem().getAdresse(),
            (String value) -> {
                this.adresse.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<LocalDate> datePromis = new ValidatorItem<>(
            "datePromis",
            () -> this.getItem().getDatePromis(),
            (LocalDate value) -> {
                this.datePromis.getValidators()
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
                this.localite,
                this.adresse,
                this.datePromis
        );
    }
}
