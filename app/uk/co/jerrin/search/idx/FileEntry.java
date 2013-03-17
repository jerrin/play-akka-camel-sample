package uk.co.jerrin.search.idx;

import com.github.cleverage.elasticsearch.Index;
import com.github.cleverage.elasticsearch.Indexable;
import com.github.cleverage.elasticsearch.annotations.IndexType;

import java.util.HashMap;
import java.util.Map;

import play.libs.Json;

/**
 * @author Jerrin.Varghese
 *
 */
@IndexType(name = "fileEntry")
public class FileEntry  extends Index {
	
	public String name;
	public String email;
	public String subject;
	public String errorCode;
	public String date;

    // Find method static for request
    public static Finder<FileEntry> find = new Finder<FileEntry>(FileEntry.class);


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Map toIndex() {
        HashMap map = new HashMap();
        map.put("name", name);
        map.put("email", email);
        map.put("subject", subject);
        map.put("errorCode", errorCode);
        map.put("date", date);
        return map;
    }

    public Indexable fromIndex(Map map) {
        if (map == null) {
            return this;
        }

        this.name = (String) map.get("name");
        this.email = (String) map.get("email");
        this.subject = (String) map.get("subject");
        this.errorCode = (String) map.get("errorCode");
        this.date = (String) map.get("date");
        return this;
    }

    @Override
    public String toString() {
    	//return Json.toJson(this).toString();
        return "{FileEntry : {\"name\" : \"" + name + "\", " +
        		"\"email\" : \"" + email + "\", " +
        		"\"subject\" : \"" + subject + "\", " +
        		"\"errorCode\" : \"" + errorCode + "\"}}";
    }

}
