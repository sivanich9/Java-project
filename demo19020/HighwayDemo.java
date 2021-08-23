package demo19020;
import java.util.ArrayList;
import base.*;

class HighwayDemo extends Highway {

	public HighwayDemo() {
		list=new ArrayList<>();
	}

	@Override
	public boolean hasCapacity() {
		if(list.size()>=getCapacity()){
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public boolean add(Truck truck) {
		if(hasCapacity()){
			list.add(truck);
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void remove(Truck truck) {
		list.remove(truck);
	}

	private ArrayList<Truck>list;
}
