package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.exceptions.OrderIdNotExistException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.valueobjects.Article;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.OrderItemForm;
import com.example.ordermanagement.xport.client.ArticleClient;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private OrderService orderService;


    @Test
    public void testPlaceOrderWithRealData() {
        List<Article> articleList = articleClient.findAll();
        System.out.println(articleList);
    }

    @Test
    public void testPlaceOrderWithRealData2() {
        List<Article> articleList = articleClient.findAll();
        Article article1 = articleList.get(0);
        Article article2 = articleList.get(1);


        OrderItemForm oi1 = new OrderItemForm();
        oi1.setArticle(article1);
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setArticle(article2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = article1.getPrice().multiply(oi1.getQuantity()).add(article2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }


}
