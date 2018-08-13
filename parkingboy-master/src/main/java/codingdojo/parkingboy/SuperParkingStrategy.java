package codingdojo.parkingboy;

import java.util.Collection;
import java.util.Iterator;

import codingdojo.parkingboy.exception.ParkingLotIsAllFull;

public class SuperParkingStrategy extends BaseParkingStrategy {

	@Override
	ParkingLot findSuitableParkingLot(Collection<ParkingLot> parkingLots) {
		ParkingLot mostLargerEmptyRatioParkingLot = null;
		Iterator<ParkingLot> parkingLotIterator = parkingLots.iterator();
		if(parkingLotIterator.hasNext()) {
			mostLargerEmptyRatioParkingLot = parkingLotIterator.next();
			while (parkingLotIterator.hasNext()) {
				ParkingLot parkingLot = parkingLotIterator.next();
				mostLargerEmptyRatioParkingLot = getLargerEmptyRatioParkingLot(mostLargerEmptyRatioParkingLot, parkingLot);
			}
		}
		if(mostLargerEmptyRatioParkingLot == null || !mostLargerEmptyRatioParkingLot.isNotFull()) {
			throw new ParkingLotIsAllFull();
		}
		return mostLargerEmptyRatioParkingLot;
	}

	private ParkingLot getLargerEmptyRatioParkingLot(ParkingLot firstParkingLot, ParkingLot secondParkingLot) {
		if(firstParkingLot.emptyRatio().compareTo(secondParkingLot.emptyRatio()) > 0) {
			return firstParkingLot;
		}else {
			return secondParkingLot;
		}
	}

}
