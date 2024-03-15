package model.commande;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import model.AuditEntity;

import java.util.List;

import static model.commande.Commande.Statut.*;

@Value
@Builder
@RequiredArgsConstructor
public class Commande extends AuditEntity {

    Long id;
    @With
    List<Long> produits;
    @With
    Long table;
    @With
    Statut statut;

    public enum Statut {
        EN_ATTENTE,
        EN_COURS,
        PRETE,
        SERVIE,
        TERMINER
    }

    public boolean isCommandeArchivable() {
        return TERMINER.equals(this.statut);
    }

    public boolean isCommandePrise() {
        return EN_ATTENTE.equals(this.statut);
    }

    public boolean isCommandeEnCuisine() {
        return EN_COURS.equals(this.statut);
    }

    public boolean isCommandePrete() {
        return PRETE.equals(this.statut);
    }

    public boolean isCommandeServie() {
        return SERVIE.equals(this.statut);
    }
}


