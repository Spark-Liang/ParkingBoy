package codingdojo.parkingboy;

import java.util.List;

import codingdojo.parkingboy.exception.CarIsNotFound;
import codingdojo.parkingboy.exception.InvalidCardException;

public class ParkingBoy implements Employee{
	
	private Company company;
	
	private ParkingStrategy strategy;

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
		for (ParkingLot parkingLot : getAllParkingLots()) {
			Car car = parkingLot.pick(card);
			if (car != null) {
				return car;
			}
		}
		throw new CarIsNotFound();
	}
	
	public ParkingCard park(Car car) {
		return strategy.park(getAllParkingLots(), car);
	}

	public static ParkingBoy buildParkingBoy(ParkingStrategy strategy) {
		return new ParkingBoy(strategy);
	}

	public List<ParkingLot> getAllParkingLots() {
		return company.getAllParkingLotOfParkingBoy(this);
	}

}
