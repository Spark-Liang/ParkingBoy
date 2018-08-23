package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import codingdojo.parkingboy.exception.CarIsNotFound;
import codingdojo.parkingboy.exception.DuplicateCarException;
import codingdojo.parkingboy.exception.InvalidCarException;
import codingdojo.parkingboy.exception.InvalidCardException;
import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public class ManagerParkCarTest {
	protected Manager manager;

	protected Company company;
	
	protected List<ParkingLot> parkingLots = new LinkedList<>();
	
	protected void initDependencies() {
		company = new Company();
		parkingLots.add(new ParkingLot("A", 2));
		parkingLots.add(new ParkingLot("B", 2));
		company.add(parkingLots.get(0));
		company.add(parkingLots.get(1));
		manager = new Manager();
		company.employ(manager);
	}
	
	@Test
	public void should_park_when_parking_lot_is_empty() {
		initDependencies();
		Car car = new Car("1");
		
		manager.park(car);
		
		assertThat(getAllParkedCars(), hasItem(car));
	}

	@Test
	public void should_pick_a_park_when_the_car_is_in_first_parking_lot() {
		initDependencies();
		Car car = new Car("1");
		ParkingLot p1 = parkingLots.get(0);
		ParkingCard card = p1.park(car);
		
		Car carPicked = manager.pick(card);
		
		assertEquals(carPicked, car);
		assertThat(p1.getParkingCars(), not(hasItem(car)));
	}
	
	@Test
	public void should_pick_a_park_when_the_car_is_in_second_parking_lot() {
		initDependencies();
		Car car = new Car("1");
		ParkingLot p2 = parkingLots.get(1);
		ParkingCard card1 = p2.park(car);
		
		Car carPicked = manager.pick(card1);
		
		assertEquals(carPicked, car);
		assertThat(p2.getParkingCars(), not(hasItem(car)));
	}
	
	@Test(expected = ParkingLotIsAllFull.class)
	public void should_throw_parking_lot_is_full_exception_when_trying_to_park_but_no_space_any_more(){
		initDependencies();
		manager.park(new Car("1"));
		manager.park(new Car("2"));
		manager.park(new Car("3"));
		manager.park(new Car("4"));
		
		manager.park(new Car("5"));
	}
	
	@Test(expected = DuplicateCarException.class)
	public void should_throw_duplicate_car_exception_when_park_the_same_car_into_parkingLot() {
		initDependencies();
		
		manager.park(new Car("1"));
		manager.park(new Car("1"));
	}

	@Test(expected = InvalidCarException.class)
	public void should_throw_invalidate_car_exception_when_park_the_null_car_into_parkingLot() {
		initDependencies();
		
		manager.park(null);
	}
	
	@Test(expected = CarIsNotFound.class)
	public void should_throw_car_is_not_found_exception_when_the_parking_card_is_not_matched_to_any_car() {
		initDependencies();
		ParkingLot p1 = parkingLots.get(0),
				p2 = parkingLots.get(1);
		p1.park(new Car("1"));
		p2.park(new Car("2"));
		
		manager.pick(new ParkingCard(new ParkingLot("B",2)));
	}
	
	
	@Test(expected = InvalidCardException.class)
	public void should_throw_invalidate_card_exception_when_pick_car_use_null_card() {
		initDependencies();
		
		manager.pick(null);	
	}
	
	private List<Car> getAllParkedCars() {
		List<Car> cars = new LinkedList<>();
		for(ParkingLot parkingLot : parkingLots) {
			cars.addAll(parkingLot.getParkingCars());
		}
		return cars;
	}
}
