package com.example.voitureresevation.entity.validators;

import com.example.voitureresevation.entity.Voiture;
import com.example.voitureresevation.zutils.validators.Validator;
import com.example.voitureresevation.zutils.validators.ValidatorItem;

import java.util.List;

public class VoitureValidator extends Validator<Voiture> {
    public VoitureValidator(Voiture item) {
        super(item);
    }

    public static void validate(Voiture item) {
        new VoitureValidator(item).validate();
    }

    public ValidatorItem<String> marque = new ValidatorItem<>(
            "marque",
            () -> this.getItem().getMarque(),
            (String value) -> {
                this.marque.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<String> coleur = new ValidatorItem<>(
            "coleur",
            () -> this.getItem().getColeur(),
            (String value) -> {
                this.coleur.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<String> categorie = new ValidatorItem<>(
            "categorie",
            () -> this.getItem().getCategorie(),
            (String value) -> {
                this.categorie.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<Integer> nbrPlaces = new ValidatorItem<>(
            "nbrPlaces",
            () -> this.getItem().getNbrPlaces(),
            (Integer value) -> {
                this.nbrPlaces.getValidators()
                        .required()
                        .valid();
            }
    );
    public ValidatorItem<Integer> vitesse = new ValidatorItem<>(
            "vitesse",
            () -> this.getItem().getVitesse(),
            (Integer value) -> {
                this.vitesse.getValidators()
                        .required()
                        .valid();
            }
    );

    @Override
    public List<ValidatorItem<?>> validatorItems() {
        return List.of(
                this.marque,
                this.coleur,
                this.categorie,
                this.nbrPlaces,
                this.vitesse
        );
    }
}
