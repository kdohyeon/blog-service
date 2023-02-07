package sample.kdohyeon.blog.service.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;
import sample.kdohyeon.blog.domain.statistics.BlogStatistic;
import sample.kdohyeon.blog.domain.statistics.CreateBlogStatistic;
import sample.kdohyeon.blog.port.input.statistics.UpsertBlogStatisticsUseCase;
import sample.kdohyeon.blog.port.input.statistics.command.UpsertBlogStatisticsCommand;
import sample.kdohyeon.blog.port.output.statistics.BlogStatisticsPort;

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
    public void increaseCountOrCreate(UpsertBlogStatisticsCommand command) {
        writeTransactionOperations.executeWithoutResult(status -> {
            var statOptional = blogStatisticsPort.findByKeyword(
                    command.getKeyword()
            );

            statOptional.ifPresentOrElse(
                    BlogStatistic::increaseCount,
                    () -> createNewStat(command)
            );
        });
    }

    private void createNewStat(UpsertBlogStatisticsCommand command) {
        blogStatisticsPort.save(
                BlogStatistic.create(
                        CreateBlogStatistic.builder()
                                .keyword(command.getKeyword())
                                .build()
                ));
    }
}
