package codingdojo.parkingboy;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public class NormalParkingStrateryTest {
	
	private List<ParkingLot> parkingLots = new LinkedList<>();
	
	NormalParkingStrategy strategy = new NormalParkingStrategy();

	private void initData() {
		ParkingLot p1 = new ParkingLot("1", 2),
				p2 = new ParkingLot("2", 2);
		parkingLots.add(p1);
		parkingLots.add(p2);
	}
	
	@Test
	public void should_park_when_parking_lot_is_empty() {
		parkingLots.add(new ParkingLot("1", 2));
		
		strategy.park(parkingLots,new Car("1"));	
	}
	
	@Test
	public void should_return_the_first_lot_when_the_first_lot_is_not_full(){
		initData();
		ParkingLot suitableParkingLot = parkingLots.get(0);
		Car carInParkingLot = new Car("1");
		suitableParkingLot.park(carInParkingLot);
		
		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(suitableParkingLot, parkingLot);
	}
	
	@Test
	public void should_return_the_first_lot_when_the_second_lot_is_not_full_and_more_empty() {
		initData();
		ParkingLot suitableParkingLot = parkingLots.get(0);
		Car carInParkingLot = new Car("1");
		suitableParkingLot.park(carInParkingLot);
		
		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(suitableParkingLot, parkingLot);
	}
	
	
	@Test
	public void should_return_the_first_lot_when_the_first_has_space_even_the_second_has_car(){
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		p2.park(new Car("1"));

		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p1, parkingLot);
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
		
		strategy.findSuitableParkingLot(parkingLots);
	}
}
