package isitonflix;

import java.util.ArrayList;

public class Movie implements Comparable<Movie>{
	private String name;
	private String url;
	private ArrayList<Award> awards;
	private boolean isStreaming;
	
	public Movie(String n, String u){
		awards = new ArrayList<Award>();
		this.name = n;
		this.url = u;
	}
	public void addAward(Award a){
		awards.add(a);
	}
	public String getName(){
		return this.name;
	}
	public ArrayList<Award> getAwards(){
		return this.awards;
	}
	public String getUrl(){
		return this.url;
	}
	public void setStreaming(boolean b){
		this.isStreaming = b;
	}
	public boolean isStreaming(){
		return this.isStreaming;
	}
	@Override
	public boolean equals(Object o){
	    if (o == null) return false;
	    if (!(o instanceof Movie))return false;
	    if (o == this) return true;
	    Movie m = (Movie)o;
	    return m.getName().equals(this.getName()) && m.getUrl().equals(this.getUrl());
	}
	@Override
	public int compareTo(Movie that) {
		return this.getName().compareTo(that.getName());
	}
}
