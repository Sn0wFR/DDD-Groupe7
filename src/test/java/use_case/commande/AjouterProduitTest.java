package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import model.commande.CommandeStatutNonComformeException;
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

import static model.commande.Commande.Statut.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    Id idTable = new Id(2L);
    List<Id> idProduits = List.of(new Id(1L), new Id(2L), new Id(3L));
    List<Id> idProduits2 = List.of(new Id(1L), new Id(2L), new Id(3L), new Id(4L));
    Commande stub1 = Commande.builder().id(idCommande).produits(Collections.emptyList()).table(idTable).statut(SERVIE).build();

    @Test
    void givenIdCommandeIdProduits_whenAjouterProduit_shouldReturnCommandeWithIdCommandeIdProduits() {

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stub1);
        Mockito.when(modifierCommande.modifierStatut(any(), any()))
                .thenReturn(stub1.withStatut(EN_ATTENTE));

        Commande tested = ajouterProduit.ajouter(idCommande, idProduits);
        Assertions.assertEquals(idCommande, tested.getId());
        Assertions.assertIterableEquals(idProduits, tested.getProduits());

        Id newIdProduit = new Id(4L);

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(tested.withStatut(SERVIE));
        Mockito.when(modifierCommande.modifierStatut(any(), any()))
                .thenReturn(tested.withStatut(EN_ATTENTE));

        List<Id> produitsToAdd = List.of(newIdProduit);
        tested = ajouterProduit.ajouter(idCommande, produitsToAdd);

        Assertions.assertIterableEquals(idProduits2, tested.getProduits());
    }

    @Test
    void givenIncorrectStatut_whenAjouterProduit_shouldThrowCommandeStatutNonComformeException() {
        Mockito.when(commandeRepository.findOne(idCommande))
                .thenReturn(stub1.withStatut(EN_COURS));

        assertThrows(
                CommandeStatutNonComformeException.class,
                () -> ajouterProduit.ajouter(idCommande, idProduits));
    }
}
