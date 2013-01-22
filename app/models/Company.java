package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Company extends Model{
	
	@Id
	public Long id;
	
	@Constraints.Required
	public String c_name;
	
	public int orders;
	
	public static Finder<Long, Company> find = 
			new Finder<Long, Company>(Long.class, Company.class);

	public static Map<String, String> options() {
		LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
		for (Company c : Company.find.orderBy("c_name").findList()) {
			options.put(c.id.toString(), c.c_name);
		}
		return options;
	}

}