package uk.co.jerrin.search.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import au.com.bytecode.opencsv.CSVReader;

import play.api.Play;
import uk.co.jerrin.search.idx.FileEntry;

/**
 * @author Jerrin.Varghese
 *
 */
public class LogFileReader implements LogReader{

	public List<FileEntry> readFile() throws IOException{

        String url = Play.getFile("/resources/idx/file.csv", Play.current()).getAbsolutePath();
        CSVReader reader = new CSVReader(new FileReader(url), '|');
        return convert(reader);
	}
	
	public List<FileEntry> parseContent(String content){
		CSVReader reader = new CSVReader(new StringReader(content), '|');
		return convert(reader);
	}
	
	private List<FileEntry> convert(CSVReader csvReader){

        ColumnPositionMappingStrategy<FileEntry> mappingStrategy = new ColumnPositionMappingStrategy<FileEntry>();

        mappingStrategy.setType(FileEntry.class);
        String[] columns = new String[] {"name", "email", "subject", "errorCode", "date"}; // the fields to bind do in your JavaBean
        mappingStrategy.setColumnMapping(columns);

        CsvToBean<FileEntry> csv = new CsvToBean<FileEntry>();
        return csv.parse(mappingStrategy, csvReader);
	}
	
}
