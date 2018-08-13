package codingdojo.parkingboy;

import java.util.Collection;

import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public class NormalParkingStrategy extends BaseParkingStrategy{
	
	@Override
	protected ParkingLot findSuitableParkingLot(Collection<ParkingLot> parkingLots) {
		for(ParkingLot parkingLot : parkingLots) {
			if(parkingLot.isNotFull()) {
				return parkingLot;
			}
		}
		throw new ParkingLotIsAllFull();
	}
}
