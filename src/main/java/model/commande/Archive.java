package model.commande;

import lombok.*;
import model.AuditEntity;

@Value
@Builder
@RequiredArgsConstructor
public class Archive extends AuditEntity {
    Long id;
}
