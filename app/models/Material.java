package models;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.data.validation.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Material extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	public String m_name;
	
	public float amount;
	
	public float t_amount;
	
	@Formats.DateTime(pattern = "dd-MM-yyyy")
	public Date dates;
	
	public static Finder<Long, Material> find = 
			new Finder<Long, Material>(Long.class, Material.class);
	
	public static Map<String, String> options() {
		LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
		for (Material c : Material.find.orderBy("m_name").findList()) {
			options.put(c.id.toString(), c.m_name);
		}
		return options;
	}
	
	
	public static Page<Material> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where()
				.ilike("m_name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.findPagingList(pageSize)
				.getPage(page);
	}
	
	

}
