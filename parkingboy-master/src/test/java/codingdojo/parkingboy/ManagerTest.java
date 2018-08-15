package codingdojo.parkingboy;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
	
	//should_throw_un_employed_exception_when_assign_a_parkingLot_to_an_parkingboy_not_in_this_company
	
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
		
		validateParkingBoyCanParkCarToParkingLot(parkingBoy,parkingLot);
	}

	//when assign a lot to new parkingboy should the old parkingboy can not park to this lot but the new parkingboy can
	
	private void validateParkingBoyCanParkCarToParkingLot(ParkingBoy parkingBoy, ParkingLot parkingLot) {
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
			Set<Car> intersectionBetweenCarsAndParkedCarsInParkingLot = intersection(new HashSet<>(carsMap.values()), parkedCarsInParkingLotCars);
			assertFalse(intersectionBetweenCarsAndParkedCarsInParkingLot.isEmpty());
		}finally {
			for(ParkingCard card : carsMap.keySet()) {
				parkingBoy.pick(card);
			}
		}
	}
	
	<T> Set<T> intersection(Set<T> s1, Set<T> s2) {
		Set<T> result = new HashSet<T>(s1);
		result.retainAll(s2);
		return result;
	}
}
