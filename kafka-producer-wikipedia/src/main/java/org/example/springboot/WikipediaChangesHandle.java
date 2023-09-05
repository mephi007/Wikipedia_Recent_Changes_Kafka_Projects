package org.example.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikipediaChangesHandle implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(WikipediaChangesHandle.class);

    private KafkaTemplate<String, String> template;
    private String topic;

    public WikipediaChangesHandle(KafkaTemplate<String, String> temp, String topic){
        this.template = temp;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        logger.info(String.format("event data -> %s", messageEvent.getData()));
        template.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
