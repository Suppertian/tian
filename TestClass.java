package subway;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class TestClass {
    static final int MaxValue=65535;
    static final int MaxNum=400;
    
    public static void main(String[] args) {
        System.out.print("请输入地铁文件路径:");
        Scanner input=new Scanner(System.in);
        File file = new File(input.nextLine());
        ArrayList<Station> stations= new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<Line>();
        try{
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String s = null;
                int j;
                Station last_station= new Station();
                while ((s = br.readLine()) != null) {
                    String tokens[]=s.split(" ");
                    Line line = new Line();
                    line.setLine_name(tokens[0]);
                    for(int i=1;i<tokens.length;i++) {
                    	line.addStation(tokens[i]);
                        for(j=0;j<stations.size();j++) {
                            if(stations.get(j).getStation_name().equals(tokens[i]))
                                {
                                    stations.get(j).addLine(tokens[0]);
                                    if(i!=1) {
                                        stations.get(j).addLinkstation(last_station);
                                        last_station.addLinkstation(stations.get(j));
                                    }
                                    last_station=stations.get(j);
                                    break;
                                }
                        }
                        if(j==stations.size()) {
                            Station station = new Station();
                            station.addLine(tokens[0]);
                            station.setStation_name(tokens[i]);
                            station.dist=MaxValue;
                            if(i!=1) {
                                station.addLinkstation(last_station);
                                last_station.addLinkstation(station);
                            }
                            stations.add(station);
                            last_station=station;
                        }
                    
                    }
                    lines.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        System.out.print("输入数字选择功能: 1.查询最短路径 2.查询线路站点 ");
        int number=input.nextInt();
        if(number==1){
        System.out.print("请输入起始站：");
        String begin_station_name=input.next();
        System.out.print("请输入终点站：");
        String end_station_name=input.next();
        Station end_station = new Station();
        Station begin_station = new Station();
        for(Station station:stations) {
            if(station.getStation_name().equals(begin_station_name)) {
                begin_station=station;
                shortestPath(station);
            }
            if(station.getStation_name().equals(end_station_name))
                end_station=station;
        }
        ArrayList<Station> shortestPath=new ArrayList<Station>();
        showPath(end_station,shortestPath);
        String changeLine=getSameLine(shortestPath.get(0),shortestPath.get(1));
        for(int i=0;i<shortestPath.size();i++) {
            if(i>=2) {
                if(!getSameLine(shortestPath.get(i),shortestPath.get(i-1)).equals(changeLine)) {
                    changeLine=getSameLine(shortestPath.get(i),shortestPath.get(i-1));
                    System.out.println("------->换乘"+changeLine);
                }
            }
            System.out.println(shortestPath.get(i).getStation_name());
        }
        }
        else if(number==2) {
        	System.out.print("请输入线路名称:");
        	String line_name=input.next();
        	int flag=1;
        	for(Line line:lines) {
        		if(line.getLine_name().equals(line_name))
        			{
        				System.out.print(line.getStations().toString());
        				flag=0;
        				break;
        			}
        	}
        	if(flag==1) {
        		System.out.print("线路名输入有误");
        	}
        }
        else {
        	System.out.print("输入有误,请重试");
        }
       }
    public static void shortestPath(Station station){
        Queue<Station> queue = new LinkedList<>();
        station.dist=0;
        queue.offer(station);
        
        while(!queue.isEmpty()) {
            Station vertex=queue.poll();
            for(Station linkstation:vertex.getLinkstation()) {
                if(linkstation.dist==MaxValue) {
                    linkstation.dist=vertex.dist+1;
                    queue.offer(linkstation);
                    linkstation.pre=vertex;
                }
            }
        }
    }
    public static void showPath(Station end_station,ArrayList<Station> result) {
        if(end_station.pre!=null)  
            showPath(end_station.pre,result);
        result.add(end_station);
    }
    private static String getSameLine(Station station1,Station station2) {  
        for(String line1:station1.getLine()) {
            for(String line2:station2.getLine()) {
                if(line1.equals(line2))
                    return line1;
            }
        }
        return null;
    }
    

}