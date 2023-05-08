package by.korzun.bookify.statistics.controller;

import by.korzun.bookify.statistics.model.Statistics;
import by.korzun.bookify.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<Statistics> findAll() {
        return ResponseEntity.ok(statisticsService.find());
    }
}
