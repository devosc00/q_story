package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Project extends Model {

	@Id
	public Long id;
	
	@Required
	public String name;
	
	@Formats.DateTime(pattern = "dd-MM-yyyy")
	public Date end_date;
	
	public int order_;
	
	public int done_parts;
	
	public float mat_count;
	
	
	@ManyToOne
	public Company company;
	
	
	@ManyToOne
	public Material material;
	
	
	public static Finder<Long, Project> find = new Finder<Long, Project>(
			Long.class, Project.class);
	
	public static Finder<String, Project> findByName = new Finder<String, Project>(
			String.class, Project.class);
	
	public static Page<Project> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where()
				.ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.fetch("company")
				.fetch("material")
				.findPagingList(pageSize)
				.getPage(page);
	}
	
	public static Long nameToId(String name){
		Project p = findByName.ref(name);
		return p.id;
	}

	public static int findDoneParts(Long projectId) {
		Project project = find.ref(projectId);
		return project.done_parts;		
	}
	
	public static Date findEndDate(Long projectId) {
		Project project = find.ref(projectId);
		return project.end_date;		
	}

}
