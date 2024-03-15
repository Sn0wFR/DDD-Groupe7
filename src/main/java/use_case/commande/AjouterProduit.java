package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;

import java.util.List;
import java.util.stream.Stream;

import static model.commande.Commande.Statut.EN_ATTENTE;

public class AjouterProduit {

    private final CommandeRepository commandeRepository;
    private final ModifierCommande modifierCommande;

    public AjouterProduit(CommandeRepository commandeRepository, ModifierCommande modifierCommande) {
        this.commandeRepository = commandeRepository;
        this.modifierCommande = modifierCommande;
    }

    public Commande ajouter(Long idCommande, List<Long> idProduits) {
        final Commande commande = commandeRepository.findOne(idCommande);
        commande.ajoutProduit();
        final Commande commandeReprise = modifierCommande.modifierStatut(commande.getId(), EN_ATTENTE);
        return commandeReprise.withProduits(Stream.concat(commandeReprise.getProduits().stream(), idProduits.stream()).toList());

    }
}
