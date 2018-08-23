package codingdojo.parkingboy;

import java.util.Collection;

import codingdojo.parkingboy.exception.CarIsNotFound;
import codingdojo.parkingboy.exception.InvalidCardException;

public abstract class AbstractParkingLotOperator implements ParkingLotOperator{

	@Override
	public ParkingCard park(Car car) {
		return getStrategy().park(getParkingLots(), car);
	}

	@Override
	public Car pick(ParkingCard card) {
		if(card == null) {
			throw new InvalidCardException();
		}
		for (ParkingLot parkingLot : getParkingLots()) {
			Car car = parkingLot.pick(card);
			if (car != null) {
				return car;
			}
		}
		throw new CarIsNotFound();
	}

	protected abstract ParkingStrategy getStrategy();
	
	protected abstract Collection<ParkingLot> getParkingLots();
}
