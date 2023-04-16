package by.korzun.bookify.publisher.service;

import by.korzun.bookify.publisher.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> findByName(String name);
}
