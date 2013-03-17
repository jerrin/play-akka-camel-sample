package uk.co.jerrin.search.service;

import java.util.List;

import com.github.cleverage.elasticsearch.IndexService;

import uk.co.jerrin.search.idx.FileEntry;

/**
 * @author Jerrin.Varghese
 *
 */
public class LogFileIndexService implements FileIndexService {

	@Override
	public void index(List<FileEntry> fileEntries) {
		IndexService.cleanIndex();
		for(FileEntry entry: fileEntries){
            entry.index();
        }
		//FileEntry.find.all();
	}
}
