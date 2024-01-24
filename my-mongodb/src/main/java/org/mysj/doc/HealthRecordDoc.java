package org.mysj.doc;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@lombok.EqualsAndHashCode(callSuper = true)
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
@Document
public class HealthRecordDoc extends AbstractMongoAuditable {

    private String name;
    @Indexed
    private String patientId;
    private int age;
}
