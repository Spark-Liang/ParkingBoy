package codingdojo.parkingboy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	NormalParkingBoyTest.class,
	SmartParkingBoyTest.class,
	SuperParkingBoyTest.class,
	ParkingLotTest.class,
	CompanyTest.class,
	CarTest.class,
	NormalParkingStrateryTest.class,
	SmartParkingStrategyTest.class,
	SuperParkingStrategyTest.class,
	ManagerTest.class,
	ManagerParkCarTest.class,
	CustomerPickCarTest.class
})
public class ParkingTestSuite {

}
