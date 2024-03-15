package use_case.commande;

import model.commande.Commande;
import model.commande.Id;

import java.util.List;

import static model.commande.Commande.Statut.EN_ATTENTE;

public class CreerCommande {

    public Commande creationCommande(List<Id> idProduits, Id idTable) {
        Commande commande = Commande.builder()
                .produits(idProduits)
                .table(idTable)
                .statut(EN_ATTENTE)
                .build();
        commande.vide();
        return commande;
    }

}
