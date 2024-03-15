package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import model.commande.CommandeStatutNonComformeException;
import model.commande.Id;


public class ModifierCommande {

    private final CommandeRepository commandeRepository;

    public ModifierCommande(CommandeRepository repository) {
        this.commandeRepository = repository;
    }

    public Commande modifierStatut(Id idCommande, Commande.Statut statut) {
        Commande commande = commandeRepository.findOne(idCommande.id());
        return switch (statut) {
            case EN_ATTENTE, TERMINER -> {
                if (commande.estServie()) {
                    yield commande.withStatut(statut);
                }
                throw new CommandeStatutNonComformeException(commande.getStatut());
            }
            case EN_COURS -> {
                if (commande.estPrise()) {
                    yield commande.withStatut(statut);
                }
                throw new CommandeStatutNonComformeException(commande.getStatut());
            }
            case PRETE -> {
                if (commande.estEnCuisine()) {
                    yield commande.withStatut(statut);
                }
                throw new CommandeStatutNonComformeException(commande.getStatut());
            }
            case SERVIE -> {
                if (commande.estPrete()) {
                    yield commande.withStatut(statut);
                }
                throw new CommandeStatutNonComformeException(commande.getStatut());
            }
            default -> throw new CommandeStatutNonComformeException(statut);
        };
    }

    public Commande modifierTable(Long idCommande, Long idTable) {
        return commandeRepository.findOne(idCommande).withTable(idTable);
    }
}
