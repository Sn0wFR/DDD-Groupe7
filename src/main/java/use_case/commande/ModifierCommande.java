package use_case.commande;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import model.commande.Commande;
import model.commande.CommandeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.List.copyOf;

public class ModifierCommande {

    private final CommandeRepository commandeRepository;

    public ModifierCommande(CommandeRepository repository) {
        this.commandeRepository = repository;
    }

    public Commande modifierStatut(Long idCommande, Commande.Statut statut) {
        //return commandeRepository.save(commandeRepository.findOne(idCommande).withStatut(statut));
        return commandeRepository.findOne(idCommande).withStatut(statut);
    }

    public Commande modifierTable(Long idCommande, Long idTable) {
        //return commandeRepository.save(commandeRepository.findOne(idCommande).withTable(idTable));
        return commandeRepository.findOne(idCommande).withTable(idTable);
    }

    public Commande ajouterProduit(Long idCommande, List<Long> idProduits) {
        Commande commande = commandeRepository.findOne(idCommande);
        return commande.withProduits(Stream.concat(commande.getProduits().stream(), idProduits.stream()).toList());
//        commandeRepository.save(commande);
    }

}
