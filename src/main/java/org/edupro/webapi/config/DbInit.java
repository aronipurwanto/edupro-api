package org.edupro.webapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.Constant;
import org.edupro.webapi.model.entity.LookupEntity;
import org.edupro.webapi.repository.LookupRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.edupro.webapi.constant.Constant.RESOURCE_TYPE;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final LookupRepo lookupRepo;

    @Override
    public void run(String... args) throws Exception {
        initResourceType();
    }

    private void initResourceType(){
        int count = lookupRepo.countAllByGroup(RESOURCE_TYPE);
        if(count > 0){
            return;
        }
        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(UUID.randomUUID().toString().toUpperCase(), Constant.ResourceType.TYPE, Constant.ResourceType.MATERIAL,Constant.ResourceType.MATERIAL,1),
                new LookupEntity(UUID.randomUUID().toString().toUpperCase(), Constant.ResourceType.TYPE, Constant.ResourceType.QUESTION,Constant.ResourceType.QUESTION,2),
                new LookupEntity(UUID.randomUUID().toString().toUpperCase(), Constant.ResourceType.TYPE, Constant.ResourceType.QUIZ,Constant.ResourceType.QUIZ,3)
        );
        try {
            lookupRepo.saveAll(lookupEntities);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
