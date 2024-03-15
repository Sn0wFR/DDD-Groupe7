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

    Id id;
    @With
    List<Id> produits;
    @With
    Id table;
    @With
    Statut statut;

    public enum Statut {
        EN_ATTENTE,
        EN_COURS,
        PRETE,
        SERVIE,
        TERMINER
    }

    public boolean estArchivable() {
        return TERMINER.equals(this.statut);
    }

    public boolean estPrise() {
        return EN_ATTENTE.equals(this.statut);
    }

    public boolean estEnCuisine() {
        return EN_COURS.equals(this.statut);
    }

    public boolean estPrete() {
        return PRETE.equals(this.statut);
    }

    public boolean estServie() {
        return SERVIE.equals(this.statut);
    }

    public boolean nestPasVide() {
        return this.produits != null && !this.produits.isEmpty();
    }

    public void archive() {
        if (!this.estArchivable()) {
            throw new CommandeStatutNonComformeException(this.getStatut());
        }
    }

    public void vide() {
        if (!this.nestPasVide()) {
            throw new CommandeVideException();
        }
    }

    public void ajoutProduit() {
        if (!this.estServie()) {
            throw new CommandeStatutNonComformeException(this.getStatut());
        }
    }

    public boolean equals(Commande commande) {
        return this.id.equals(commande.id);
    }
}