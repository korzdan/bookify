package by.bookify.statistics.repository;

import by.bookify.domain.statistics.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
