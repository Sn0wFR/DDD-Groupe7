package model.commande;

import java.util.List;

public interface CommandeRepository {

    Commande save(Commande commande);

    Commande findOne(Long id);

    List<Commande> findAll();

    void delete(Long id);

    List<Commande> findAllByIds(List<Long> ids);
}
