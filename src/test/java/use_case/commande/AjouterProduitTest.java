package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import model.commande.Id;
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
import static model.commande.Commande.Statut.SERVIE;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AjouterProduitTest {

    @InjectMocks
    AjouterProduit ajouterProduit;
    @Mock
    CommandeRepository commandeRepository;
    @Mock
    ModifierCommande modifierCommande;

    Id idCommande = new Id(1L);
    Long idTable = 2L;
    List<Long> idProduits = List.of(1L, 2L, 3L);
    Commande stub1 = Commande.builder().id(idCommande).produits(Collections.emptyList()).table(idTable).statut(SERVIE).build();

    @Test
    void givenIdCommandeIdProduits_whenAjouterProduit_shouldReturnCommandeWithIdCommandeIdProduits() {

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stub1);
        Mockito.when(modifierCommande.modifierStatut(any(), any()))
                .thenReturn(stub1.withStatut(EN_ATTENTE));

        Commande tested = ajouterProduit.ajouter(idCommande.id(), idProduits);
        Assertions.assertEquals(idCommande, tested.getId());
        Assertions.assertIterableEquals(idProduits, tested.getProduits());

        Long newIdProduit = 4L;

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(tested.withStatut(SERVIE));
        Mockito.when(modifierCommande.modifierStatut(any(), any()))
                .thenReturn(tested.withStatut(EN_ATTENTE));

        List<Long> produitsToAdd = List.of(newIdProduit);
        tested = ajouterProduit.ajouter(idCommande.id(), produitsToAdd);

        List<Long> expected = List.of(1L, 2L, 3L, 4L);
        Assertions.assertIterableEquals(expected, tested.getProduits());
    }

}
