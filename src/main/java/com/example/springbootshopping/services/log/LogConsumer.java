package com.example.springbootshopping.services.log;

import com.example.springbootshopping.dao.log.LogDao;
import com.example.springbootshopping.models.Log;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class LogConsumer {

    private LogDao logDao;

    public LogConsumer(@Qualifier("logRepo") LogDao logDao){
        this.logDao = logDao;
    }

    @RabbitListener(queues = "${rabbitmq.queue.log}")
    public void consume(Log log, Channel channel, @Header("amqp_deliveryTag") long deliveryTag) throws Exception{
        try{
            logDao.insertLog(log);
            channel.basicAck(deliveryTag, false);
        }catch (Exception e){
            channel.basicNack(deliveryTag, false, true);
            throw e;
        }
    }
}
