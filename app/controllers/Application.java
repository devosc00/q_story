package controllers;

import java.util.Date;

import models.History;
import models.Project;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;
import views.html.material.*;

public class Application extends Controller {
  
	public static Result index() {
		return GO_HOME;
	}
	
	public static Result GO_HOME = redirect(routes.Application.projectList(0,
			"name", "asc", ""));
  
	public static Result edit(Long id) {
		Form<Project> projectForm = form(Project.class).fill(
				Project.find.byId(id));
		return ok(editForm.render(id, projectForm));
	}

	public static Result projectList(int page, String sortBy, String order,
			String filter) {
		return ok(projectList.render(
				Project.page(page, 10, sortBy, order, filter), sortBy, order,
				filter));
	}
	
	public static Result update(Long id) {
		Form<Project> projectForm = form(Project.class).bindFromRequest();
		if (projectForm.hasErrors()) {
			return badRequest(editForm.render(id, projectForm));
		}
		projectForm.get().update(id);
		flash("success", "Project " + projectForm.get().name
				+ " has been updated");
		return GO_HOME;
	}
	
	public static Result create() {
		Form<Project> projectForm = form(Project.class);
		return ok(createForm.render(projectForm));
	}
	
	public static Result save() {
		Form<Project> projectForm = form(Project.class).bindFromRequest();
		if (projectForm.hasErrors() || projectForm.get().company.id == null 
				|| projectForm.get().material.id == null){
				return badRequest(createForm.render(projectForm));		
		}
		projectForm.get().save();
		flash("success", "Project " + projectForm.get().name
				+ " has been created");
		return GO_HOME;
	}
	
	public static Result delete(Long id) {
		Project.find.ref(id).delete();
		flash("success", "Project has been deleted");
		return GO_HOME;
	}
	
	public static Result editDoneParts(Long id){
		Project p = Project.find.byId(id);
		p.done_parts = 0;
		Form<Project> projectForm = form(Project.class).fill(p);
		return ok(editParts.render(id, projectForm));
	}
	
	public static Result updateDoneParts(Long id){
		int upParts = form(Project.class).bindFromRequest().get().done_parts;
		Project p = Project.find.byId(id);
		p.done_parts = upParts + Project.findDoneParts(id);
		p.update();
		if (p.done_parts >= Project.find.byId(id).order_){
			Materials.updateFinished(p.material.id, p.mat_count, p.end_date);
			int saldo = p.done_parts - p.order_;
			if(History.isHistoryCreated(id)){
				History.update(p.done_parts, saldo, p.mat_count, p.id);
			} else
			History.create(p.name, p.done_parts, saldo, p.end_date, p.mat_count, 1, p.id);
			flash("success", "Project is finished");
		}
		flash("success", "Done parts have been updated");
		return GO_HOME;
	}
	
	public static Result editMaterial(Long id){
		Project p = Project.find.byId(id);
		Form<Project> editMaterialForm = form(Project.class).fill(p);
		return ok(editMaterial.render(id, editMaterialForm));
	}
	
	public static Result updateMaterial(Long id){
		float quantity = form(Project.class).bindFromRequest().get().mat_count;
		Project p = Project.find.byId(id);
		p.mat_count = quantity;
		p.update();
		flash("success", "Project " + p.name
				+ " has been updated");
		return GO_HOME;
	}

}
