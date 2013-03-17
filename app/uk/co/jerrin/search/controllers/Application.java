package uk.co.jerrin.search.controllers;

import java.util.Collections;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import play.mvc.Controller;
import play.mvc.Result;
import uk.co.jerrin.search.idx.FileEntry;
import views.html.index;

import com.github.cleverage.elasticsearch.IndexQuery;
import com.github.cleverage.elasticsearch.IndexResults;

/**
 * @author Jerrin.Varghese
 *
 */
public class Application extends Controller {

	public static Result filter(String text) {

		IndexQuery<FileEntry> indexQuery = FileEntry.find.query();
        indexQuery.setBuilder(QueryBuilders.queryString("*" + text + "*"));

        IndexResults<FileEntry> indexResults = FileEntry.find.search(indexQuery);
        List<FileEntry> fileContent = indexResults.getResults();
        if(fileContent == null)
            fileContent = Collections.emptyList();

        return ok(index.render(fileContent));
	}
	
	public static Result index() {
		
		IndexResults<FileEntry> indexResults = FileEntry.find.all();
        List<FileEntry> fileContent = indexResults.getResults();
        if(fileContent == null)
            fileContent = Collections.emptyList();

        return ok(index.render(fileContent));
		
	}
  
}