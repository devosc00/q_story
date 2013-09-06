package controllers;

import java.util.Date;

import models.Material;
import models.Project;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.material.*;

public class Materials extends Controller {
	
	public static Result mList() {
		return redirect(routes.Materials.materialList(0,
			"m_name", "asc", ""));
	}
	
	public static Result materialList(int page, String sortBy, String order,
			String filter) {
		return ok(materialList.render(
				Material.page(page, 10, sortBy, order, filter), sortBy, order,
				filter));
	}
	
	public static Result create() {
		Form<Material> materialForm = form(Material.class);
		return ok(addMaterial.render(materialForm));
	}
	
	public static Result save() {
		Form<Material> materialForm = form(Material.class).bindFromRequest();
		if (materialForm.hasErrors()) {
			return badRequest(addMaterial.render(materialForm));
		}
		materialForm.get().save();
		flash("success", "Material " + materialForm.get().m_name
				+ " has been created");
		return mList();
	}
	
	public static void updateFinished (Long id, float t_amount, Date data){
		Material m = Material.find.byId(id);
		m.t_amount += t_amount;
		if (m.dates == null){
		m.dates = data;	
		}	
		m.update();
	}
	
	public static Result delete(Long id) {
		Material.find.ref(id).delete();
		flash("success", "Material has been deleted");
		return mList();
	}

}
