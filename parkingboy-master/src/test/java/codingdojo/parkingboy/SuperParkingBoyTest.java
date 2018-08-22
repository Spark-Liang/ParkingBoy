package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SuperParkingBoyTest extends ParkingBoyTest{
	
	@Override
	protected ParkingBoy getTestedParkingBoy() {
		return ParkingBoy.buildParkingBoy(new SuperParkingStrategy());
	}
	
	@Test
	public void should_park_to_the_first_parkingLot_when_its_empty_ratio_is_larger() {
		initDependencies();
		p2.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		parkingBoy.park(carInParkingLot1);
		
		assertThat(p1.getParkingCars(), hasItem(carInParkingLot1));
	}
	
	@Test
	public void should_park_to_the_second_parkingLot_when_its_empty_ratio_is_larger() {
		initDependencies();
		p1.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		parkingBoy.park(carInParkingLot1);
		
		assertThat(p2.getParkingCars(), hasItem(carInParkingLot1));
	}
	
	@Test
	public void should_park_to_the_first_parkingLot_when_its_empty_ratio_is_larger_but_has_less_empty_space() {
		company = new Company();
		p1 = new ParkingLot("1", 5);
		p2 = new ParkingLot("2", 3);
		company.add(p1);
		company.add(p2);
		parkingBoy = getTestedParkingBoy();
		company.employ(parkingBoy);
		company.registerParkingBoyToParkingLot(parkingBoy, p1);
		company.registerParkingBoyToParkingLot(parkingBoy, p2);
		p1.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		parkingBoy.park(carInParkingLot1);
		
		assertThat(p2.getParkingCars(), hasItem(carInParkingLot1));
	}
	
	@Test
	public void should_park_to_the_second_parkingLot_when_its_empty_ratio_is_larger_but_has_less_empty_space() {
		company = new Company();
		p1 = new ParkingLot("1", 5);
		p2 = new ParkingLot("2", 3);
		company.add(p1);
		company.add(p2);
		parkingBoy = getTestedParkingBoy();
		company.employ(parkingBoy);
		company.registerParkingBoyToParkingLot(parkingBoy, p1);
		company.registerParkingBoyToParkingLot(parkingBoy, p2);
		p1.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		parkingBoy.park(carInParkingLot1);
		
		assertThat(p2.getParkingCars(), hasItem(carInParkingLot1));
	}
}
