package com.example.articles.xport.events;

import com.example.articles.domain.models.ArticleId;
import com.example.articles.service.ArticleService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.domain.events.orders.OrderItemRemoved;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleEventListener {

    /*Ovaa klasa ni pretstavuva EventListener. Odnosno ovaa aplikacija/modul articles e pretplaten na dve topics a toa se
    kreiranje na order-item i brisenje na order-item od dadena naracka. Koga kje se primi vakviot nastan od strana na Kafka message
    brokerot se pravat soodvetnite promeni so servisnata logika odnosno se zgolemuva ili namaluva brojot na salses vo zavisnost od toa
    koj metod e povikan, dali se slucil nastan na kreiranje na order-item ili na brisenje.

     */

    private final ArticleService articleService;

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "articles")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage,OrderItemCreated.class);
            articleService.orderItemCreated(ArticleId.of(event.getArticleId()), event.getQuantity());
        } catch (Exception e){

        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage,OrderItemRemoved.class);
            articleService.orderItemRemoved(ArticleId.of(event.getArticleId()), event.getQuantity());
        } catch (Exception e){

        }

    }

}


