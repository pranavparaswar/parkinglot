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
}
