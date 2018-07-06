package com.imtiaz.searchviewwithrxandroid.Retrofit.POJO;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Notices{

	@SerializedName("notice")
	private List<NoticeItem> notice;

	public void setNotice(List<NoticeItem> notice){
		this.notice = notice;
	}

	public List<NoticeItem> getNotice(){
		return notice;
	}

	@Override
 	public String toString(){
		return 
			"Notices{" + 
			"notice = '" + notice + '\'' + 
			"}";
		}
}