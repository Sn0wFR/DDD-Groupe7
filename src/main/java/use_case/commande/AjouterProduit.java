package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import model.commande.Id;

import java.util.List;
import java.util.stream.Stream;


public class AjouterProduit {

    private final CommandeRepository commandeRepository;
    private final ModifierCommande modifierCommande;

    public AjouterProduit(CommandeRepository commandeRepository, ModifierCommande modifierCommande) {
        this.commandeRepository = commandeRepository;
        this.modifierCommande = modifierCommande;
    }

    public Commande ajouter(Id idCommande, List<Id> idProduits) {
        final Commande commande = commandeRepository.findOne(idCommande);
        commande.ajoutProduit();
        final Commande commandeReprise = modifierCommande.modifierStatut(commande.getId(), true);
        return commandeReprise.withProduits(Stream.concat(commandeReprise.getProduits().stream(), idProduits.stream()).toList());

    }
}
