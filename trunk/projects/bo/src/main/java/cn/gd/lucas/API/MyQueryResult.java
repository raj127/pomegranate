package cn.gd.lucas.API;

public class MyQueryResult {
	String error = null;

	private String[][] resultArray = null;

	private String total;

	private String action;
	private String keyword;
	private String pageNumber;

	private String sa;

	public MyQueryResult() {

	}

	public MyQueryResult(String[][] resultArray, String total, String action, String keyword, String pageNumber,
			String sa) {
		this.action = action;
		this.resultArray = resultArray;
		this.pageNumber = pageNumber;
		this.keyword = keyword;
		this.total = total;
		this.sa = sa;
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

	public String toString() {
		if (null == error) {
			StringBuffer sb = new StringBuffer();
			sb.append("tootal:" + total + "\n");
			sb.append("action:" + action + "\n");
			sb.append("keyword:" + keyword + "\n");
			sb.append("pageNumber:" + pageNumber + "\n");
			sb.append("sa:" + sa + "\n");
			sb.append("resultArray:\n");
			for (String[] temp : resultArray) {
				sb.append(temp[0] + ":" + temp[1] + ":" + temp[2] + ":" + temp[3]);
			}
			return sb.toString();
		} else {
			return "err:" + error;
		}

	}

	public String getError() {
		return error;
	}

	public void setError(String err) {
		error = err;
	}
}
