import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AttendantTest {

    ParkingLotSelector parkingLotSelector = new FirstAvailableParkingLotSelectorImpl();

    @Test
    public void AttendentShouldBeAbleToParkInAParkingLot() {
        Attendant attendant = new Attendant(parkingLotSelector);
        ParkingLot lot = new ParkingLot(1);
        attendant.assignParkingLot(lot);
        Car mockCar = Mockito.mock(Car.class);

        Assertions.assertDoesNotThrow(()-> attendant.park(mockCar));
    }

    @Test
    public void AttendentShouldNotBeAbleToParkInParkingFull() throws Exception {
        Attendant attendant = new Attendant(parkingLotSelector);
        ParkingLot lot = new ParkingLot(1);
        attendant.assignParkingLot(lot);
        Car mockCar = Mockito.mock(Car.class);
        Car mockAnotherCar = Mockito.mock(Car.class);
        attendant.park(mockCar);

        Assertions.assertThrows(ParkingLotFullException.class, ()-> attendant.park(mockAnotherCar));
    }

    @Test
    public void AttendantShouldParkOtherLotIfOneLotisFull() throws Exception {
        Attendant attendant = new Attendant(parkingLotSelector);
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);

        attendant.assignParkingLot(lot1);
        attendant.assignParkingLot(lot2);
        Car mockCar1 = Mockito.mock(Car.class);
        Car mockCar2 = Mockito.mock(Car.class);
        attendant.park(mockCar1);

        Assertions.assertDoesNotThrow(()-> attendant.park(mockCar2));
    }

    @Test
    public void AttendantShouldThrowParkingFulIfAllLotsAreFull() throws Exception {
        Attendant attendant = new Attendant(parkingLotSelector);
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);

        attendant.assignParkingLot(lot1);
        attendant.assignParkingLot(lot2);
        Car mockCar1 = Mockito.mock(Car.class);
        Car mockCar2 = Mockito.mock(Car.class);
        attendant.park(mockCar1);
        attendant.park(mockCar2);

        Car mockCar3 = Mockito.mock(Car.class);


        Assertions.assertThrows(ParkingLotFullException.class, ()-> attendant.park(mockCar3));
    }

    @Test
    public void AttendantShouldUnparkTheCar() throws Exception {

        Attendant attendant = new Attendant(parkingLotSelector);
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);

        attendant.assignParkingLot(lot1);
        attendant.assignParkingLot(lot2);
        Car mockCar1 = Mockito.mock(Car.class);
        attendant.park(mockCar1);

        Assertions.assertDoesNotThrow(()-> attendant.unpark(mockCar1));

    }

    @Test
    public void AttendantShouldNotUnparkTheCarTwoTimes() throws Exception {

        Attendant attendant = new Attendant(parkingLotSelector);
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);

        attendant.assignParkingLot(lot1);
        attendant.assignParkingLot(lot2);
        Car mockCar1 = Mockito.mock(Car.class);
        attendant.park(mockCar1);
        attendant.unpark(mockCar1);
        Assertions.assertThrows(InvalidCarException.class, ()-> attendant.unpark(mockCar1));

    }






}
