package cn.gd.lucas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gd.lucas.API.DirectAPI;
import cn.gd.lucas.API.MyQueryResult;

public class SearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		String db = request.getParameter("db");
		String q = request.getParameter("q");
		System.out.println("query1:" + q);
		String pageNumber = request.getParameter("pageNumber");
		String start = request.getParameter("start");
		if (null == start && null != pageNumber) {
			start = pageNumber;
			start = Integer.toString((Integer.parseInt(pageNumber) - 1) * 10);
		}
		q = new String(q.getBytes("iso8859_1"), "utf-8");
		System.out.println("query2:" + q);
		PrintWriter wr = response.getWriter();

		try {

			queryAndShow(request, response, q, db, start);

		} catch (Throwable e) {
			e.printStackTrace();
			wr.write("error!");
			wr.flush();
			wr.close();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	private void queryAndShow(HttpServletRequest request, HttpServletResponse response, String q, String db,
			String start) throws Throwable {
		MyQueryResult result;
		result = new DirectAPI().queryAndReturnMyQueryResult(db, q, start, "10", "on");
		//System.out.println(result.toString());
		//log.info(result.toString());
		request.setAttribute("searchresult", result.getResultArray());
		request.setAttribute("total", result.getTotal());
		request.setAttribute("action", result.getAction());
		request.setAttribute("q", result.getKeyword());
		//System.out.println("searchstr:" + result.getSearchstr());
		request.setAttribute("pageNumber", result.getPageNumber());
		request.setAttribute("sa", result.getSa());
		request.setAttribute("ID", "IDD");
		request.setAttribute("db", db);
		RequestDispatcher rd;
		if ("sysu".endsWith(db)) {
			rd = request.getRequestDispatcher("/sysu/ResultShow.jsp");
		} else {
			rd = request.getRequestDispatcher("/pku/ResultShow.jsp");
		}

		rd.forward(request, response);

	}

}
