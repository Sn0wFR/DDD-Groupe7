package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import model.commande.Id;

import java.util.List;

public class ListerCommande {

    private final CommandeRepository commandeRepository;

    public ListerCommande(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAll() {
        return commandeRepository.findAll();
    }

    public List<Commande> getAllByIds(List<Id> ids) {
        return commandeRepository.findAllByIds(ids);
    }

    public Commande getOne(Id id) {
        return commandeRepository.findOne(id);
    }
}
