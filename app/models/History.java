package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.db.ebean.Model;

@Entity
public class History extends Model {

	@Id
	public Long id;
	
	public String name;

	public int t_parts;

	public int saldo;

	@Formats.DateTime(pattern = "dd-MM-yyyy")
	public Date dates;

	public int order_counter;

	public float t_material;

	@ManyToOne
	public Project project;

	public History(String name, int total_p, int s, Date from, float mat, int counter,
			Long p_id) {
		this.name = name;
		this.t_parts = total_p;
		this.saldo = s;
		this.dates = from;
		this.t_material = mat;
		this.order_counter = counter;
		this.project = Project.find.ref(p_id);
	}

	public static boolean isHistoryCreated(Long id) {
		if (History.find.where().eq("project.id", id).findUnique() != null) {
			return true;
		} else
			return false;
	}

	public static void update(int total, int s, float mat, Long id) {
		History h = History.find.where().eq("project.id", id).findUnique();
		h.t_parts += total;
		h.saldo += s;
		h.t_material += mat;
		h.order_counter++;
		h.update();
	}

	public static History create(String name, int total_p, int s, Date from, float mat,
			int counter, Long p_id) {
		History history = new History(name, total_p, s, from, mat, counter, p_id);
		history.save();
		return history;
	}

	public static Finder<Long, History> find = new Finder<Long, History>(
			Long.class, History.class);

	public static Page<History> page(int page, int pageSize, String sortBy,
			String order, String filter) {
		return find.where()
				.ilike("project.name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.fetch("project")
				.fetch("project.material")
				.fetch("project.company")
				.findPagingList(pageSize)
				.getPage(page);
	}
}
