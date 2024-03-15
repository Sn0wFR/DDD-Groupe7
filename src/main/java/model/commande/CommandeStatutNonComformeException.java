package model.commande;

public class CommandeStatutNonComformeException extends RuntimeException {
    public CommandeStatutNonComformeException(Commande.Statut statut) {
        super(statut.name());
    }
}
