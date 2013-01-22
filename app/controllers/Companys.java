package controllers;

import models.Company;
import models.Project;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.company.*;
import views.html.*;

public class Companys extends Controller{
	
	public static Result create () {
		Form<Company> companyForm = form(Company.class);
		return ok(createCompForm.render(companyForm));
	}
	
	public static Result save () {
		Form<Company> companyForm = form(Company.class).bindFromRequest();
		if (companyForm.hasErrors()) {
			return badRequest(createCompForm.render(companyForm));
		}
		companyForm.get().save();
/*
		tworzenie z formularza tworzenia projektu i powrót do formularza po id
		
*/		return redirect(routes.Application.index());
	}

}
