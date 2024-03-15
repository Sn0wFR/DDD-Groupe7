package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import model.commande.statut.*;
import model.commande.Id;


public class ModifierCommande {

    private final CommandeRepository commandeRepository;

    public ModifierCommande(CommandeRepository repository) {
        this.commandeRepository = repository;
    }

    public Commande modifierStatut(Id idCommande, boolean repriseCommande) {
        Commande commande = commandeRepository.findOne(idCommande);

        return commande.withStatut(StatutFactory.build(commande.getStatut(), repriseCommande));
    }

    public Commande modifierTable(Id idCommande, Id idTable) {
        return commandeRepository.findOne(idCommande).withTable(idTable);
    }
}
