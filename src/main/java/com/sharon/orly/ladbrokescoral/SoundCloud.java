package com.sharon.orly.ladbrokescoral;

public class SoundCloud {

	public Long ID;
	public String Title;
	public String PermalinkURL;
	public Long User;

	public SoundCloud() {
	}

	public SoundCloud(Long iD, String title, String permalinkURL, Long user) {
		super();
		ID = iD;
		Title = title;
		PermalinkURL = permalinkURL;
		User = user;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getPermalinkURL() {
		return PermalinkURL;
	}

	public void setPermalinkURL(String permalinkURL) {
		PermalinkURL = permalinkURL;
	}

	public Long getUser() {
		return User;
	}

	public void setUser(Long user) {
		User = user;
	}

	@Override
	public String toString() {
		return "SoundCloud [ID=" + ID + ", Title=" + Title + ", PermalinkURL=" + PermalinkURL + ", User=" + User + "]";
	}

}
