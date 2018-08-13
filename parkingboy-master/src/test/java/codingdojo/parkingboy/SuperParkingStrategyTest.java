package codingdojo.parkingboy;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class SuperParkingStrategyTest extends BaseParkingStrategyTest{
	
	private SuperParkingStrategy strategy = new SuperParkingStrategy();
	
	private List<ParkingLot> parkingLots = new LinkedList<>();
	
	@Override
	protected BaseParkingStrategy getTestedBaseParkingStrategy() {
		return new SuperParkingStrategy();
	}
	
	private void initData() {
		ParkingLot p1 = new ParkingLot("1", 2),
				p2 = new ParkingLot("2", 2);
		parkingLots.add(p1);
		parkingLots.add(p2);
	}
	
	@Test
	public void should_return_the_first_parkingLot_when_its_empty_ratio_is_larger() {
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		p2.park(new Car("1"));
		
		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p1, parkingLot);
	}
	
	@Test
	public void should_return_the_second_parkingLot_when_its_empty_ratio_is_larger(){
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		p1.park(new Car("1"));

		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p2, parkingLot);
	}
	
	@Test
	public void should_return_the_third_parkingLot_when_its_empty_ratio_is_larger(){
		initData();
		ParkingLot p1 = parkingLots.get(0);
		ParkingLot p2 = parkingLots.get(1);
		ParkingLot p3 = new ParkingLot("C", 3);
		parkingLots.add(p3);
		p1.park(new Car("1"));
		p2.park(new Car("2"));

		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p3, parkingLot);
	}
	
	@Test
	public void should_return_the_first_parkingLot_when_its_empty_ratio_is_larger_but_has_less_empty_space(){
		ParkingLot p1 = new ParkingLot("1", 5),
				p2 = new ParkingLot("2", 3);
		parkingLots.add(p1);
		parkingLots.add(p2);
		p2.park(new Car("1"));
		
		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p1, parkingLot);
	}
	
	@Test
	public void should_return_the_second_parkingLot_when_its_empty_ratio_is_larger_but_has_less_empty_space(){
		ParkingLot p1 = new ParkingLot("1", 5),
				p2 = new ParkingLot("2", 3);
		parkingLots.add(p1);
		parkingLots.add(p2);
		p1.park(new Car("1"));
		
		ParkingLot parkingLot = strategy.findSuitableParkingLot(parkingLots);
		
		assertEquals(p2, parkingLot);
	}
	
	
}
