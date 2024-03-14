package model.commande;

public class FakeCommandeRepository implements CommandeRepository {
    @Override
    public Commande save(Commande commande) {
        return null;
    }

    @Override
    public Commande findOne(Long id) {
        return null;
    }
}
