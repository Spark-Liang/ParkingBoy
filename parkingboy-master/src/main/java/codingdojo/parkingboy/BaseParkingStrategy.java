package codingdojo.parkingboy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import codingdojo.parkingboy.exception.DuplicateCarException;
import codingdojo.parkingboy.exception.InvalidCarException;

public abstract class BaseParkingStrategy implements ParkingStrategy {

	@Override
	public final ParkingCard park(Collection<ParkingLot> parkingLots, Car car) {
		validateCar(parkingLots,car);
		ParkingLot suitableParkingLot = findSuitableParkingLot(parkingLots);
		return suitableParkingLot.park(car);
	}

	abstract ParkingLot findSuitableParkingLot(Collection<ParkingLot> parkingLots);
	
	void validateCar(Collection<ParkingLot> parkingLots, Car car) {
		if(car == null) {
			throw new InvalidCarException();
		}
		for(Car carInParkingLot : getAllCars(parkingLots)) {
			if (carInParkingLot.equals(car)) {
				throw new DuplicateCarException();
			}
		}
	}
	
	private List<Car> getAllCars(Collection<ParkingLot> parkingLots) {
		List<Car> cars = new LinkedList<>();
		for(ParkingLot parkingLot :parkingLots) {
			cars.addAll(parkingLot.getParkingCars());
		}
		return cars;
	}
}
