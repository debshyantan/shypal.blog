package com.blog.shypal;

public class Custom {
	String title;
	String featured_image,avatar_URL;
	String posturl;

	public Custom(String title, String featured_image, String avatar_URL, String posturl) {
		this.title=title;
		this.featured_image=featured_image;
		this.avatar_URL=avatar_URL;
		this.posturl=posturl;
	
	}

	public String getPosturl() {
		return posturl;
	}

	public void setPosturl(String posturl) {
		this.posturl = posturl;
	}

	public String getAvatar_URL() {
		return avatar_URL;
	}

	public void setAvatar_URL(String avatar_URL) {
		this.avatar_URL = avatar_URL;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeatured_image() {
		return featured_image;
	}

	public void setFeatured_image(String featured_image) {
		this.featured_image = featured_image;
	}

	
	
	
}
