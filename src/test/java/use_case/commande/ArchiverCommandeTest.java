package use_case.commande;

import model.commande.CommandeArchiveRepository;
import model.commande.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArchiverCommandeTest {

    @Mock
    CommandeRepository commandeRepository;
    @Mock
    CommandeArchiveRepository commandeArchiveRepository;
    @InjectMocks
    ArchiverCommande archiverCommande;

    Long idCommande = 1L;
    @Test
    void givenIdCommande_whenArchiverCommande_shouldArchiveCommande(){


        archiverCommande.archiverCommande(idCommande);


    }

}
