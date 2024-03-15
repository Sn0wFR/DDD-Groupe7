package model.commande;

import java.util.List;

public interface CommandeRepository {

    Commande save(Commande commande);

    Commande findOne(Id id);

    List<Commande> findAll();

    void delete(Id id);

    List<Commande> findAllByIds(List<Id> ids);
}
