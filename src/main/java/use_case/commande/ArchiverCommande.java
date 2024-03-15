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
        if (!commande.isCommandeArchivable()) {
            throw new CommandeStatutNonComformeException(commande.getStatut());
        }
        commandeRepository.delete(commande.getId());
        return commandeArchiveRepository.save(commande);
    }

}
