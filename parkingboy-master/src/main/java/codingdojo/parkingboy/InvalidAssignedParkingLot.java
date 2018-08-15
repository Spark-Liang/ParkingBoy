package codingdojo.parkingboy;

public class InvalidAssignedParkingLot extends RuntimeException {
	
	public InvalidAssignedParkingLot(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String PARKINGLOT_IS_NULL = "ParkingLot is null";

	public static final String PARKINGLOT_IS_NOT_BELONGED_TO_COMPANY = "parkinglot is not belonged to the company";
	
}
