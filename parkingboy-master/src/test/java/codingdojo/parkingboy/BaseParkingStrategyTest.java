package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.cglib.proxy.Enhancer;

import codingdojo.parkingboy.exception.DuplicateCarException;
import codingdojo.parkingboy.exception.InvalidCarException;
import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public abstract class BaseParkingStrategyTest {

private List<ParkingLot> parkingLots = new LinkedList<>();
	
	BaseParkingStrategy strategy ;

	@Before
	public void setUpBaseParkingStrategyTest() {
		strategy = getTestedBaseParkingStrategy();
	}
	
	protected abstract BaseParkingStrategy getTestedBaseParkingStrategy();
	
	private void initData() {
		ParkingLot p1 = new ParkingLot("1", 2),
				p2 = new ParkingLot("2", 2);
		parkingLots.add(p1);
		parkingLots.add(p2);
	}
	
	@Test(expected = DuplicateCarException.class)
	public void should_throw_duplicate_car_exception_when_the_is_in_one_of_the_parkingLot() {
		initData();
		parkingLots.get(0).park(new Car("1"));
		
		strategy.validateCar(parkingLots,new Car("1"));
	}
	
	@Test(expected = InvalidCarException.class)
	public void should_throw_invalidate_car_exception_when_park_the_null_car_into_parkingLot() {
		initData();
		
		strategy.validateCar(parkingLots,null);
	}
	
	@Test
	public void should_success_park_car_when_find_the_suitable_parkinglot() {
		initData();
		
		strategy.park(parkingLots, new Car("1"));
		
		List<Car> cars = new LinkedList<>();
		for(ParkingLot parkingLot : parkingLots) {
			cars.addAll(parkingLot.getParkingCars());
		}
		assertThat(cars, hasItem(new Car("1")));
	}
	
	@Test(expected = ParkingLotIsAllFull.class)
	public void should_throw_parking_lot_is_full_exception_when_trying_to_park_but_no_space_any_more(){
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		p1.park(new Car("1"));
		p1.park(new Car("2"));
		p2.park(new Car("3"));
		p2.park(new Car("4"));
		
		strategy.park(parkingLots, new Car("5"));
	}
}
