package sample.kdohyeon.blog.service.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;
import sample.kdohyeon.blog.domain.statistics.BlogStatistic;
import sample.kdohyeon.blog.domain.statistics.CreateBlogStatistic;
import sample.kdohyeon.blog.port.in.statistics.UpsertBlogStatisticsUseCase;
import sample.kdohyeon.blog.port.in.statistics.command.UpsertBlogStatisticsCommand;
import sample.kdohyeon.blog.port.out.statistics.BlogStatisticsPort;

@Service
public class BlogStatisticsUpsertService implements UpsertBlogStatisticsUseCase {

    private final BlogStatisticsPort blogStatisticsPort;
    private final TransactionOperations writeTransactionOperations;

    public BlogStatisticsUpsertService(BlogStatisticsPort blogStatisticsPort,
                                       TransactionOperations writeTransactionOperations) {
        this.blogStatisticsPort = blogStatisticsPort;
        this.writeTransactionOperations = writeTransactionOperations;
    }

    @Override
    public void upsert(UpsertBlogStatisticsCommand command) {
        writeTransactionOperations.executeWithoutResult(status -> {
            var statOptional = blogStatisticsPort.findByKeyword(
                    command.getKeyword()
            );

            statOptional.ifPresentOrElse(
                    BlogStatistic::count,
                    () ->
                            blogStatisticsPort.save(
                                    BlogStatistic.create(
                                            CreateBlogStatistic.builder()
                                                    .keyword(command.getKeyword())
                                                    .build()
                                    ))
            );
        });
    }
}
