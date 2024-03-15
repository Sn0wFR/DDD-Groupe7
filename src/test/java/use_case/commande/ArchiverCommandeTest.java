package use_case.commande;

import model.commande.*;
import model.commande.Commande.Statut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static model.commande.Commande.Statut.EN_COURS;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArchiverCommandeTest {

    @Mock
    CommandeRepository commandeRepository;
    @Mock
    CommandeArchiveRepository commandeArchiveRepository;
    @InjectMocks
    ArchiverCommande archiverCommande;

    Id idCommande = new Id(1L);
    Id idArchive = new Id(1L);
    Commande stubFind = Commande.builder().id(idCommande).statut(Statut.TERMINER).build();
    Archive stubArchive = Archive.builder().id(idArchive).build();


    @Test
    void givenIdCommande_whenArchiverCommande_shouldReturnArchive() {
        when(commandeRepository.findOne(any()))
                .thenReturn(stubFind);

        when(commandeArchiveRepository.save(any()))
                .thenReturn(stubArchive);

        Archive archive = archiverCommande.archiverCommande(idCommande);

        Assertions.assertEquals(idArchive, archive.getId());
    }

    @Test
    void givenWrongStatut_whenArchiverCommande_shouldThrowCommandeNonComformeException() {
        when(commandeRepository.findOne(any()))
                .thenReturn(stubFind.withStatut(EN_COURS));

        assertThrows(
                CommandeStatutNonComformeException.class,
                () -> archiverCommande.archiverCommande(idCommande));
    }
}
