package codingdojo.parkingboy;

public class ParkingCard {

	private ParkingLot parkingLot;
	
	public ParkingCard(ParkingLot parkingLot) {
		super();
		this.parkingLot = parkingLot;
	}

	ParkingLot getParkingLot() {
		return parkingLot;
	}

}
