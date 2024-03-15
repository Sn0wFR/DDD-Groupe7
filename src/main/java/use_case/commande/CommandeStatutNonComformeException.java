package use_case.commande;

import model.commande.Commande;

public class CommandeStatutNonComformeException extends RuntimeException {
    public CommandeStatutNonComformeException(Commande.Statut statut) {
        super(statut.name());
    }
}
