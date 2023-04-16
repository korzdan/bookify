package by.korzun.bookify.publisher.service;

import by.korzun.bookify.publisher.model.Publisher;
import by.korzun.bookify.publisher.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPublisherService implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findByName(String name) {
        return publisherRepository.findByName(name);
    }
}
