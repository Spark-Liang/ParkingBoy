package codingdojo.parkingboy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import codingdojo.parkingboy.exception.ParkingLotNameIsNull;
import codingdojo.parkingboy.exception.ParkingLotNameIsTooLong;
import codingdojo.parkingboy.exception.ParkingLotSizeInvalid;

public class ParkingLot {
	private static final int MIN_PARKING_SPACE = 0;
	private static final int MAX_PARKING_SPACE = 10000;
	
	private Integer parkingSpace;
	
	private String parkingName;
	
	private ParkingBoy currentParkingBoy;
	
	private Map<ParkingCard,Car> parkedCars = new HashMap<ParkingCard, Car>();
	
	public ParkingLot(String name, Integer parkingSpace) {	
		checkParameter(name, parkingSpace);
		this.parkingSpace = parkingSpace;
		this.setParkingName(name);
	}

	private void checkParameter(String name, Integer parkingSpace) {
		if(parkingSpaceIsValid(parkingSpace)) {
			throw new ParkingLotSizeInvalid();
		}
		checkParkingLotName(name);
	}

	private void checkParkingLotName(String name) {
		if(null==name) {
			throw new ParkingLotNameIsNull();
		}
		if(name.length() > 10) {
			throw new ParkingLotNameIsTooLong();
		}
	}

	private boolean parkingSpaceIsValid(Integer parkingSpace) {		
		return null==parkingSpace||parkingSpace <= MIN_PARKING_SPACE || parkingSpace >= MAX_PARKING_SPACE;
	}

	public Integer getParkingSpace() {
		return parkingSpace;
	}

	public List<Car> getParkingCars() {
		return new LinkedList<Car>(parkedCars.values());
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public boolean isNotFull() {
		return parkedCars.size() < parkingSpace;
	}

	public ParkingCard park(Car car) {
		ParkingCard newCard = new ParkingCard(this);
		parkedCars.put(newCard, car);
		return newCard;
	}

	public Car pick(ParkingCard card1) {
		return parkedCars.remove(card1);
	}
	
	int emptySpaceNum() {
		return parkingSpace - parkedCars.size();
	}

	public Double emptyRatio() {
		return (double) emptySpaceNum()/parkingSpace;
	}

	ParkingBoy getCurrentParkingBoy() {
		return currentParkingBoy;
	}

	void setCurrentParkingBoy(ParkingBoy parkingBoy) {
		this.currentParkingBoy = parkingBoy;
	}

	
}
