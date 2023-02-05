package sample.kdohyeon.blog.port.in.statistics;

import sample.kdohyeon.blog.port.in.statistics.command.UpsertBlogStatisticsCommand;

public interface UpsertBlogStatisticsUseCase {
    void upsert(UpsertBlogStatisticsCommand command);
}
