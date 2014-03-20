package isitonflix;

public class ImdbPage implements Comparable<ImdbPage>{
	private String page;
	private String name;
	private int year;
	public ImdbPage (String p, String n, int y){
		this.page = p;
		this.name = n;
		this.year = y;
	}
	public String getPage(){
		return this.page;
	}
	public String getName(){
		return this.name;
	}
	public int getYear(){
		return this.year;
	}
	@Override
	public int compareTo(ImdbPage that) {
		return this.getName().compareTo(that.getName());
	}
}
