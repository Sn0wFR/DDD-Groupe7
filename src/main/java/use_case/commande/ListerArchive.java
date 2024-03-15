package use_case.commande;

import model.commande.Archive;
import model.commande.CommandeArchiveRepository;
import model.commande.Id;

import java.util.List;

public class ListerArchive {

    private final CommandeArchiveRepository commandeArchiveRepository;

    public ListerArchive(CommandeArchiveRepository commandeArchiveRepository) {
        this.commandeArchiveRepository = commandeArchiveRepository;
    }

    public List<Archive> getAll() {
        return commandeArchiveRepository.findAll();
    }

    public List<Archive> getAllByIds(List<Id> ids) {
        return commandeArchiveRepository.findAllByIds(ids);
    }

    public Archive getOne(Id id) {
        return commandeArchiveRepository.findOne(id);
    }
}
