package sample.kdohyeon.blog.port.input.statistics;

import sample.kdohyeon.blog.port.input.statistics.command.UpsertBlogStatisticsCommand;

public interface UpsertBlogStatisticsUseCase {
    void increaseCountOrCreate(UpsertBlogStatisticsCommand command);
}
