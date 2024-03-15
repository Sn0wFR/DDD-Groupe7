package model.commande.statut;

import lombok.Getter;
import model.commande.Commande.Statut;

@Getter
public abstract class GeneriqueStatut {

    Statut name;

    public GeneriqueStatut(Statut name){
        this.name = name;
    }

}
