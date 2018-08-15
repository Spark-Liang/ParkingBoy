package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SmartParkingBoyTest extends ParkingBoyTest{

	@Override
	protected ParkingBoy getTestedParkingBoy() {
		return ParkingBoy.buildParkingBoy(new SmartParkingStrategy());
	}
	
	@Before
	public void setUpInSmartParkingBoyTest() {
		initDependencies();
	}
	
	@Test
	public void should_park_to_the_first_parkingLot_when_first_parkingLot_is_more_empty() {
		initDependencies();
		p2.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		parkingBoy.park(carInParkingLot1);
		
		assertThat(p1.getParkingCars(), hasItem(carInParkingLot1));
		
	}

	
	@Test
	public void should_park_to_the_second_parkingLot_when_second_parkingLot_is_more_empty() {
		initDependencies();
		p1.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		parkingBoy.park(carInParkingLot1);
		
		assertThat(p2.getParkingCars(), hasItem(carInParkingLot1));
		
	}
	
}
