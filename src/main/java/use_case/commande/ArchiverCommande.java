package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeArchiveRepository;
import model.commande.CommandeRepository;

import static model.commande.Commande.Statut.*;

public class ArchiverCommande {

    CommandeRepository commandeRepository;
    CommandeArchiveRepository commandeArchiveRepository;

    public ArchiverCommande(CommandeRepository commandeRepository, CommandeArchiveRepository commandeArchiveRepository){
        this.commandeRepository = commandeRepository;
        this.commandeArchiveRepository = commandeArchiveRepository;
    }

    public void archiverCommande(Long idCommande){
        Commande commande = commandeRepository.findOne(idCommande);
        commandeRepository.delete(commande.getId());
        commandeArchiveRepository.save(commande);
    }

}
