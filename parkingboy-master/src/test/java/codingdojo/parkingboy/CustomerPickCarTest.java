package codingdojo.parkingboy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import codingdojo.parkingboy.exception.CantChangeParkingBoy;

public class CustomerPickCarTest {
	
	@Test
	public void should_pick_the_car_when_the_customer_find_the_right_parking_lot_pick() {
		Manager manager = new Manager();
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		ParkingLot parkingLot = new ParkingLot("A", 2);
		prepareCompany(manager, new ParkingLot[] {parkingLot}, new ParkingBoy[] {parkingBoy});
		manager.assign(parkingLot, parkingBoy);
		Car car = new Car("1");
		ParkingCard card = parkingBoy.park(car);
		ParkingLot parkingLotToPickCar = card.getParkingLot();
		ParkingBoy parkingBoyToPickCar = parkingLotToPickCar.getCurrentParkingBoy();
		
		Car pickedCar = parkingBoyToPickCar.pick(card);
		
		assertEquals(car, pickedCar);
	}
	
	@Test
	public void should_pick_the_car_when_the_assign_parking_lot_to_other_parkingboy() {
		Manager manager = new Manager();
		ParkingBoy oldParkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		ParkingBoy newParkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		ParkingLot parkingLot = new ParkingLot("A", 2);
		prepareCompany(manager, new ParkingLot[] {parkingLot}, new ParkingBoy[] {oldParkingBoy,newParkingBoy});
		manager.assign(parkingLot, oldParkingBoy);
		Car car = new Car("1");
		ParkingCard card = oldParkingBoy.park(car);
		manager.assign(parkingLot, newParkingBoy);
		ParkingLot parkingLotToPickCar = card.getParkingLot();
		ParkingBoy parkingBoyToPickCar = parkingLotToPickCar.getCurrentParkingBoy();
		
		Car pickedCar = parkingBoyToPickCar.pick(card);
		
		assertEquals(car, pickedCar);
	}
	
	@SuppressWarnings("unused")
	//@Test(expected = CantChangeParkingBoy.class)
	public void should_throw_Cant_change_parkingBoy_exception_when_the_assign_parking_lot_to_other_parkingboy_during_a_customer_picking_car() {
		Manager manager = new Manager();
		ParkingBoy oldParkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		ParkingBoy newParkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		ParkingLot parkingLot = new ParkingLot("A", 2);
		prepareCompany(manager, new ParkingLot[] {parkingLot}, new ParkingBoy[] {oldParkingBoy,newParkingBoy});
		manager.assign(parkingLot, oldParkingBoy);
		Car car = new Car("1");
		ParkingCard card = oldParkingBoy.park(car);
		ParkingLot parkingLotToPickCar = card.getParkingLot();
		
		manager.assign(parkingLot, newParkingBoy);
	}
	
	private Company prepareCompany(Manager manager,ParkingLot[] parkingLots,ParkingBoy[] parkingBoys) {
		Company company = new Company();
		if(manager != null) {
			company.employ(manager);
		}
		for(ParkingLot parkingLot : parkingLots) {
			company.add(parkingLot);
		}
		for(ParkingBoy parkingBoy : parkingBoys) {
			company.employ(parkingBoy);
		}
		return company;
	}
}
