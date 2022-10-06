package jmsqueue.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jmsqueue.app.model.Product;
import jmsqueue.app.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @Autowired
    private Service service;

    @PostMapping("/send")
    public Product sendName(@RequestBody Product product) {
        return service.sendProduct(product);
    }

}
