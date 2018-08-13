package codingdojo.parkingboy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import codingdojo.parkingboy.exception.ParkingLotNameDuplication;

public class Company {

	private List<ParkingLot> parkingLots = new LinkedList<ParkingLot>();
	
	public Integer getParkingLotSize() {
		return parkingLots.size();
	}
	
	public void add(ParkingLot parkingLot) {
		for (ParkingLot parkLot : parkingLots) {
			if (parkLot.getParkingName().equals(parkingLot.getParkingName())) {
				throw new ParkingLotNameDuplication();
			}
		}
		parkingLots.add(parkingLot);
	}

	public void employ(ParkingBoy parkingBoy) {
		parkingBoy.setCompany(this);
	}
	
	public List<ParkingLot> getParkingLots() {
		return new ArrayList<ParkingLot>(parkingLots);
	}

}
