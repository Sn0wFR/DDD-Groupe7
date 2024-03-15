package model.commande;

public class CommandeVideException extends RuntimeException {
    public CommandeVideException() {
        super("La commande ne peut pas être vide");
    }
}
