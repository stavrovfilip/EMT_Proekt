package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.domain.exceptions.OrderIdNotExistException;
import com.example.ordermanagement.domain.exceptions.OrderItemIdNotExistException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.model.OrderItemId;
import com.example.ordermanagement.domain.repository.OrderRepository;
import com.example.ordermanagement.service.OrderService;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.OrderItemForm;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    /*
    Ovde go implementirame OrderService i pravime manipulacija so narackite koristejki go orderRepository.
    Isto taka ovde e i mestoto kade se publikuvaat nastani, i toa vo dva metodi.
     */

    private final OrderRepository orderRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;


    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"Order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));

        newOrder.getOrderItemList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getArticleId().getId(),item.getQuantity())));
        return newOrder.getId();

        /*
        koga imame nova naracka, za sekoj orderItem vo narackata publikuvame nastan deka istiot e kreiran, so sto aplikacija
        koja e pretplatena na ovoj topic ja dobiva porakata i soodvetno reagira, t.e vo ovoj slucaj articles aplikacijata go pokacuva
        brojot na prodazbi(sales) na dadeniot article vo order-itemot.
         */

    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new OrderIdNotExistException());
        order.addItem(orderItemForm.getArticle(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getArticle().getId().getId(),orderItemForm.getQuantity()));

        //isto taka koga vo dadena naracka dodavame nov produkt se publikuva nastan za soodvetno da se zgolemi brojot na prodazbi vo aplikacijata koja go slusa ovoj nastan

    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException {
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new OrderIdNotExistException());
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);


    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addItem(item.getArticle(),item.getQuantity()));
        return order;
    }

}
