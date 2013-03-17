package uk.co.jerrin.search.consumer;


import java.util.List;

import play.Logger;
import uk.co.jerrin.search.idx.FileEntry;
import uk.co.jerrin.search.service.FileIndexService;
import uk.co.jerrin.search.service.LogReader;
import akka.camel.CamelMessage;
import akka.camel.javaapi.UntypedConsumerActor;

import com.google.inject.Inject;

/**
 * @author Jerrin.Varghese
 *
 */
public class LogFileConsumer extends UntypedConsumerActor implements LogConsumer{
	
	@Inject
	private LogReader reader;
	@Inject
	private FileIndexService fileIndexService; 
	
    public String getEndpointUri() {
        //return "jetty:http://localhost:8877/camel/default";
    	//return "file://resources/idx/files";
    	long delay = 3 * 60 * 60 * 1000; // 3 hours
    	long initDelay = 1 * 1000; // 1 seconds
    	return String.format("ftp://admin@localhost:21/?username=admin&password=admin&readLock=changed&consumer.initialDelay=%s&consumer.delay=%s", initDelay, delay);
    }

    public void onReceive(Object message) {
        if (message instanceof CamelMessage) {
            CamelMessage camelMessage = (CamelMessage) message;
            String body = camelMessage.getBodyAs(String.class, getCamelContext());
            
            Logger.debug("----------"+body);
            
            List<FileEntry> fileEntries = reader.parseContent(body);
            fileIndexService.index(fileEntries);
            
            //getSender().tell(String.format("Received message: %s",body), getSelf());
        } else
            unhandled(message);
    }

}

