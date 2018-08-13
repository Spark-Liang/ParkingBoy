package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class ParkingLotTest {

	@Test
	public void should_return_true_when_parkingLot_is_not_full_and_call_isNotFull_method() {
		ParkingLot parkingLot = new ParkingLot("A", 2);
		parkingLot.park(new Car("1"));
		
		assertTrue(parkingLot.isNotFull());
	}
	
	@Test
	public void should_return_false_when_parkingLot_is_full_and_call_isNotFull_method() {
		ParkingLot parkingLot = new ParkingLot("A", 2);
		parkingLot.park(new Car("1"));
		parkingLot.park(new Car("2"));
		
		assertTrue(!parkingLot.isNotFull());
	}
	
	
	@Test
	public void should_find_the_car_after_parking_the_car_in_this_parkingLot() {
		ParkingLot parkingLot = new ParkingLot("A", 2);
		Car car = new Car("1");
		
		parkingLot.park(car);
		
		List<Car> parkingCars = parkingLot.getParkingCars();
		assertThat(parkingCars, hasItems(car));
	}
	
	@Test
	public void should_pick_the_right_car_when_pick_the_car_with_the_right_card() {
		ParkingLot parkingLot = new ParkingLot("A", 2);
		Car car = new Car("1");
		ParkingCard card = parkingLot.park(car);
		
		Car carPicked = parkingLot.pick(card);
		
		assertEquals(car, carPicked);
	}
	
	@Test
	public void should_get_the_right_empty_num_before_and_after_park_the_car() {
		ParkingLot parkingLot = new ParkingLot("A", 2);
		Car car = new Car("1");
		parkingLot.park(car);
		
		int beforeParked = parkingLot.emptySpaceNum();
		parkingLot.park(new Car("2"));
		int afterParked = parkingLot.emptySpaceNum();
		
		assertEquals(2 - 1, beforeParked);
		assertEquals(beforeParked - 1, afterParked);
	}
	
	
}
