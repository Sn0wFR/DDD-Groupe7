package use_case.commande;


import model.commande.Commande;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static model.commande.Commande.Statut.*;

@ExtendWith(MockitoExtension.class)
public class CreerCommandeTest {

    Long idTable = 1L;
    List<Long> idProduits = List.of(1L,2L);
    CreerCommande creerCommande = new CreerCommande();

    @Test
    void givenIdProduitIdTable_whenCreationCommande_shouldReturnCommandeWithIdProduitsIdTableStatutEnAttente() {
        Commande commande = creerCommande.creationCommande(idProduits, idTable);
        Assertions.assertEquals(idTable, commande.getTable());
        Assertions.assertIterableEquals(idProduits, commande.getProduits());
        Assertions.assertEquals(EN_ATTENTE, commande.getStatut());

    }

}
