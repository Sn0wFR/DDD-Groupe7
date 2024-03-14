package model.commande;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Value
public class Commande {

    @With
    List<Long> produits;
    @With
    Long table;
    @With
    Statut statut;
    Long id;

    public enum Statut {
        EN_ATTENTE,
        EN_COURS,
        PRETE,
        SERVIE,
        TERMINER
    }

}


