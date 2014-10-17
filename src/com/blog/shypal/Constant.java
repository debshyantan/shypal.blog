package com.blog.shypal;

public class Constant {
	String mtitle;
	int micon;
	
	String myurl="http://public-api.wordpress.com/rest/v1/sites/www.shypal.com/posts/?pretty=true&number=5";
//	
//	String myurl="http://public-api.wordpress.com/rest/v1/sites/www.shypal.com/posts/?pretty=true";


	public String getMyurl() {
		return myurl;
	}

	public Constant(String mtitle, int micon) {
		this.micon = micon;
		this.mtitle = mtitle;

	}

	public Constant() {
		// TODO Auto-generated constructor stub
	}

	public String getMtitle() {
		return mtitle;
	}

	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}

	public int getMicon() {
		return micon;
	}

	public void setMicon(int micon) {
		this.micon = micon;
	}

}
