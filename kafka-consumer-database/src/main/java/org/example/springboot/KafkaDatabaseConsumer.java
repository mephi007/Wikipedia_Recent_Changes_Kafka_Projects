package org.example.springboot;

import org.example.springboot.entity.WikipediaData;
import org.example.springboot.repository.WikipediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    private WikipediaDataRepository  wikipediaDataRepository;

    public KafkaDatabaseConsumer(WikipediaDataRepository repository){
        this.wikipediaDataRepository = repository;
    }

    @KafkaListener(topics = "wikipedia_recent_change", groupId = "group-id")
    public void consume(String eventMsg){
        logger.info(String.format("msg recieved -> %s", eventMsg));

        WikipediaData wikipediaData = new WikipediaData();
        wikipediaData.setWikiEventData(eventMsg);

        wikipediaDataRepository.save(wikipediaData);
    }
}
