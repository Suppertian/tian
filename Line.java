package subway;

import java.util.ArrayList;

public class Line {
	private String line_name;
	private ArrayList<String> stations =new  ArrayList<>();
	
	public String getLine_name() {
		return line_name;
	}
	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}
	public ArrayList<String> getStations() {
		return stations;
	}
	public void setStations(ArrayList<String> stations) {
		stations = stations;
	}
	public void addStation(String station) {
		stations.add(station);
	}
	
}
