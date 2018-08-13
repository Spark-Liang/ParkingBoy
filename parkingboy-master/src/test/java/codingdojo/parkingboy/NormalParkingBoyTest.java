package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import codingdojo.parkingboy.exception.CarIsNotFound;
import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public class NormalParkingBoyTest {

	private Company company;
	
	private ParkingLot p1,p2;
	
	private ParkingBoy parkingBoy;
	
	private void initDependencies() {
		company = new Company();
		p1 = new ParkingLot("1", 2);
		p2 = new ParkingLot("2", 2);
		company.add(p1);
		company.add(p2);
		parkingBoy = ParkingBoy.buildNormalParkingBoy();
		company.employ(parkingBoy);
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
	public void should_not_have_the_car_when_pick_the_car_from_the_parkingLot() {
		initDependencies();
		Car carInParkingLot1_1 = new Car("1");
		ParkingCard parkingCard = p1.park(carInParkingLot1_1);

		Car car = parkingBoy.pick(parkingCard);
		
		List<Car> cars = p1.getParkingCars();
		assertEquals(carInParkingLot1_1, car);
		assertThat(cars, not(hasItems(carInParkingLot1_1)));
	}
	
	@Test
	public void should_pick_a_car_when_the_car_is_in_the_second_parking_lot(){
		initDependencies();
		Car carInParkingLot2 = new Car("1");
		ParkingCard cardForcarInParkingLot2 = p2.park(carInParkingLot2);
		
		Car car = parkingBoy.pick(cardForcarInParkingLot2);
		
		assertEquals(carInParkingLot2, car);
		assertThat(p2.getParkingCars(), not(hasItem(carInParkingLot2)));
	}
	
	@Test
	public void should_park_to_the_first_parking_lot_when_the_first_has_space_even_the_second_has_car(){
		initDependencies();
		Car carInParkingLot1_1 = new Car("1"),
			carInParkingLot1_2 = new Car("2");
		parkingBoy.park(carInParkingLot1_1);
		ParkingCard cardForcarInParkingLot1_2 = parkingBoy.park(carInParkingLot1_2);
		Car carInParkingLot2 = new Car("3");
		parkingBoy.park(carInParkingLot2);
		parkingBoy.pick(cardForcarInParkingLot1_2);
		Car carInParkingLot1_3 = new Car("4");
		
		assertTrue(p1.isNotFull());
		assertTrue(!p2.getParkingCars().isEmpty());
		
		parkingBoy.park(carInParkingLot1_3);
		
		List<Car> carsInParkingLot1 = p1.getParkingCars();
		assertThat(carsInParkingLot1, hasItem(carInParkingLot1_3));
	}
	
	@Test(expected = ParkingLotIsAllFull.class)
	public void should_throw_parking_lot_is_full_exception_when_trying_to_park_but_no_space_any_more(){
		initDependencies();
		parkingBoy.park(new Car("1"));
		parkingBoy.park(new Car("2"));
		parkingBoy.park(new Car("3"));
		parkingBoy.park(new Car("4"));
		
		assertTrue(!p1.isNotFull());
		assertTrue(!p2.isNotFull());
		
		parkingBoy.park(new Car("5"));
	}
	
	

	
	@Test(expected = CarIsNotFound.class)
	public void should_throw_car_is_not_found_exception_when_the_parking_card_is_not_matched_to_any_car() {
		initDependencies();
		p1.park(new Car("1"));
		p2.park(new Car("2"));
		
		parkingBoy.pick(new ParkingCard());
	}
	
}
