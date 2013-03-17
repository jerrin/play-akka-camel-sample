package uk.co.jerrin.search.app;


import java.io.File;
import java.util.concurrent.TimeUnit;

import play.Application;
import play.Configuration;
import play.GlobalSettings;
import play.Logger;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import uk.co.jerrin.search.consumer.LogConsumer;
import uk.co.jerrin.search.consumer.LogFileConsumer;
import uk.co.jerrin.search.idx.FileEntry;
import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActorFactory;
import akka.camel.Camel;
import akka.camel.CamelExtension;
import akka.util.Timeout;

import com.github.cleverage.elasticsearch.IndexService;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Jerrin.Varghese
 *
 */
public class Global extends GlobalSettings {

	private Injector injector;

	@Override
	public Configuration onLoadConfig(Configuration configuration, File file,
			ClassLoader classloader) {

		Configuration config = super.onLoadConfig(configuration, file,
				classloader);

		injector = Guice.createInjector(new ProdModule());

		return config;
	}

	@Override
	public void onStart(Application app) {

		Logger.debug("Application has started");
		
		/* create an empty index just in case the file consumer fails to get the data from ftp server*/
		IndexService.cleanIndex();
		FileEntry emptyFile = new FileEntry();
		emptyFile.index();
		
		UntypedActorFactory factory = new UntypedActorFactory() {
			public Actor create() {
				return (LogFileConsumer) injector.getInstance(LogConsumer.class);
			}
		};

		ActorSystem system = ActorSystem.create("file-search");
		ActorRef producer = system.actorOf(new Props(factory), "filesearch");
		Camel camel = CamelExtension.get(system);

		Timeout timeout = new Timeout(Duration.create(30, TimeUnit.SECONDS));
		Future<ActorRef> activationFuture = camel.activationFutureFor(producer,
				timeout, system.dispatcher());

		Logger.debug("----------" + activationFuture.isCompleted());

	}

	@Override
	public void onStop(Application app) {
		Logger.debug("Application shutdown...");
	}

	@Override
	public <A> A getControllerInstance(Class<A> clazz) throws Exception {
		return injector.getInstance(clazz);
	}

}
