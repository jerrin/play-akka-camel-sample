package uk.co.jerrin.search.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.google.inject.Inject;

import uk.co.jerrin.search.idx.FileEntry;

/**
 * @author Jerrin.Varghese
 *
 */
public class DefaultSearchService implements SearchService {
	
	@Inject
	private LogReader reader;
	
	public List<FileEntry> getFileContent(){
		try {
			return reader.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}
