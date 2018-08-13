package codingdojo.parkingboy;

import java.util.Collection;

public interface ParkingStrategy {
	ParkingCard park(Collection<ParkingLot> parkingLots,Car car);
}
