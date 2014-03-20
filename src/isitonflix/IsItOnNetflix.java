package isitonflix;

import isitonflix.request.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class IsItOnNetflix {
	
	
	public static void main(String[] args) {
		ArrayList<ImdbPage> pages = new ArrayList<ImdbPage>();
		for (int i = 2010; i < 2015; i++){
			pages.addAll(getPages(i));
		}
		ImdbParser ip = new ImdbParser();
		ArrayList<Movie> movies;
		for (ImdbPage p : pages){
			System.out.println("Checking out " + p.getName() + " " + p.getYear() + ".");
			movies = ip.getMovies();
			ip = new ImdbParser(p.getName(), p.getYear(), movies);
			ImdbRequest imR = new ImdbRequest(p.getPage());
			ip.parse(imR.getPage());
		}
		movies = ip.getMovies();
		Collections.sort(movies);
		
		BufferedWriter writer = null;
        try {
            File file = new File(System.getProperty("user.home") + "AwardWinnersOnNetflix.xls");
            writer = new BufferedWriter(new FileWriter(file));
			writer.write("Movie \t Url \t Event \t Year \t Category \t Honor \t Won \n");
			for (Movie m : movies){
				System.out.println("Checking " + m.getName() + ".");
				IisRequest iiR = new IisRequest(m.getName());
				m.setStreaming(iiR.isStreaming());
				if (m.isStreaming()){
					for (Award a : m.getAwards()) {
						writer.write(m.getName() + "\t" +
								"www.imdb.com" + m.getUrl() + "\t" +
								a.getPlace() + "\t" +
								a.getYear() + "\t" +
								a.getCategory() + "\t" +
								a.getHoner()  + "\t" +
								won(a.won()) + "\n"
								);
					}
				}
				//Thread.sleep(250);
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
        System.out.println("Done!");
	}
	private static String won(boolean w){
		return w ? "Won" : "Nominated";
	}
	private static ArrayList<ImdbPage> getPages(int year){
		ArrayList<ImdbPage> pages = new ArrayList<ImdbPage>();
		String preface = "http://www.imdb.com/event/";
		pages.add(new ImdbPage(preface + "ev0000003/" + String.valueOf(year) + "/", "Academy Awards", year));
		pages.add(new ImdbPage(preface + "ev0000123/" + String.valueOf(year) + "/", "BAFTAs", year));
		pages.add(new ImdbPage(preface + "ev0000631/" + String.valueOf(year) + "/", "Sundance", year));
		pages.add(new ImdbPage(preface + "ev0000147/" + String.valueOf(year) + "/", "Cannes", year));
		pages.add(new ImdbPage(preface + "ev0000894/" + String.valueOf(year) + "/", "Tribeca", year));
		pages.add(new ImdbPage(preface + "ev0000091/" + String.valueOf(year) + "/", "Berlin", year));
		pages.add(new ImdbPage(preface + "ev0000681/" + String.valueOf(year) + "/", "Venice", year));
		pages.add(new ImdbPage(preface + "ev0000659/" + String.valueOf(year) + "/", "Toronto", year));
		pages.add(new ImdbPage(preface + "ev0000292/" + String.valueOf(year) + "/", "Golden Globes", year));
		pages.add(new ImdbPage(preface + "ev0000655/" + String.valueOf(year) + "/", "Tokyo", year));
		pages.add(new ImdbPage(preface + "ev0000030/" + String.valueOf(year) + "/", "Ann Arbor", year));
		Collections.sort(pages);
		return pages;
	}
}
