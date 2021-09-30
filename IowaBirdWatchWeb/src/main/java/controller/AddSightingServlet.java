package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BirdEntry;

/**
 * Servlet implementation class AddSightingServlet
 */
@WebServlet("/addSightingServlet")
public class AddSightingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSightingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user input from form
		String county = request.getParameter("county");
		String bird = request.getParameter("bird");
		
		//create bird entry object and use method in birdentryhelper to add the item to the database
		BirdEntry be = new BirdEntry(county, bird);
		BirdEntryHelper dao = new BirdEntryHelper();
		dao.insertItem(be);
		
		//after adding the item the user will be redirected to the index page
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		
	}

}
