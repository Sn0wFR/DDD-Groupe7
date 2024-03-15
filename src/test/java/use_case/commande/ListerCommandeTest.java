package use_case.commande;

import model.commande.Commande;
import model.commande.CommandeRepository;
import model.commande.Id;
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

    Id id1 = new Id(1L);
    Id id2 = new Id(2L);

    List<Commande> commandes = List.of(Commande.builder().id(id1).build(), Commande.builder().id(id2).build());
    Commande commande = Commande.builder().id(id1).build();

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

        List<Commande> tested = listerCommande.getAllByIds(List.of(id1, id2));

        assertEquals(commandes, tested);
    }

    @Test
    void givenIdCommande_whenGetById_shouldReturnCommande() {
        when(commandeRepository.findOne(any()))
                .thenReturn(commande);

        Commande tested = listerCommande.getOne(id1);

        assertEquals(commande, tested);
    }
}
