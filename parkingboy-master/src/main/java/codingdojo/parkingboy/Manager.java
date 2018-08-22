package codingdojo.parkingboy;

import codingdojo.parkingboy.exception.ParkingBoyIsNull;
import codingdojo.parkingboy.exception.UnEmployedManager;
import codingdojo.parkingboy.exception.UnEmployedParkingBoy;

public class Manager implements Employee{

	private Company company;
	
	public void assign(ParkingLot parkingLot, ParkingBoy parkingBoy) {
		if(company == null) {
			throw new UnEmployedManager();
		}
		validateParkingLot(parkingLot);
		validateParkingBoy(parkingBoy);
		company.registerParkingBoyToParkingLot(parkingBoy,parkingLot);
	}

	private void validateParkingLot(ParkingLot parkingLot) {
		if(parkingLot == null) {
			throw new InvalidAssignedParkingLot(InvalidAssignedParkingLot.PARKINGLOT_IS_NULL);
		}
		if(!company.getParkingLots().contains(parkingLot)) {
			throw new InvalidAssignedParkingLot(InvalidAssignedParkingLot.PARKINGLOT_IS_NOT_BELONGED_TO_COMPANY);
		}
	}

	private void validateParkingBoy(ParkingBoy parkingBoy) {
		if(parkingBoy == null) {
			throw new ParkingBoyIsNull();
		}
		if(!company.getEmployedParkingBoy().contains(parkingBoy)) {
			throw new UnEmployedParkingBoy();
		}
	}
	
	@Override
	public void setCompany(Company company) {
		this.company = company;
	}

}
