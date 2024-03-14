package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AuditEntity {

    LocalDateTime heureDebut;
    LocalDateTime heureDeFin;
    String serveurCreation;
}
