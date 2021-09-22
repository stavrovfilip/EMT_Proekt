package com.example.sharedkernel.infra;

import com.example.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    /*interfejs za publikuvanje na eventi. Negovata konkretna implementacija e posebno vo modulot order-management.
    Soodvetno dokolku imame i drugi eventi i tie kje go implementiraat ovoj interfejs
    */
    void publish(DomainEvent event);
}
