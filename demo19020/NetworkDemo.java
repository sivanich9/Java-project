package demo19020;

import java.util.ArrayList;

import base.*;

public class NetworkDemo extends Network {

    NetworkDemo(){
        hubs=new ArrayList<>();
        hyws=new ArrayList<>();
        trucks=new ArrayList<>();
    }

    @Override
    public void add(Hub hub) {
        hubs.add(hub);
    }

    @Override
    public void add(Highway hwy) {
        hyws.add(hwy);
    }

    @Override
    public void add(Truck truck) {
        trucks.add(truck);
    }

    @Override
    public void start() {
        int i;int n1=hubs.size();
        for(i=0;i<n1;i++){
            hubs.get(i).start();
        }
        int n2=trucks.size();
        for(i=0;i<n2;i++){
            trucks.get(i).start();
        }
    }

    @Override
    public void redisplay(Display disp) {
        int i;int n1=hubs.size();
        for(i=0;i<n1;i++){
            hubs.get(i).draw(disp);;
        }
        int n2=hyws.size();
        for(i=0;i<n2;i++){
            hyws.get(i).draw(disp);
        }
        int n3=trucks.size();
        for(i=0;i<n3;i++){
            trucks.get(i).draw(disp);;
        }
    }

    @Override
    protected Hub findNearestHubForLoc(Location loc) {
        int i,n=hubs.size();
        Location loc1=hubs.get(0).getLoc();Hub hub=hubs.get(0);
        double distance=Math.sqrt((loc.getX()-loc1.getX())*(loc.getX()-loc1.getX())+(loc.getY()-loc1.getY())*(loc.getY()-loc1.getY()));
        for(i=1;i<n;i++){
            loc1=hubs.get(i).getLoc();
            double temp=Math.sqrt((loc.getX()-loc1.getX())*(loc.getX()-loc1.getX())+(loc.getY()-loc1.getY())*(loc.getY()-loc1.getY()));
            if(distance>temp){
                distance=temp;
                hub=hubs.get(i);
            }
        }
        return hub;
    }
    
    private ArrayList<Hub>hubs;
    private ArrayList<Highway>hyws;
    private ArrayList<Truck>trucks;
}
