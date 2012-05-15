package cn.gd.lucas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gd.lucas.API.DirectAPI;
import cn.gd.lucas.spider.Page;
import cn.gd.lucas.spider.Spider;

public class UpdateIndexServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateIndexServlet() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("gb2312");
		//System.out.println("访问Update");
		String db=request.getParameter("db");
		PrintWriter wr=response.getWriter();
		updateIndex(db,wr);

		
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private void updateIndex(String db,PrintWriter pw){
		int id=1;
		Spider spider=null;
		if("sysu".equals(db)){
			spider=new Spider("http://www.sysu.edu.cn/");
		}
		if("pku".equals(db)){
			spider=new Spider("http://www.pku.edu.cn/");
		}
		Page page=spider.nextPage();
		while(null!=page){
			System.out.println("**********"+id);
			try {
				DirectAPI.addIndex(db,Integer.toString(id++), page.getUrl(), page.getTitle(),page.getText());
			} catch (Throwable e) {
				pw.write("error happend while make index of page "+id);
				e.printStackTrace();
			}
			//System.out.println(page.getText());
			page=spider.nextPage();			
		}
		pw.write("已经建立索引的页面数："+id);
		pw.flush();
		pw.close();
	}

}
