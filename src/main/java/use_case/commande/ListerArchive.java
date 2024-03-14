package use_case.commande;

import model.commande.Archive;
import model.commande.CommandeArchiveRepository;
import model.commande.CommandeRepository;

import java.util.List;

public class ListerArchive {

    private final CommandeArchiveRepository commandeArchiveRepository;

    public ListerArchive(CommandeArchiveRepository commandeArchiveRepository) {
        this.commandeArchiveRepository = commandeArchiveRepository;
    }

    public List<Archive> getAll() {
        return commandeArchiveRepository.findAll();
    }

    public List<Archive> getAllByIds(List<Long> ids) {
        return commandeArchiveRepository.findAllByIds(ids);
    }

    public Archive getOne(Long id) {
        return commandeArchiveRepository.findOne(id);
    }
}
