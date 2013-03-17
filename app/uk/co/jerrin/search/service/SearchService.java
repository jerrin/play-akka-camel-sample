package uk.co.jerrin.search.service;

import java.util.List;

import uk.co.jerrin.search.idx.FileEntry;

/**
 * @author Jerrin.Varghese
 *
 */
public interface SearchService {
	public List<FileEntry> getFileContent();
}
