package codingdojo.parkingboy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import codingdojo.parkingboy.exception.ParkingLotIsNull;
import codingdojo.parkingboy.exception.ParkingLotNameDuplication;

public class Company {

	private Map<ParkingLot, ParkingBoy> parkingLotMap = new HashMap<>();
	private List<ParkingBoy> employedParkingBoys = new ArrayList<>();
	
	public Integer getParkingLotSize() {
		return parkingLotMap.size();
	}
	
	public void add(ParkingLot parkingLot) {
		validateParkingLot(parkingLot);
		parkingLotMap.put(parkingLot, null);
	}

	private void validateParkingLot(ParkingLot parkingLot) {
		if(parkingLot == null) {
			throw new ParkingLotIsNull();
		}
		for (ParkingLot parkLot : parkingLotMap.keySet()) {
			if (parkLot.getParkingName().equals(parkingLot.getParkingName())) {
				throw new ParkingLotNameDuplication();
			}
		}
	}

	public void employ(Employee employee) {
		employee.setCompany(this);
		if(employee instanceof ParkingBoy) {
			ParkingBoy parkingBoy = (ParkingBoy)employee;
			employedParkingBoys.add(parkingBoy);
		}
	}
	
	public List<ParkingLot> getParkingLots() {
		return new ArrayList<>(parkingLotMap.keySet());
	}

	List<ParkingBoy> getEmployedParkingBoy() {
		return new ArrayList<>(employedParkingBoys);
	}

	List<ParkingLot> getAllParkingLotOfParkingBoy(ParkingBoy parkingBoy) {
		List<ParkingLot> parkingLotsOfParkingBoy = new LinkedList<>();
		for(Map.Entry<ParkingLot, ParkingBoy> entry : parkingLotMap.entrySet()) {
			if(parkingBoy.equals(entry.getValue())) {
				parkingLotsOfParkingBoy.add(entry.getKey());
			}
		}
		return parkingLotsOfParkingBoy;
	}

	void registerParkingBoyToParkingLot(ParkingBoy parkingBoy, ParkingLot parkingLot) {
		parkingLotMap.put(parkingLot, parkingBoy);
		parkingLot.setCurrentParkingBoy(parkingBoy);
	}

}
