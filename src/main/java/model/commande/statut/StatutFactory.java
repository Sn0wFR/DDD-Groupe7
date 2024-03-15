package model.commande.statut;

import static model.commande.Commande.Statut.SERVIE;

public class StatutFactory {

    public static GeneriqueStatut build(GeneriqueStatut statut, boolean repriseCommande) {
        if (repriseCommande && SERVIE.equals(statut.name)) {
            return new StatutEnAttente();
        } else if (repriseCommande) {
            throw new CommandeStatutNonComformeException(statut);
        }
        return switch (statut.getName()) {
            case EN_ATTENTE -> new StatutEnCours();
            case EN_COURS -> new StatutPrete();
            case PRETE -> new StatutServie();
            case SERVIE -> new StatutTerminer();
            default -> throw new IllegalStateException("Unexpected value: " + statut.getName());
        };

    }
}
