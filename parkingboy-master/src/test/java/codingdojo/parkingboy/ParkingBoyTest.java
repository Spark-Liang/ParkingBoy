package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import codingdojo.parkingboy.exception.CarIsNotFound;
import codingdojo.parkingboy.exception.DuplicateCarException;
import codingdojo.parkingboy.exception.InvalidCarException;
import codingdojo.parkingboy.exception.InvalidCardException;
import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public abstract class ParkingBoyTest {
	
	protected ParkingBoy parkingBoy;

	protected Company company;
	
	protected ParkingLot p1,p2;
	
	@Before 
	public void setUp() {
		parkingBoy = getTestedParkingBoy();
	}
	
	protected abstract ParkingBoy getTestedParkingBoy();
	
	protected void initDependencies() {
		company = new Company();
		p1 = new ParkingLot("1", 2);
		p2 = new ParkingLot("2", 2);
		company.add(p1);
		company.add(p2);
		parkingBoy = getTestedParkingBoy();
		company.employ(parkingBoy);
		company.registerParkingBoyToParkingLot(parkingBoy, p1);
		company.registerParkingBoyToParkingLot(parkingBoy, p2);
	}
	
	@Test
	public void should_park_when_parking_lot_is_empty() {
		initDependencies();
		
		parkingBoy.park(new Car("1"));	
	}
	
	@Test
	public void should_pick_a_park_when_the_car_is_in_first_parking_lot() {
		initDependencies();
		Car car = new Car("1");
		ParkingCard card1 = p1.park(car);
		
		Car carPicked = parkingBoy.pick(card1);
		
		assertEquals(carPicked, car);
		assertThat(p1.getParkingCars(), not(hasItem(car)));
	}
	
	@Test
	public void should_pick_a_park_when_the_car_is_in_second_parking_lot() {
		initDependencies();
		Car car = new Car("1");
		ParkingCard card1 = p2.park(car);
		
		Car carPicked = parkingBoy.pick(card1);
		
		assertEquals(carPicked, car);
		assertThat(p1.getParkingCars(), not(hasItem(car)));
	}
	
	@Test(expected = ParkingLotIsAllFull.class)
	public void should_throw_parking_lot_is_full_exception_when_trying_to_park_but_no_space_any_more(){
		initDependencies();
		parkingBoy.park(new Car("1"));
		parkingBoy.park(new Car("2"));
		parkingBoy.park(new Car("3"));
		parkingBoy.park(new Car("4"));
		
		parkingBoy.park(new Car("5"));
	}
	
	@Test(expected = DuplicateCarException.class)
	public void should_throw_duplicate_car_exception_when_park_the_same_car_into_parkingLot() {
		initDependencies();
		
		parkingBoy.park(new Car("1"));
		parkingBoy.park(new Car("1"));
	}

	@Test(expected = InvalidCarException.class)
	public void should_throw_invalidate_car_exception_when_park_the_null_car_into_parkingLot() {
		initDependencies();
		
		parkingBoy.park(null);
	}
	
	@Test(expected = CarIsNotFound.class)
	public void should_throw_car_is_not_found_exception_when_the_parking_card_is_not_matched_to_any_car() {
		initDependencies();
		p1.park(new Car("1"));
		p2.park(new Car("2"));
		
		parkingBoy.pick(new ParkingCard(new ParkingLot("B",2)));
	}
	
	
	@Test(expected = InvalidCardException.class)
	public void should_throw_invalidate_card_exception_when_pick_car_use_null_card() {
		initDependencies();
		
		parkingBoy.pick(null);	
	}
}
