package cn.gd.lucas.spider;

public class Test {
	public static void main(String[] args) {
		String url="http://www.sysu.edu.cn/";
		System.out.println("test:");
		Spider spider=new Spider(url);
		int a=0;
		Page page=spider.nextPage();
		while(null!=page){
			System.out.println(a+++page.getUrl()+":"+page.getTitle());
			//System.out.println(page.getText());
			page=spider.nextPage();
			
		}
	}

}
