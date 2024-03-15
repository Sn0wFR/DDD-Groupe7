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

import static model.commande.Commande.Statut.EN_COURS;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ModifierCommandeTest {

    Id idCommande = new Id(1L);
    Long idTable = 2L;

    @Mock
    CommandeRepository commandeRepository;

    @InjectMocks
    ModifierCommande modifierCommande;

    Commande stub1 = Commande.builder().id(idCommande).produits(Collections.emptyList()).table(idTable).statut(EN_COURS).build();

    @Test
    void givenIdCommandeStatut_whenModifierStatut_shouldReturnCommandeWithIdCommandeStatut() {
        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stub1);

        Commande.Statut statut = Commande.Statut.PRETE;
        Commande commande = modifierCommande.modifierStatut(idCommande, statut);

        Assertions.assertEquals(idCommande, commande.getId());
        Assertions.assertEquals(statut, commande.getStatut());
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
