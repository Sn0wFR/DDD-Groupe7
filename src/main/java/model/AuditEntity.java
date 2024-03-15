package model;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AuditEntity {

    LocalDateTime heureDebut;
    LocalDateTime heureDeFin;
    LocalDateTime heureMiseAJour;
    String serveurCreation;
    String serveurMiseAJour;
}
