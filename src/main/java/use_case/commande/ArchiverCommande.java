package use_case.commande;

import model.commande.*;

public class ArchiverCommande {

    CommandeRepository commandeRepository;
    CommandeArchiveRepository commandeArchiveRepository;

    public ArchiverCommande(CommandeRepository commandeRepository, CommandeArchiveRepository commandeArchiveRepository) {
        this.commandeRepository = commandeRepository;
        this.commandeArchiveRepository = commandeArchiveRepository;
    }

    public Archive archiverCommande(Id idCommande) {
        Commande commande = commandeRepository.findOne(idCommande);
        commande.archive();
        commandeRepository.delete(commande.getId());
        return commandeArchiveRepository.save(commande);
    }
}
