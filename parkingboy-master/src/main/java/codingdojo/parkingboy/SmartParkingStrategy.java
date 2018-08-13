package codingdojo.parkingboy;

import java.util.Collection;
import java.util.Iterator;

import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public class SmartParkingStrategy extends BaseParkingStrategy{

	@Override
	protected ParkingLot findSuitableParkingLot(Collection<ParkingLot> parkingLots) {
		ParkingLot maxSpaceParkingLot = null;
		Iterator<ParkingLot> parkingLotIterator = parkingLots.iterator();
		if(parkingLotIterator.hasNext()) {
			maxSpaceParkingLot = parkingLotIterator.next();
			while (parkingLotIterator.hasNext()) {
				ParkingLot parkingLot = parkingLotIterator.next();
				if(maxSpaceParkingLot.emptySpaceNum() < parkingLot.emptySpaceNum()) {
					maxSpaceParkingLot = parkingLot;
				}
			}
		}
		if(maxSpaceParkingLot == null || !maxSpaceParkingLot.isNotFull()) {
			throw new ParkingLotIsAllFull();
		}
		return maxSpaceParkingLot;
	}

}
