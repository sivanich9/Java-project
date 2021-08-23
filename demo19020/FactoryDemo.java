package demo19020;
import base.*;

public class FactoryDemo extends Factory {

	@Override
	public Highway createHighway() {
		return new HighwayDemo();
	}

	@Override
	public Hub createHub(Location loc) {
		return new HubDemo(loc);
	}


	@Override
	public Truck createTruck() {
		
		return new TruckDemo();
	}

	@Override
	public Network createNetwork() {
		return new NetworkDemo();
	}


}
