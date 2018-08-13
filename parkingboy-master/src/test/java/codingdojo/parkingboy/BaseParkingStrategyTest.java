package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import codingdojo.parkingboy.exception.DuplicateCarException;
import codingdojo.parkingboy.exception.InvalidateCarException;

public class BaseParkingStrategyTest {

private List<ParkingLot> parkingLots = new LinkedList<>();
	
	BaseParkingStrategy strategy = spy(BaseParkingStrategy.class);

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
	
	@Test(expected = InvalidateCarException.class)
	public void should_throw_invalidate_car_exception_when_park_the_null_car_into_parkingLot() {
		initData();
		
		strategy.validateCar(parkingLots,null);
	}
	
	@Test
	public void should_success_park_car_when_find_the_suitable_parkinglot() {
		initData();
		ParkingLot suitableParkingLot = parkingLots.get(0);
		when(strategy.findSuitableParkingLot(parkingLots)).thenReturn(suitableParkingLot);
		
		strategy.park(parkingLots, new Car("1"));
		
		assertThat(suitableParkingLot.getParkingCars(), hasItem(new Car("1")));
	}
}
