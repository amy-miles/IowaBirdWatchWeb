package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BirdEntry;

/**
 * Servlet implementation class EditSightingServlet
 */
@WebServlet("/editSightingServlet")
public class EditSightingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSightingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BirdEntryHelper dao = new BirdEntryHelper();
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String county = request.getParameter("county");
		String bird = request.getParameter("bird");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		}catch(NumberFormatException e) {
			ld = LocalDate.now();
		}
		
		BirdEntry itemToUpdate = dao.searchForSightingById(tempId);	
		itemToUpdate.setSiteDate(ld);
		itemToUpdate.setCounty(county);
		itemToUpdate.setBird(bird);
		
		dao.updateSighting(itemToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllSightingsServlet").forward(request, response);
	}

}
