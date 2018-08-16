package codingdojo.parkingboy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerPickCarTest {
	
	@Test
	public void should_pick_the_car_when_the_customer_find_the_right_parking_lot_pick() {
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(parkingBoy);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		manager.assign(parkingLot, parkingBoy);
		Car car = new Car("1");
		ParkingCard card = parkingBoy.park(car);
		ParkingLot parkingLotToPickCar = card.getParkingLot();
		ParkingBoy parkingBoyToPickCar = parkingLotToPickCar.getCurrentParkingBoy();
		
		Car pickedCar = parkingBoyToPickCar.pick(card);
		
		assertEquals(car, pickedCar);
	}
	
	//should_pick_the_car_when_the_assign_parking_lot_to_other_parkingboy
}
