package isitonflix.request;

import java.util.ArrayList;

public class ImdbRequest extends GenericRequest {
	private ArrayList<String> page;
	public ImdbRequest(String s){
		super(s);
		run();
	}
	public ArrayList<String> getPage(){
		return this.page;
	}
	
	protected void trim(){
		ArrayList<String> newPage = new ArrayList<String>();
		boolean start = false;
		boolean finish = false;
		for (String s : getPageData()){
			if (s.equals("<form method=\"POST\" action=\"/updates\">")){
				finish = true;
			}
			if (start && !finish){
				if (!s.trim().equals("")){
					newPage.add(s);
				}
			}
			if (s.equals("<div id=\"main\">")){
				start = true;
			} 
		}
		this.page = newPage;
	}
}