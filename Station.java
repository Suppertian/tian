package subway;

import java.util.ArrayList;

public class Station {
	
	private String Station_name="";
	private ArrayList<String> Line = new ArrayList<String>();
	private ArrayList<Station> Linkstation = new ArrayList<Station>();
	public int dist;
	public Station pre;
	
	public ArrayList<Station> getLinkstation() {
		return Linkstation;
	}
	public void setLinkstation(ArrayList<Station> linkstation) {
		Linkstation = linkstation;
	}
	public void addLinkstation(Station station) {
		Linkstation.add(station);
	}
	public String getStation_name() {
		return Station_name;
	}
	public void setStation_name(String station_name) {
		Station_name = station_name;
	}
	public void addLine(String line) {
		Line.add(line);
	}
	public ArrayList<String> getLine() {
		return Line;
	}
	public void setLine(ArrayList<String> line) {
		Line = line;
	}
	
}
