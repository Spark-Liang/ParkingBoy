package codingdojo.parkingboy;

import java.util.ArrayList;
import java.util.List;

import codingdojo.parkingboy.exception.ParkingLotIsNull;
import codingdojo.parkingboy.exception.ParkingLotNameDuplication;

public class Company {

	private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
	private List<ParkingBoy> employedParkingBoys = new ArrayList<>();
	
	public Integer getParkingLotSize() {
		return parkingLots.size();
	}
	
	public void add(ParkingLot parkingLot) {
		validateParkingLot(parkingLot);
		parkingLots.add(parkingLot);
	}

	private void validateParkingLot(ParkingLot parkingLot) {
		if(parkingLot == null) {
			throw new ParkingLotIsNull();
		}
		for (ParkingLot parkLot : parkingLots) {
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
		return new ArrayList<ParkingLot>(parkingLots);
	}

	List<ParkingBoy> getEmployedParkingBoy() {
		return new ArrayList<>(employedParkingBoys);
	}

}
