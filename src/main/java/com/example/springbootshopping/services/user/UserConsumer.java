package com.example.springbootshopping.services.user;

import com.example.springbootshopping.dao.user.UserDao;
import com.example.springbootshopping.models.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    @Qualifier("userRepo")
    private UserDao userDao;

    @Autowired
    public UserConsumer(UserDao userDao){
        this.userDao = userDao;
    }

    @RabbitListener(queues = "${rabbitmq.queue.user}")
    public void consume(User user, Channel channel, @Header(name = "amqp_deliveryTag") long deliveryTag) throws Exception{
        try{
            userDao.insertUser(user);
            channel.basicAck(deliveryTag,false);
        }catch (Exception e){
            channel.basicNack(deliveryTag, false, true);
            throw e;
        }
    }


}
