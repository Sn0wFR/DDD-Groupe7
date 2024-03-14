package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListerCommandeTest {

    @InjectMocks
    ListerCommande listerCommande;
    @Mock
    CommandeRepository commandeRepository;

    List<Commande> commandes = List.of(Commande.builder().id(1L).build(), Commande.builder().id(2L).build());
    Commande commande = Commande.builder().id(1L).build();

    @Test
    void whenGetAll_shouldReturnAllCommandes() {
        when(commandeRepository.findAll())
                .thenReturn(commandes);

        List<Commande> tested = listerCommande.getAll();

        assertEquals(commandes, tested);
    }

    @Test
    void givenIdCommandes_whenGetAllByIds_shouldReturnCommandes() {
        when(commandeRepository.findAllByIds(any()))
                .thenReturn(commandes);

        List<Commande> tested = listerCommande.getAllByIds(List.of(1L, 2L));

        assertEquals(commandes, tested);
    }

    @Test
    void givenIdCommande_whenGetById_shouldReturnCommande() {
        when(commandeRepository.findOne(any()))
                .thenReturn(commande);

        Commande tested = listerCommande.getOne(1L);

        assertEquals(commande, tested);
    }
}
