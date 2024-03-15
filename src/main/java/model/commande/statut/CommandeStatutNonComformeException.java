package model.commande.statut;

public class CommandeStatutNonComformeException extends RuntimeException {
    public CommandeStatutNonComformeException(GeneriqueStatut statut) {
        super(statut.name.name());
    }
}
