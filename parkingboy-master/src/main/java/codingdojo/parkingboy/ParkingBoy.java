package codingdojo.parkingboy;

import codingdojo.parkingboy.exception.CarIsNotFound;
import codingdojo.parkingboy.exception.InvalidateCardException;

public class ParkingBoy {
	
	private Company company;
	
	private ParkingStrategy strategy;
	
	private ParkingBoy(ParkingStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Car pick(ParkingCard card1) {
		if(card1 == null) {
			throw new InvalidateCardException();
		}
		for (ParkingLot parkingLot : company.getParkingLots()) {
			Car car = parkingLot.pick(card1);
			if (car != null) {
				return car;
			}
		}
		throw new CarIsNotFound();
	}
	
	public ParkingCard park(Car car) {
		return strategy.park(company.getParkingLots(), car);
	}

	public static ParkingBoy buildSmartParkingBoy() {
		return new ParkingBoy(new SmartParkingStrategy());
	}

	public static ParkingBoy buildNormalParkingBoy() {
		return new ParkingBoy(new NormalParkingStrategy());
	}

	public static ParkingBoy buildSuperParkingBoy() {
		return new ParkingBoy(new SuperParkingStrategy());
	}
	
}
