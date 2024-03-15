package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import model.commande.Id;
import model.commande.statut.GeneriqueStatut;
import model.commande.statut.StatutEnAttente;
import model.commande.statut.StatutEnCours;
import model.commande.statut.StatutPrete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static model.commande.Commande.Statut.EN_COURS;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ModifierCommandeTest {

    Id idCommande = new Id(1L);
    Id idTable = new Id(2L);

    @Mock
    CommandeRepository commandeRepository;

    @InjectMocks
    ModifierCommande modifierCommande;

    Commande stub1 = Commande.builder().id(idCommande).produits(Collections.emptyList()).table(idTable).statut(new StatutEnCours()).build();

    @Test
    void givenIdCommandeStatut_whenModifierStatut_shouldReturnCommandeWithIdCommandeStatut() {
        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stub1);


        Commande commande = modifierCommande.modifierStatut(idCommande, false);

        Assertions.assertEquals(idCommande, commande.getId());
        Assertions.assertEquals(new StatutPrete(), commande.getStatut());
    }

    @Test
    void givenIdCommandeIdTable_whenModifierTable_shouldReturnCommandeWithIdCommandeIdTable() {

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stub1);

        Commande commande = modifierCommande.modifierTable(idCommande, idTable);

        Assertions.assertEquals(idCommande, commande.getId());
        Assertions.assertEquals(idTable, commande.getTable());
    }
}
