package jmsqueue.app.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jmsqueue.app.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue queue;

    public Product sendProduct(Product product) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String productAsJson = mapper.writeValueAsString(product);
            jmsTemplate.convertAndSend(queue, productAsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

}
