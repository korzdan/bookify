package by.korzun.bookify.statistics.service;

import by.korzun.bookify.statistics.model.Statistics;
import by.korzun.bookify.statistics.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public Statistics find() {
        return statisticsRepository.findById(1L)
                .orElseThrow();
    }

    public void incOrders() {
        Statistics statistics = find();
        statistics.setOrdersNum(statistics.getOrdersNum() + 1);
        statisticsRepository.save(statistics);
    }

    public void incUsers() {
        Statistics statistics = find();
        statistics.setUsersNum(statistics.getUsersNum() + 1);
        statisticsRepository.save(statistics);
    }

    public void incBooks() {
        Statistics statistics = find();
        statistics.setBooksNum(statistics.getBooksNum() + 1);
        statisticsRepository.save(statistics);
    }
}
