package model.commande;

import java.util.List;

public interface CommandeArchiveRepository {

    Archive save(Commande commande);

    List<Archive> findAll();

    List<Archive> findAllByIds(List<Id> ids);

    Archive findOne(Id id);

}
