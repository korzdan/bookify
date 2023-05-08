package by.korzun.bookify.statistics.repository;

import by.korzun.bookify.statistics.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
