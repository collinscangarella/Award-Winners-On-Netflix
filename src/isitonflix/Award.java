package isitonflix;

public class Award{
	private String place;
	private int year;
	private String category;
	private String honor;
	private boolean won;
	
	public Award(String p, int y, String c, String h, boolean w){
		this.place = p;
		this.year = y;
		this.category = c;
		this.honor = h;
		this.won = w;
	}
	public String getPlace(){
		return this.place;
	}
	public int getYear(){
		return this.year;
	}
	public String getCategory(){
		return this.category;
	}
	public String getHoner(){
		return this.honor;
	}
	public boolean won(){
		return this.won;
	}
}
