package controllers;

import models.History;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.history.*;

public class Historys extends Controller{
	
	public static Result hList(){
		return redirect(routes.Historys.historyList(0,
			"name", "asc", ""));
	}
	
	public static Result historyList(int page, String sortBy, String order,
			String filter) {
		return ok(historyList.render(
				History.page(page, 10, sortBy, order, filter), sortBy, order,
				filter));
	}

}
