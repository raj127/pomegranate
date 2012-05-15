package cn.gd.lucas.spider;
/**
 * 
 * @author Administrator
 * 表示页面的类
 */
public class Page {
	/**页面的url */
	private String url;
	/**从页面中提取出来的文本*/
	private String text;
	private String title;
	public Page(String url,String title,String text) {
		// TODO Auto-generated constructor stub
		this.url=url;
		this.text=text;
		this.title=title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
