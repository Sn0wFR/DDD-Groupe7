package model.commande;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import model.AuditEntity;
import model.commande.statut.*;

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
    GeneriqueStatut statut;

    public enum Statut {
        EN_ATTENTE("En attente"),
        EN_COURS("En cours"),
        PRETE("Prete"),
        SERVIE("Servie"),
        TERMINER("Terminer");

        final String label;

        Statut(String label) {
            this.label = label;
        }
    }

    public boolean estArchivable() {
        return this.statut instanceof StatutTerminer;
    }

    public boolean estPrise() {
        return this.statut instanceof StatutEnAttente;
    }

    public boolean estEnCuisine() {
        return this.statut instanceof StatutEnCours;
    }

    public boolean estPrete() {
        return this.statut instanceof StatutPrete;
    }

    public boolean estServie() {
        return this.statut instanceof StatutServie;
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