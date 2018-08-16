package codingdojo.parkingboy;

import java.util.LinkedList;
import java.util.List;

import codingdojo.parkingboy.exception.CarIsNotFound;
import codingdojo.parkingboy.exception.InvalidCardException;

public class ParkingBoy implements Employee{
	
	private Company company;
	
	private ParkingStrategy strategy;

	private List<ParkingLot> parkingLots = new LinkedList<>();
	
	private ParkingBoy(ParkingStrategy strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Car pick(ParkingCard card) {
		if(card == null) {
			throw new InvalidCardException();
		}
		for (ParkingLot parkingLot : parkingLots) {
			Car car = parkingLot.pick(card);
			if (car != null) {
				return car;
			}
		}
		throw new CarIsNotFound();
	}
	
	public ParkingCard park(Car car) {
		return strategy.park(parkingLots, car);
	}

	public static ParkingBoy buildParkingBoy(ParkingStrategy strategy) {
		return new ParkingBoy(strategy);
	}

	void addParkingLot(ParkingLot parkingLot) {
		parkingLots.add(parkingLot);
	}

	void removeParkingLot(ParkingLot parkingLot) {
		parkingLots.remove(parkingLot);
	}
}
