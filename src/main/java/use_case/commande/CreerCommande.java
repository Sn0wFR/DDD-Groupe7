package use_case.commande;

import model.commande.Commande;

import java.util.List;

import static model.commande.Commande.Statut.*;

public class CreerCommande {

    public Commande creationCommande(List<Long> idProduits, Long idTable) {
        Commande commande = Commande.builder()
                .produits(idProduits)
                .table(idTable)
                .statut(EN_ATTENTE)
                .build();
        commande.vide();
        return commande;
    }

}
