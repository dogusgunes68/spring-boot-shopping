package com.example.springbootshopping.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // EXCHANGES
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // QUEUES
    @Value("${rabbitmq.queue.product}")
    private String productQueue;

    @Value("${rabbitmq.queue.order}")
    private String orderQueue;

    @Value("${rabbitmq.queue.user}")
    private String userQueue;

    @Value("${rabbitmq.queue.log}")
    private String logQueue;

    // ROUTING KEYS
    @Value("${rabbitmq.routing.product}")
    private String productRoutingKey;

    @Value("${rabbitmq.routing.order}")
    private String orderRoutingKey;

    @Value("${rabbitmq.routing.user}")
    private String userRoutingKey;

    @Value("${rabbitmq.routing.log}")
    private String logRoutingKey;

    @Bean
    public Queue productQueue(){
        return new Queue(productQueue);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueue);
    }

    @Bean
    public Queue userQueue(){
        return new Queue(userQueue);
    }

    @Bean
    public Queue logQueue(){
        return new Queue(logQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding productBinding(){
        return BindingBuilder.bind(productQueue())
                .to(exchange())
                .with(productRoutingKey);
    }

    @Bean
    public Binding orderBinding(){
        return BindingBuilder.bind(orderQueue())
                .to(exchange())
                .with(orderRoutingKey);
    }

    @Bean
    public Binding userBinding(){
        return BindingBuilder.bind(userQueue())
                .to(exchange())
                .with(userRoutingKey);
    }

    @Bean
    public Binding logBinding(){
        return BindingBuilder.bind((logQueue()))
                .to(exchange())
                .with(logRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
