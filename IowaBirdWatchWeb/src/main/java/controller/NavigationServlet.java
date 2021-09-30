package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BirdEntry;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// call to the BirdEntryHelper to we can use methods
		BirdEntryHelper dao = new BirdEntryHelper();

		// figure out what button was clicked
		String act = request.getParameter("doThisToItem");

		// after all chages, we should redirect to the viewAllItems servlet
		// the only time we don't is if they select to add a new item or edit
		String path = "/viewAllSightingsServlet";

		if (act.equals("delete")) {
			try {// try catch in case tried to delete but didn't select a radio button
				Integer tempId = Integer.parseInt(request.getParameter("id"));// parameter id is set in the input radio
				BirdEntry itemToDelete = dao.searchForSightingById(tempId);
				dao.deleteSighting(itemToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a sighting");
			}

		} else if (act.equals("edit")) {

			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				BirdEntry itemToEdit = dao.searchForSightingById(tempId);
				request.setAttribute("itemToEdit", itemToEdit);
				path = "/edit-sighting.jsp";

			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a sighting.");
			}

		} else if (act.equals("add")) {
			path = "/index.html";
		}

		// send to correct path
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
