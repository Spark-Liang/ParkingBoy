package codingdojo.parkingboy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	NormalParkingBoyTest.class,
	SmartParkingBoyTest.class,
	ParkingLotTest.class,
	CompanyTest.class,
	CarTest.class,
	BaseParkingStrategyTest.class,
	NormalParkingStrateryTest.class,
	SmartParkingStrategyTest.class
})
public class ParkingTestSuite {

}
