package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;

import static model.commande.Commande.Statut.EN_ATTENTE;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ModifierCommandeTest {

    Long idCommande = 1L;
    Long idTable = 2L;

    List<Long> idProduits = List.of(1L,2L,3L);
    @Mock
    CommandeRepository commandeRepository;

    @InjectMocks
    ModifierCommande modifierCommande;

    Commande stub1 = Commande.builder().id(idCommande).produits(Collections.emptyList()).table(idTable).statut(EN_ATTENTE).build();

    @Test
    void givenIdCommandeStatut_whenModifierStatut_shouldReturnCommandeWithIdCommandeStatut(){
        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stub1);

        Commande.Statut statut = Commande.Statut.PRETE;
        Commande commande = modifierCommande.modifierStatut(idCommande, statut);

        Assertions.assertEquals(idCommande, commande.getId());
        Assertions.assertEquals(statut, commande.getStatut());
    }

    @Test
    void givenIdCommandeIdTable_whenModifierTable_shouldReturnCommandeWithIdCommandeIdTable(){

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stub1);

        Commande commande = modifierCommande.modifierTable(idCommande, idTable);

        Assertions.assertEquals(idCommande, commande.getId());
        Assertions.assertEquals(idTable, commande.getTable());
    }

    @Test
    void givenIdCommandeIdProduits_whenAjouterProduit_shouldReturnCommandeWithIdCommandeIdProduits(){

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stub1);

        Commande tested = modifierCommande.ajouterProduit(idCommande, idProduits);
        Assertions.assertEquals(idCommande, tested.getId());
        Assertions.assertIterableEquals(idProduits, tested.getProduits());

        Long newIdProduit = 4L;

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(tested);

        List<Long> produitsToAdd = List.of(newIdProduit);
        tested = modifierCommande.ajouterProduit(idCommande, produitsToAdd);

        List<Long> expected = List.of(1L, 2L, 3L, 4L);
        Assertions.assertIterableEquals(expected, tested.getProduits());
    }
}
