package sample.kdohyeon.blog.service.statistics;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;
import sample.kdohyeon.blog.converter.statistics.BlogStatisticsDtoConverter;
import sample.kdohyeon.blog.port.input.statistics.SearchBlogStatisticsUseCase;
import sample.kdohyeon.blog.port.input.statistics.command.SearchBlogStatisticsCommand;
import sample.kdohyeon.blog.port.input.statistics.response.BlogStatisticsDto;
import sample.kdohyeon.blog.port.output.statistics.BlogStatisticsPort;

import java.util.List;

@Service
public class BlogStatisticsSearchService implements SearchBlogStatisticsUseCase {

    private final BlogStatisticsPort blogStatisticsPort;
    private final TransactionOperations readTransactionOperations;
    private final BlogStatisticsDtoConverter blogStatisticsDtoConverter;

    public BlogStatisticsSearchService(BlogStatisticsPort blogStatisticsPort,
                                       TransactionOperations readTransactionOperations,
                                       BlogStatisticsDtoConverter blogStatisticsDtoConverter) {
        this.blogStatisticsPort = blogStatisticsPort;
        this.readTransactionOperations = readTransactionOperations;
        this.blogStatisticsDtoConverter = blogStatisticsDtoConverter;
    }

    @Override
    public List<BlogStatisticsDto> search(SearchBlogStatisticsCommand command) {
        var stats = readTransactionOperations.execute(status -> blogStatisticsPort.findTopNByOrderByCountDesc(command.getTop()));

        return blogStatisticsDtoConverter.convert(stats);
    }
}
