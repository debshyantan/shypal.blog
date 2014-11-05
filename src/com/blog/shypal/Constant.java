package com.blog.shypal;

public class Constant {
	String mtitle;
	int micon;

	String myurl = "http://public-api.wordpress.com/rest/v1/sites/www.shypal.com/posts/?pretty=true&number=5";
	String HomeURlwithOffset = "http://public-api.wordpress.com/rest/v1/sites/www.shypal.com/posts/?pretty=true&number=5&offset=";
	String categoryUrl = "http://public-api.wordpress.com/rest/v1/sites/www.shypal.com/posts/?pretty=true&number=5&category=";
	String categoryURLwithoffset="http://public-api.wordpress.com/rest/v1/sites/www.shypal.com/posts/?pretty=true&number=5&offset=";
	
	public String getCategoryURLwithoffset(Long offset,String categoryslug) {
		
		categoryURLwithoffset=categoryURLwithoffset+offset+"&category="+categoryslug;
		return categoryURLwithoffset;
	}

	public String getHomeURlwithOffset() {
		return HomeURlwithOffset;
	}
	
	public String getCategoryUrl(String categorySlug) {

		categoryUrl = categoryUrl + categorySlug;
		return categoryUrl;
	}

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
