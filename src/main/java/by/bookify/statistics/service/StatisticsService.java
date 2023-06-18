package by.bookify.statistics.service;

import by.bookify.statistics.model.Statistics;

public interface StatisticsService {
    Statistics find();

    void incrementOrdersNum();

    void incrementUsersNum();

    void incrementBooksNum();
}
