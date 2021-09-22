package com.example.ordermanagement.infra;

import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    /*
    Ovaa klasa ni pretstavuva implementacija na generalniot interfejs za publikuvanje na nastani.
    Znaeme deka ovoj modul order-management ni e Producer na daden topic, pa ovde se vrsi publikuvanje na nastan
    koristejki go kafka templejtot.
     */

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(),event.toJson());
    }

}
