package com.fortech.controllers;

import com.fortech.entity.Publisher;
import com.fortech.service.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myapi")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/createPublisher")
    public void createPublisher(@RequestParam(name = "publisherName") String publisherName) {
        publisherService.createPublisher(publisherName);
    }
    @GetMapping("/getAllPublisher")
    public List<Publisher> findAll() {
        return publisherService.findAll();
    }

}
