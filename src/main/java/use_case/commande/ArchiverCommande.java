package use_case.commande;

import model.commande.Archive;
import model.commande.Commande;
import model.commande.CommandeArchiveRepository;
import model.commande.CommandeRepository;

public class ArchiverCommande {

    CommandeRepository commandeRepository;
    CommandeArchiveRepository commandeArchiveRepository;

    public ArchiverCommande(CommandeRepository commandeRepository, CommandeArchiveRepository commandeArchiveRepository){
        this.commandeRepository = commandeRepository;
        this.commandeArchiveRepository = commandeArchiveRepository;
    }

    public Archive archiverCommande(Long idCommande){
        Commande commande = commandeRepository.findOne(idCommande);
        commande.archive();
        commandeRepository.delete(commande.getId().id());
        return commandeArchiveRepository.save(commande);
    }
}
