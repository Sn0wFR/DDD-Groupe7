package use_case.commande;


import model.commande.Commande;
import model.commande.CommandeVideException;
import model.commande.Id;
import model.commande.statut.StatutEnAttente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static model.commande.Commande.Statut.EN_ATTENTE;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CreerCommandeTest {

    Id idTable = new Id(1L);
    List<Id> idProduits = List.of(new Id(1L), new Id(2L));
    CreerCommande creerCommande = new CreerCommande();

    @Test
    void givenIdProduitIdTable_whenCreationCommande_shouldReturnCommandeWithIdProduitsIdTableStatutEnAttente() {
        Commande commande = creerCommande.creationCommande(idProduits, idTable);

        Assertions.assertEquals(idTable, commande.getTable());
        Assertions.assertIterableEquals(idProduits, commande.getProduits());
        Assertions.assertEquals(new StatutEnAttente(), commande.getStatut());
    }

    @Test
    void givenNoProduits_whenCreationCommande_shouldThrowCommandeVideException() {
        assertThrows(CommandeVideException.class,
                () -> creerCommande.creationCommande(List.of(), idTable));
    }
}
