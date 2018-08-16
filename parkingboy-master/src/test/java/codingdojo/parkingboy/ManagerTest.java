package codingdojo.parkingboy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import codingdojo.parkingboy.common.Sets;
import codingdojo.parkingboy.exception.ParkingBoyIsNull;
import codingdojo.parkingboy.exception.ParkingLotIsAllFull;
import codingdojo.parkingboy.exception.UnEmployedManager;
import codingdojo.parkingboy.exception.UnEmployedParkingBoy;

public class ManagerTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void should_assign_the_parking_lot_to_parkingboy() {
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(parkingBoy);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		
		manager.assign(parkingLot,parkingBoy);
	}
	
	@Test(expected = UnEmployedManager.class)
	public void should_throw_un_employed_exception_when_Manager_was_not_employed_by_company(){
		Company company = new Company();
		Manager manager = new Manager();
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(parkingBoy);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		
		manager.assign(parkingLot,parkingBoy);
	}
	
	@Test(expected = ParkingBoyIsNull.class)
	public void should_throw_parkingboy_is_null_exception_when_assign_a_parkingLot_to_a_null_parkingboy(){
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(parkingBoy);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		
		manager.assign(parkingLot,null);
	}
	
	@Test(expected = UnEmployedParkingBoy.class)
	public void should_throw_un_employed_exception_when_assign_a_parkingLot_to_an_un_employed_parkingboy(){
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		
		manager.assign(parkingLot,ParkingBoy.buildParkingBoy(new NormalParkingStrategy()));
	}
	
	@Test(expected = UnEmployedParkingBoy.class)
	public void should_throw_un_employed_exception_when_assign_a_parkingLot_to_an_parkingboy_not_in_this_company() {
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		Company otherCompany = new Company();
		otherCompany.employ(parkingBoy);
		
		manager.assign(parkingLot,parkingBoy);
	}
	
	@Test
	public void should_throw_Invalid_parkingLot_exception_when_assign_a_null_parkingLot_to_a_parkingboy(){
		expectedException.expect(InvalidAssignedParkingLot.class);
		expectedException.expectMessage(InvalidAssignedParkingLot.PARKINGLOT_IS_NULL);
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(parkingBoy);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		
		manager.assign(null,parkingBoy);
	}
	
	@Test
	public void should_throw_Invalid_parkingLot_exception_when_assign_a_parkingLot_which_is_not_in_company_to_a_parkingboy(){
		expectedException.expect(InvalidAssignedParkingLot.class);
		expectedException.expectMessage(InvalidAssignedParkingLot.PARKINGLOT_IS_NOT_BELONGED_TO_COMPANY);
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(parkingBoy);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		
		manager.assign(parkingLot,parkingBoy);
	}
	
	@Test
	public void should_a_parkingboy_can_parking_car_to_the_lot_when_assign_a_parkinglot_to_him(){
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingBoy parkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(parkingBoy);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		
		manager.assign(parkingLot,parkingBoy);
		
		validateParkingBoyCanParkCarToParkingLot(parkingBoy,parkingLot,true);
	}

	@Test
	public void should_the_old_parkingboy_can_not_park_to_this_lot_but_the_new_parkingboy_can_after_assign_a_lot_to_new_parkingboy(){
		Company company = new Company();
		Manager manager = new Manager();
		company.employ(manager);
		ParkingBoy oldParkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(oldParkingBoy);
		ParkingLot parkingLot = new ParkingLot("A", 2);
		company.add(parkingLot);
		manager.assign(parkingLot,oldParkingBoy);
		ParkingBoy newParkingBoy = ParkingBoy.buildParkingBoy(new NormalParkingStrategy());
		company.employ(newParkingBoy);
		
		manager.assign(parkingLot, newParkingBoy);
		
		validateParkingBoyCanParkCarToParkingLot(newParkingBoy,parkingLot,true);
		validateParkingBoyCanParkCarToParkingLot(oldParkingBoy,parkingLot,false);
	}
	
	
	
	private void validateParkingBoyCanParkCarToParkingLot(ParkingBoy parkingBoy, ParkingLot parkingLot,boolean canParkCar) {
		int carNum = 0;
		Map<ParkingCard, Car> carsMap = new HashMap<>();
		try {
			while(true) {
				carNum++;
				Car tmpCar = new Car(Integer.toString(carNum));
				ParkingCard tmpCard = parkingBoy.park(tmpCar);
				carsMap.put(tmpCard, tmpCar);
			}
		} catch (ParkingLotIsAllFull e) {
			Set<Car> parkedCarsInParkingLotCars = new HashSet<>(parkingLot.getParkingCars());
			Set<Car> intersectionBetweenCarsAndParkedCarsInParkingLot = Sets.intersection(new HashSet<>(carsMap.values()), parkedCarsInParkingLotCars);
			if(canParkCar) {
				assertFalse(intersectionBetweenCarsAndParkedCarsInParkingLot.isEmpty());
			}else {
				assertTrue(intersectionBetweenCarsAndParkedCarsInParkingLot.isEmpty());
			}
		}finally {
			for(ParkingCard card : carsMap.keySet()) {
				parkingBoy.pick(card);
			}
		}
	}
	
	
}
