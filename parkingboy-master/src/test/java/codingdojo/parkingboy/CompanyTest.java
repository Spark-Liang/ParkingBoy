package codingdojo.parkingboy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import codingdojo.parkingboy.exception.ParkingLotIsNull;
import codingdojo.parkingboy.exception.ParkingLotNameDuplication;
import codingdojo.parkingboy.exception.ParkingLotNameIsNull;
import codingdojo.parkingboy.exception.ParkingLotNameIsTooLong;
import codingdojo.parkingboy.exception.ParkingLotSizeInvalid;

public class CompanyTest {



	@Test
	public void should_success_create_a_parking_lot() {
		ParkingLot parkingLot = buildParkingLot("", 2);;
		assertEquals(2, parkingLot.getParkingSpace().intValue());
	}
	
	
	@Test
	public void should_success_when_everything_is_right() {
		Company company = new Company();
		assertEquals(0, company.getParkingLotSize().intValue());
		ParkingLot parkingLot = buildParkingLot("", 2);
		
		company.add(parkingLot);
		
		assertEquals(1, company.getParkingLotSize().intValue());
		assertEquals(2, parkingLot.getParkingSpace().intValue());
	}
	
	
	@SuppressWarnings("unused")
	@Test(expected=ParkingLotSizeInvalid.class)
	public void should_throw_exception_when_the_parking_lot_size_is_negative() {
		ParkingLot parkingLot = buildParkingLot("", -2);;
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ParkingLotSizeInvalid.class)
	public void should_throw_exception_when_the_parking_lot_size_is_Null() {
		ParkingLot parkingLot = buildParkingLot("", null);;
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ParkingLotSizeInvalid.class)
	public void should_throw_exception_when_the_parking_lot_size_is_zero() {
		ParkingLot parkingLot = buildParkingLot("", 0);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ParkingLotSizeInvalid.class)
	public void should_throw_exception_when_the_paking_lot_size_is_more_than_10000() {
		ParkingLot parkingLot = buildParkingLot("", 10000);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ParkingLotNameDuplication.class)
	public void should_throw_exception_when_the_paking_lot_is_in_duplicate_name() {
		ParkingLot parkingLot = buildParkingLot(new String("a"), 2);
		ParkingLot parkingLot1 = buildParkingLot(new String("a"), 2);
		Company company = new Company();
		company.add(parkingLot);
		
		company.add(parkingLot1);
	}

	private ParkingLot buildParkingLot(String name, Integer size) {
		return new ParkingLot(name, size);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ParkingLotNameIsNull.class)
	public void should_throw_exception_when_the_parking_lot_has_null_name() {
		ParkingLot parkingLot = buildParkingLot(null, 1);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=ParkingLotNameIsTooLong.class)
	public void should_throw_exception_when_the_parking_lot_has_too_long_name() {
		ParkingLot parkingLot = buildParkingLot("looooooooooooooong name", 1);
	}
	
	@Test(expected = ParkingLotIsNull.class)
	public void should_throw_parkinglot_is_null_exception_when_add_a_null_parkinglot(){
		Company company = new Company();
		
		company.add(null);
	}
}
