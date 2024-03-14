package model.commande;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;
import model.AuditEntity;

import java.util.List;

import static model.commande.Commande.Statut.TERMINER;

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

    public boolean isCommandeArchivable(Commande commande) {
        return TERMINER.equals(commande.statut);
    }
}


