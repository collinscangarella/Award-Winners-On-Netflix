package isitonflix;

import isitonflix.Award;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImdbParser {
	private Pattern category = Pattern.compile("(.*)<h1>(.*)</h1>(.*)");
	private Pattern honor = Pattern.compile("(.*)<h2>(.*)</h2>(.*)");
	private Pattern won = Pattern.compile("(.*)<h3>(.*)</h3>(.*)");
	private Pattern film = Pattern.compile("(.*)<a  href=(.*) >(.*)</a>: </strong><a  href=(.*)");
	
	private ArrayList<Movie> movies;
	private String place;
	private int year;
	public ImdbParser(){
		this.movies = new ArrayList<Movie>();
	}
	public ImdbParser(String p, int y, ArrayList<Movie> m){
		this.place = p;
		this.year = y;
		this.movies = m;
	}
	public ArrayList<Movie> getMovies(){
		return this.movies;
	}
	public void parse(ArrayList<String> strings){
		String c = "";
		String h = "";
		boolean w = false;
		Matcher m;
		for (String s : strings) {
			m = category.matcher(s);
			if (m.matches()){
				c = m.group(2);
				h = c;
				continue;
			}
			m = honor.matcher(s);
			if (m.matches()){
				h = m.group(2);
				continue;
			}
			m = won.matcher(s);
			if (m.matches()){
				if(m.group(2).equals("WINNER") || m.group(2).equals("WINNERS")){
					w = true;
				} else {
					w = false;
				}
				continue;
			}
			m = film.matcher(s);
			if (m.matches()){
				Movie movie = new Movie(m.group(3), m.group(2).substring(1, m.group(2).length()-2));
				Award award = new Award(this.place, this.year, c, h, w);
				addAwardToList(movie, award);
				continue;
			}
		}
	}
	private void addAwardToList(Movie m, Award a){
		boolean found = false;
		for (Movie movie : movies) {
			if (movie.equals(m)){
				found = true;
				movie.addAward(a);
				break;
			}
		}
		if(!found){
			m.addAward(a);
			this.movies.add(m);
		}
	}
}
