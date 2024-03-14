package model.commande;

public interface CommandeRepository {

    Commande save(Commande commande);

    Commande findOne(Long id);
}
