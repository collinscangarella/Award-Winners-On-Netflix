package isitonflix.request;

public class IisRequest extends GenericRequest {
	private boolean isOn;
	private String name;
	public IisRequest(String s){
		super("http://isitoninstant.com/" + s);
		this.name = s;
		run();
	}
	public boolean isStreaming(){
		return this.isOn;
	}
	@Override
	protected void trim(){
		boolean next = false;
		for (String s : getPageData()){
			if (next) {
				setIsOn(s);
				break;
			}
			if (s.equals("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />")){
				next = true;
			} 
		}
	}
	private void setIsOn(String s){
		int firstIndex = s.indexOf("&quot;") + 6;
		int lastIndex = s.lastIndexOf("&quot; ") + 7;
		if (s.substring(lastIndex, lastIndex + 2).equals("IS")){
			this.isOn = true;
		} else {
			this.isOn = false;
		}
		if(firstIndex < (lastIndex - 8)){
			if (!s.substring(firstIndex, lastIndex - 7).equals(this.name)) {
				this.isOn = false;
			}
		}
	}
}
