package org.mysj.doc;

import org.springframework.data.annotation.*;
import org.springframework.data.domain.Auditable;

import java.time.Instant;
import java.util.*;

@lombok.Setter
@lombok.ToString
public abstract class AbstractMongoAuditable implements Auditable<String, String, Instant> {

    @Id
    private String id;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant lastModifiedDate;

    @Override
    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    @Override
    public Optional<Instant> getCreatedDate() {
        return Optional.ofNullable(createdDate);
    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.ofNullable(lastModifiedBy);
    }

    @Override
    public Optional<Instant> getLastModifiedDate() {
        return Optional.ofNullable(lastModifiedDate);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return getId() == null;
    }
}
