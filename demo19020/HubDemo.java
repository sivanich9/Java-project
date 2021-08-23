package demo19020;
import java.util.ArrayList;
import base.*;

class HubDemo extends Hub {

	public HubDemo(Location loc) {
		super(loc);
		list=new ArrayList<>();
	}

	@Override
	public boolean add(Truck truck) {
		if(list.size()>=getCapacity()){
			return false;
		}
		else{
			list.add(truck);
			return true;
		}
	}

	@Override
	public void remove(Truck truck) {
		list.remove(truck);
	}

	@Override
	protected void processQ(int deltaT) {
		int n=list.size();
		for(int i=0;i<n;i++){
			Truck t=list.get(n-i-1);
			Highway nh=getNextHighway(this, Network.getNearestHub(t.getDest()));
			if(getLoc().getX()==t.getDest().getX() && getLoc().getY()==t.getDest().getY()){
				list.remove(t);
				t.setLoc(t.getDest());
			}
			else if(nh!=null){
				t.enter(nh);
				list.remove(t);
			}
		}
	}

	@Override
	public Highway getNextHighway(Hub from, Hub dest){
		int destX=dest.getLoc().getX();
		int destY=dest.getLoc().getY();
		int currX=from.getLoc().getX();
		int currY=from.getLoc().getY();
		for(Highway highway:from.getHighways()){
			int toX=highway.getEnd().getLoc().getX();
			int toY=highway.getEnd().getLoc().getY();

			if(highway.hasCapacity() && ((currX<toX && toX<destX) || (currY<toY && toY<destY))){
				return highway;
			}
		}
		return null;
	}


	private ArrayList<Truck>list;
	
}
