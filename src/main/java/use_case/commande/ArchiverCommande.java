package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;

import static model.commande.Commande.Statut.*;

public class ArchiverCommande {

    CommandeRepository commandeRepository;

    public ArchiverCommande(CommandeRepository commandeRepository){
        this.commandeRepository = commandeRepository;
    }

    public Commande archiverCommande(Long idCommande){
        return commandeRepository.findOne(idCommande).withStatut(TERMINER);



    }

}
