package uk.co.jerrin.search.app;

import uk.co.jerrin.search.consumer.LogConsumer;
import uk.co.jerrin.search.consumer.LogFileConsumer;
import uk.co.jerrin.search.service.DefaultSearchService;
import uk.co.jerrin.search.service.FileIndexService;
import uk.co.jerrin.search.service.LogFileIndexService;
import uk.co.jerrin.search.service.LogFileReader;
import uk.co.jerrin.search.service.LogReader;
import uk.co.jerrin.search.service.SearchService;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * @author Jerrin.Varghese
 *
 */
public class ProdModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SearchService.class).to(DefaultSearchService.class).in(Singleton.class);
		bind(LogReader.class).to(LogFileReader.class).in(Singleton.class);
		bind(FileIndexService.class).to(LogFileIndexService.class).in(Singleton.class);
		bind(LogConsumer.class).to(LogFileConsumer.class).in(Singleton.class);
	}
}
