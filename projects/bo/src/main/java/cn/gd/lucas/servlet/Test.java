package cn.gd.lucas.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.htmlparser.util.ParserException;

import cn.gd.lucas.API.*;
import cn.gd.lucas.spider.Page;
import cn.gd.lucas.spider.Spider;

public class Test extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		test2();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private void test2(){
		int id=1;
		Spider spider=new Spider("http://www.sysu.edu.cn/");
		Page page=spider.nextPage();
		while(null!=page){
			System.out.println("**********");
			try {
				DirectAPI.addIndex("sysu",Integer.toString(id++), page.getUrl(), page.getTitle(),page.getText());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(page.getText());
			page=spider.nextPage();
			
		}
	}
	
	private void test1(){
		String url="http://sysu.edu.cn/";
		String text="中山大学信息科学与技术学院";
		try {
			DirectAPI.addIndex("sysu","1", url,"中山大学", text);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    private void test3(){
    	try {
			String result=DirectAPI.query("sysu", "中山大学","0","10","on");
			System.out.println(result);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
