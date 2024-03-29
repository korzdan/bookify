package by.bookify.statistics.service;

import by.bookify.domain.statistics.exception.StatisticsNotFoundException;
import by.bookify.domain.statistics.model.Statistics;
import by.bookify.statistics.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultStatisticsService implements StatisticsService {

    private static final Long STATISTICS_ID = 1L;

    private final StatisticsRepository statisticsRepository;

    @Override
    public Statistics find() {
        return statisticsRepository.findById(STATISTICS_ID)
                .orElseThrow(() -> new StatisticsNotFoundException("Error occurred when getting statistics."));
    }

    @Override
    public Statistics incrementOrdersNum() {
        Statistics statistics = find();
        statistics.setOrdersNum(statistics.getOrdersNum() + 1);
        return statisticsRepository.save(statistics);
    }

    @Override
    public Statistics incrementUsersNum() {
        Statistics statistics = find();
        statistics.setUsersNum(statistics.getUsersNum() + 1);
        return statisticsRepository.save(statistics);
    }

    @Override
    public Statistics incrementBooksNum() {
        Statistics statistics = find();
        statistics.setBooksNum(statistics.getBooksNum() + 1);
        return statisticsRepository.save(statistics);
    }
}
