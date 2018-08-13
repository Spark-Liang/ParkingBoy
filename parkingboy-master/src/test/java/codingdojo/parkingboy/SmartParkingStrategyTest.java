package codingdojo.parkingboy;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public class SmartParkingStrategyTest {
	
	private SmartParkingStrategy strategy = new SmartParkingStrategy();
	
	private List<ParkingLot> parkingLots = new LinkedList<>();
	
	private void initData() {
		ParkingLot p1 = new ParkingLot("1", 2),
				p2 = new ParkingLot("2", 2);
		parkingLots.add(p1);
		parkingLots.add(p2);
	}
	
	
	@Test
	public void should_return_the_first_lot_when_the_first_more_empty() {
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		p1.park(new Car("1"));
		p1.park(new Car("2"));
		Car carInParkingLot2 = new Car("3");
		
		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p1, parkingLot);
	}
	
	
	@Test
	public void should_park_to_the_first_parking_lot_when_the_first_has_space_even_the_second_has_car(){
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		p2.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");

		strategy.park(parkingLots,carInParkingLot1);
		
		List<Car> carsInParkingLot1 = p1.getParkingCars();
		assertThat(carsInParkingLot1, hasItem(carInParkingLot1));
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
		
		strategy.park(parkingLots,new Car("5"));
	}
	
	@Test
	public void should_park_to_the_second_parkingLot_when_second_parkingLot_is_more_empty() {
		ParkingLot p1 = new ParkingLot("1", 3),
				p2 = new ParkingLot("2", 3);
		parkingLots.add(p1);
		parkingLots.add(p2);
		p2.park(new Car("1"));
		Car carInParkingLot1 = new Car("2");
		
		assertTrue(p1.emptySpaceNum() > p2.emptySpaceNum());
		
		strategy.park(parkingLots,carInParkingLot1);
		
		assertThat(p1.getParkingCars(), hasItem(carInParkingLot1));
		
	}
	
}
