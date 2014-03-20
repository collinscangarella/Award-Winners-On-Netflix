package isitonflix.request;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public abstract class GenericRequest {

	private String url;
	private ArrayList<String> pageData;
	public GenericRequest(String s){
		this.url = s;
		pageData = new ArrayList<String>();
	}
	protected ArrayList<String> getPageData(){
		return this.pageData;
	}
	public void run() {
		try {
			URLConnection connection = new URL(this.url).openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			InputStream response = connection.getInputStream();
			BufferedReader br = new BufferedReader(
				new InputStreamReader(response, "UTF-8")
			);
			String t = "";
			while ((t = br.readLine()) != null) {
				pageData.add(t);
			}
			br.close();
			response.close();
			trim();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected abstract void trim();	
}
