package model.commande;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import model.AuditEntity;

@Value
@Builder
@RequiredArgsConstructor
public class Archive extends AuditEntity {
    Id id;

    public boolean equals(Archive archive) {
        return this.id.equals(archive.id);
    }
}
