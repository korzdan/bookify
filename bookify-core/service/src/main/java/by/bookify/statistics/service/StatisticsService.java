package by.bookify.statistics.service;

import by.bookify.domain.statistics.model.Statistics;

public interface StatisticsService {
    Statistics find();

    Statistics incrementOrdersNum();

    Statistics incrementUsersNum();

    Statistics incrementBooksNum();
}
