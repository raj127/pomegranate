package cn.gd.lucas.spider;

public class SubLinkFilter implements LinkFilter {
    private String url;
	public boolean accept(String url) {
		if(url.startsWith(this.url))
			return true;
		else
			return false;
	}
	public SubLinkFilter(String url) {
		this.url=url;
	}

}
