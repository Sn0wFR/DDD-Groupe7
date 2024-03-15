package use_case.commande;

import model.commande.Archive;
import model.commande.CommandeArchiveRepository;
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
public class ListerArchiveTest {

    @InjectMocks
    ListerArchive listerArchive;

    @Mock
    CommandeArchiveRepository commandeArchiveRepository;

    Id id1 = new Id(1L);
    Id id2 = new Id(2L);
    List<Archive> archives = List.of(new Archive(id1), new Archive(id2));
    Archive archive = new Archive(id1);

    @Test
    void whenFindAll_ShouldReturnAllArchives() {
        when(commandeArchiveRepository.findAll())
                .thenReturn(archives);

        List<Archive> tested = listerArchive.getAll();

        assertEquals(archives, tested);
    }

    @Test
    void givenIdList_whenFindAllByIds_shouldReturnArchives() {
        when(commandeArchiveRepository.findAllByIds(any()))
                .thenReturn(archives);

        List<Archive> tested = listerArchive.getAllByIds(List.of(id1.id(), id2.id()));

        assertEquals(archives, tested);
    }

    @Test
    void givenId_whenFindOne_shouldReturnArchive() {
        when(commandeArchiveRepository.findOne(any()))
                .thenReturn(archive);

        Archive tested = listerArchive.getOne(id1.id());

        assertEquals(archive, tested);
    }
}
