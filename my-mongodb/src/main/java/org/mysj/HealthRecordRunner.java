package org.mysj;

import org.mysj.doc.HealthRecordDoc;
import org.mysj.doc.HealthRecordRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@lombok.extern.slf4j.Slf4j
@lombok.RequiredArgsConstructor
@Component
public class HealthRecordRunner implements CommandLineRunner {

    private final HealthRecordRepo healthRecordRepo;

    @Override
    public void run(String... args) throws Exception {
        var healthRecord = new HealthRecordDoc("Bob", "010101-01-0101", 17);
        healthRecordRepo.save(healthRecord);
        log.info("New document created with id {}", healthRecord.getId());
    }
}
