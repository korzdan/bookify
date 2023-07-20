package by.bookify.statistics.service;

import by.bookify.book.model.Book;
import by.bookify.book.model.BookLanguage;
import by.bookify.statistics.exception.StatisticsNotFoundException;
import by.bookify.statistics.model.Statistics;
import by.bookify.statistics.repository.StatisticsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultStatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks
    private DefaultStatisticsService statisticsService;

    @Test
    void find_statisticsExist_true() {
        Long id = 1L;
        Statistics statistics = new Statistics()
                .setBooksNum(1L)
                .setUsersNum(2L)
                .setOrdersNum(3L);

        when(statisticsRepository.findById(id)).thenReturn(Optional.of(statistics));
        Statistics actualStatistics = statisticsService.find();

        assertEquals(1L, actualStatistics.getBooksNum());
        assertEquals(2L, actualStatistics.getUsersNum());
        assertEquals(3L, actualStatistics.getOrdersNum());
    }

    @Test
    void find_statisticsDoesNotExist_thrownException() {
        Long id = 1L;

        when(statisticsRepository.findById(id)).thenReturn(Optional.empty());
        StatisticsNotFoundException actualException = assertThrows(StatisticsNotFoundException.class,
                () -> statisticsService.find());


        assertEquals("Error occurred when getting statistics.", actualException.getMessage());
    }

    @Test
    void incrementOrdersNum_checkIncrement_correct() {
        Long id = 1L;
        Long currentOrderNum = 3L;
        Statistics statistics = new Statistics()
                .setOrdersNum(currentOrderNum);

        when(statisticsRepository.findById(id)).thenReturn(Optional.of(statistics));
        when(statisticsRepository.save(statistics)).thenReturn(statistics);
        Long expectedOrderNum = ++currentOrderNum;

        Statistics actualStatistics = statisticsService.incrementOrdersNum();

        assertEquals(expectedOrderNum, actualStatistics.getOrdersNum());
    }

    @Test
    void incrementUsersNum_checkIncrement_correct() {
        Long id = 1L;
        Long currentUsersNum = 2L;
        Statistics statistics = new Statistics()
                .setUsersNum(currentUsersNum);

        when(statisticsRepository.findById(id)).thenReturn(Optional.of(statistics));
        when(statisticsRepository.save(statistics)).thenReturn(statistics);
        Long expectedUsersNum = ++currentUsersNum;

        Statistics actualStatistics = statisticsService.incrementUsersNum();

        assertEquals(expectedUsersNum, actualStatistics.getUsersNum());
    }

    @Test
    void incrementBooksNum_checkIncrement_correct() {
        Long id = 1L;
        Long currentBooksNum = 1L;
        Statistics statistics = new Statistics()
                .setBooksNum(currentBooksNum);

        when(statisticsRepository.findById(id)).thenReturn(Optional.of(statistics));
        when(statisticsRepository.save(statistics)).thenReturn(statistics);
        Long expectedBooksNum = ++currentBooksNum;

        Statistics actualStatistics = statisticsService.incrementBooksNum();

        assertEquals(expectedBooksNum, actualStatistics.getBooksNum());
    }
}
