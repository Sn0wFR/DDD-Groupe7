package use_case.commande;

import model.commande.Archive;
import model.commande.Commande;
import model.commande.CommandeArchiveRepository;
import model.commande.CommandeRepository;
import model.commande.Commande.Statut;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ArchiverCommandeTest {

    @Mock
    CommandeRepository commandeRepository;
    @Mock
    CommandeArchiveRepository commandeArchiveRepository;
    @InjectMocks
    ArchiverCommande archiverCommande;

    Long idCommande = 4L;
    Long idArchive = 1L;
    @Test
    void givenIdCommande_whenArchiverCommande_shouldReturnArchive(){

        Commande stubFind = Commande.builder().id(idCommande).statut(Statut.TERMINER).build();
        Archive stubArchive = Archive.builder().id(idArchive).build();

        Mockito.when(commandeRepository.findOne(any()))
                .thenReturn(stubFind);

        Mockito.when(commandeArchiveRepository.save(any()))
                .thenReturn(stubArchive);
        
        Archive archive = archiverCommande.archiverCommande(idCommande);

        Assertions.assertEquals(idArchive, archive.getId());


    }

}
