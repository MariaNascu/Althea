package com.fortech.service;

import com.fortech.entity.Publisher;
import java.util.List;

public interface PublisherService {
    void  createPublisher (String publisherName);

    public List<Publisher> findAll();
}
