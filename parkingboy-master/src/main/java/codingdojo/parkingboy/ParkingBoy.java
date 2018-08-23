package codingdojo.parkingboy;

import java.util.Collection;
import java.util.List;

public class ParkingBoy extends AbstractParkingLotOperator implements Employee{
	
	private Company company;
	
	private ParkingStrategy strategy;

	private ParkingBoy(ParkingStrategy strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public static ParkingBoy buildParkingBoy(ParkingStrategy strategy) {
		return new ParkingBoy(strategy);
	}

	public List<ParkingLot> getAllParkingLots() {
		return company.getAllParkingLotOfParkingBoy(this);
	}

	@Override
	protected ParkingStrategy getStrategy() {
		return strategy;
	}

	@Override
	protected Collection<ParkingLot> getParkingLots() {
		return getAllParkingLots();
	}

	
}
