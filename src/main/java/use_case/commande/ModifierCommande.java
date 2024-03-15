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
import static model.commande.Commande.Statut.EN_ATTENTE;

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
        final Commande commande = commandeRepository.findOne(idCommande);
        if (commande.isCommandeServie()) {
            final Commande commandeReprise = modifierStatut(commande.getId(), EN_ATTENTE);
            return commandeReprise.withProduits(Stream.concat(commandeReprise.getProduits().stream(), idProduits.stream()).toList());
        }
        throw new CommandeStatutNonComformeException(commande.getStatut());
//        commandeRepository.save(commande);
    }

}
