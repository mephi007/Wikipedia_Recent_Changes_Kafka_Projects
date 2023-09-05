package org.example.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> temp){
        this.kafkaTemplate = temp;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikipedia_recent_change";

        EventHandler eventHandler = new WikipediaChangesHandle(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
