package cn.gd.lucas.spider;
/**
 * 
 * @author Administrator
 * ��ʾҳ�����
 */
public class Page {
	/**ҳ���url */
	private String url;
	/**��ҳ������ȡ�������ı�*/
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
