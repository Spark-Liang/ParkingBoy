package codingdojo.parkingboy;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public class SmartParkingStrategyTest extends BaseParkingStrategyTest{
	
	private SmartParkingStrategy strategy = new SmartParkingStrategy();
	
	private List<ParkingLot> parkingLots = new LinkedList<>();
	
	@Override
	protected BaseParkingStrategy getTestedBaseParkingStrategy() {
		return new SmartParkingStrategy();
	}
	
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
		p2.park(new Car("1"));
		
		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p1, parkingLot);
	}
	
	
	@Test
	public void should_return_the_second_lot_when_the_second_more_empty(){
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		p1.park(new Car("1"));

		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p2, parkingLot);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = ParkingLotIsAllFull.class)
	public void should_throw_parking_lot_is_full_exception_when_trying_to_park_but_no_space_any_more(){
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		p1.park(new Car("1"));
		p1.park(new Car("2"));
		p2.park(new Car("3"));
		p2.park(new Car("4"));
		
		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
	}
	
	
}
