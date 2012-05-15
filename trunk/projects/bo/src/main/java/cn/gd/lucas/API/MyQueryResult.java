package cn.gd.lucas.API;
/**
 * 
 * @author songyw
 * 该类的本质是将查询的xml结果进行了重新组织，一边对结果中有意义的数据更方便地引用
 */

public class MyQueryResult {
	String error=null;
	//二维数组，每一行代表一个查询结果中的信息
	private String [][]resultArray=null;
	//查询结果总数
	private String total;
	//查询类型，待扩展....
	private String action;
	private String keyword;
    private String pageNumber;
    //???
    private String sa; 
    public MyQueryResult() {
		// TODO Auto-generated constructor stub
	}
    public MyQueryResult(String [][] resultArray,String total,String action,String keyword,String pageNumber,String sa) {
	    this.action=action;
	    this.resultArray=resultArray;
	    this.pageNumber=pageNumber;
	    this.keyword=keyword;
	    this.total=total;
	    this.sa=sa;
	}
	public String[][] getResultArray() {
		return resultArray;
	}
	public void setResultArray(String[][] resultArray) {
		this.resultArray = resultArray;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getSa() {
		return sa;
	}
	public void setSa(String sa) {
		this.sa = sa; 
	}
	public String toString(){
		if(null==error){
			StringBuffer sb=new StringBuffer();
			sb.append("tootal:"+total+"\n");
			sb.append("action:"+action+"\n");
			sb.append("keyword:"+keyword+"\n");
			sb.append("pageNumber:"+pageNumber+"\n");
			sb.append("sa:"+sa+"\n");
			sb.append("resultArray:\n");
			for(String[] temp:resultArray){
				sb.append(temp[0]+":"+temp[1]+":"+temp[2]+":"+temp[3]);
			}		
			return sb.toString();
		}else{
			return "err:"+error;
		}
		
	}
    public String getError(){
    	return error;
    }
    public void setError(String err){
    	error=err;
    }
}
