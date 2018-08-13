package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SuperParkingBoyTest extends ParkingBoyTest{
	private Company company;
	
	private ParkingLot p1,p2;
	
	private ParkingBoy parkingBoy;
	
	@Override
	protected ParkingBoy getTestedParkingBoy() {
		return ParkingBoy.buildSuperParkingBoy();
	}
	
	@Before
	public void setUpInSmartParkingBoyTest() {
		initDependencies();
	}
	
	private void initDependencies() {
		company = new Company();
		p1 = new ParkingLot("1", 3);
		p2 = new ParkingLot("2", 3);
		company.add(p1);
		company.add(p2);
		parkingBoy = getTestedParkingBoy();
		company.employ(parkingBoy);
	}
	
	@Test
	public void should_park_to_the_first_parkingLot_when_its_empty_ratio_is_larger() {
		p2.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		parkingBoy.park(carInParkingLot1);
		
		assertThat(p1.getParkingCars(), hasItem(carInParkingLot1));
	}
	
	@Test
	public void should_park_to_the_second_parkingLot_when_its_empty_ratio_is_larger() {
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
		p1.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		parkingBoy.park(carInParkingLot1);
		
		assertThat(p2.getParkingCars(), hasItem(carInParkingLot1));
	}
}
