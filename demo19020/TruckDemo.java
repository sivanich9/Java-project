package demo19020;
import base.*;

class TruckDemo extends Truck {

	TruckDemo(){
		counter+=1;
		id=counter;
		timer=0;
		highwaybool=false;
	}

	@Override
	protected void update(int deltaT) {
		timer+=deltaT;
		if(timer<deltaT){
			return;
		}
		else{
			if(highwaybool){
				double dist=getLoc().distSqrd(currenthyw.getEnd().getLoc());
				double compute=((double)(currenthyw.getMaxSpeed()*deltaT))/1000.0;
				if(compute>=dist && currenthyw.getEnd().add(this)){
					highwaybool=false;
					currenthyw.remove(this);
					currenthyw=null;
					return;
				}
				else if(compute<dist){
					double cos=(highway.getEnd().getLoc().getX()-getLoc().getX())/highway.getEnd().getLoc().distSqrd(getLoc());
					double sin=(highway.getEnd().getLoc().getY()-getLoc().getY())/highway.getEnd().getLoc().distSqrd(getLoc());
					setLoc(new Location(getLoc().getX()+(int)(compute*cos),getLoc().getY()+(int)(compute*sin)));
				}
			}
			else if(getLoc().getX()==getSource().getX() && getLoc().getY()==getSource().getY()){
				setLoc(Network.getNearestHub(getLoc()).getLoc());
			}
		}

	}

	@Override
	public Hub getLastHub() {
		return lasthub;
	}

	@Override
	public void enter(Highway hwy) {
		hwy.add(this);	
		currenthyw=hwy;
		highwaybool=true;
	}

	public Highway getHwy(){
		return highway;
	}

	public void setLasthub(Hub hub) {
		lasthub=hub;
	}

	@Override
	public String getTruckName() {
		return "Truck19020"+Integer.valueOf(id);
	}

	private Highway highway;
	private Hub lasthub;
	private static int counter=0;
	private int id=0;
	private int timer=0;
	private Highway currenthyw;
	private boolean highwaybool;
}
