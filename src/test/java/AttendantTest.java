import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AttendantTest {

    @Test
    public void AttendentShouldBeAbleToParkInAParkingLot() {
        Attendant attendant = new Attendant();
        ParkingLot lot = new ParkingLot(1);
        attendant.assignParkingLot(lot);
        Car mockCar = Mockito.mock(Car.class);

        Assertions.assertDoesNotThrow(()-> attendant.park(mockCar));
    }

    @Test
    public void AttendentShouldNotBeAbleToParkInParkingFull() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot lot = new ParkingLot(1);
        attendant.assignParkingLot(lot);
        Car mockCar = Mockito.mock(Car.class);
        attendant.park(mockCar);

        Assertions.assertThrows(ParkingLotFullException.class, ()-> attendant.park(mockCar));
    }

    @Test
    public void AttendantShouldParkOtherLotIfOneLotisFull() throws Exception {
        Attendant attendant = new Attendant();
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
        Attendant attendant = new Attendant();
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


}
