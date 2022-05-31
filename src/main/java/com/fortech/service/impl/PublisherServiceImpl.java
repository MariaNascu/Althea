package com.fortech.service.impl;

import com.fortech.entity.Publisher;
import com.fortech.repository.PublisherRepository;
import com.fortech.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void createPublisher(String publisherName) {

        Publisher newPublisher = new Publisher();
        newPublisher.setPublisherName(publisherName);

        publisherRepository.save(newPublisher);
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }
}
