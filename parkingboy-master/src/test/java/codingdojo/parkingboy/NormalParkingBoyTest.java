package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class NormalParkingBoyTest extends ParkingBoyTest{

	private Company company;
	
	private ParkingLot p1,p2;
	
	private ParkingBoy parkingBoy;
	
	@Override
	protected ParkingBoy getTestedParkingBoy() {
		return ParkingBoy.buildNormalParkingBoy();
	}
	
	private void initDependencies() {
		company = new Company();
		p1 = new ParkingLot("1", 2);
		p2 = new ParkingLot("2", 2);
		company.add(p1);
		company.add(p2);
		parkingBoy = getTestedParkingBoy();
		company.employ(parkingBoy);
	}
	
	@Test
	public void should_park_when_parking_lot_is_empty() {
		initDependencies();
		
		parkingBoy.park(new Car("1"));	
	}
	
	
	@Test
	public void should_park_a_car_to_the_first_parking_lot_when_the_first_parking_lot_is_not_full(){
		initDependencies();
		Car carInParkingLot1_1 = new Car("1"),
			carInParkingLot1_2 = new Car("2");
		p1.park(carInParkingLot1_1);
		
		parkingBoy.park(carInParkingLot1_2);
		
		List<Car> cars = p1.getParkingCars();
		assertThat(cars, hasItems(carInParkingLot1_1,carInParkingLot1_2));
	}
	
	@Test
	public void should_park_to_the_second_parking_lot_when_the_first_is_full() {
		initDependencies();
		p1.park(new Car("1"));
		p1.park(new Car("2"));
		Car carInParkingLot2 = new Car("3");
		
		parkingBoy.park(carInParkingLot2);
		
		List<Car> carsInParkingLot2 = p2.getParkingCars();
		assertThat(carsInParkingLot2, hasItem(carInParkingLot2));
	}

	@Test
	public void should_park_to_the_first_parking_lot_when_the_first_has_space_even_the_second_has_car(){
		initDependencies();
		p1.park(new Car("1"));
		p2.park(new Car("2"));
		Car carInParkingLot1 = new Car("3");
		
		parkingBoy.park(carInParkingLot1);
		
		List<Car> carsInParkingLot1 = p1.getParkingCars();
		assertThat(carsInParkingLot1, hasItem(carInParkingLot1));
	}
	
}
