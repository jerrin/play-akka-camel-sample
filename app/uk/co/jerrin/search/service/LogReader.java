package uk.co.jerrin.search.service;

import java.io.IOException;
import java.util.List;

import uk.co.jerrin.search.idx.FileEntry;

/**
 * @author Jerrin.Varghese
 *
 */
public interface LogReader {
	public List<FileEntry> readFile() throws IOException;
	public List<FileEntry> parseContent(String content);
}
