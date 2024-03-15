package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;

import java.util.List;

public class ListerCommande {

    private final CommandeRepository commandeRepository;

    public ListerCommande(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAll() {
        return commandeRepository.findAll();
    }

    public List<Commande> getAllByIds(List<Long> ids) {
        return commandeRepository.findAllByIds(ids);
    }

    public Commande getOne(Long id) {
        return commandeRepository.findOne(id);
    }
}
