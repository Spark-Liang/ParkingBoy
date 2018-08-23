package codingdojo.parkingboy;

public interface ParkingLotOperator {
	ParkingCard park(Car car);
	
	Car pick(ParkingCard card);
}
