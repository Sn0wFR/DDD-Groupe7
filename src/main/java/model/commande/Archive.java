package model.commande;

import lombok.*;
import model.AuditEntity;

@Value
@Builder
@RequiredArgsConstructor
public class Archive extends AuditEntity {
    Long id;

    public boolean equals(Archive archive) {
        return this.id.equals(archive.id);
    }
}
