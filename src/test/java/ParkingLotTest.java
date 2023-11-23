import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ParkingLotTest {

    @Test
    public void ParkTheCar() throws Exception{
        Car car = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot lot = new ParkingLot(1, owner);

        Assertions.assertDoesNotThrow(()-> lot.Park(car));
    }

    @Test
    public void ParkTheCarreturnsParkingFull() throws Exception{
        Car car = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot lot = new ParkingLot(0, owner);


        Assertions.assertThrows(ParkingLotFullException.class,()-> lot.Park(car));
    }

    @Test
        public void ParkTheSameCar() throws Exception{
        Car car = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot lot = new ParkingLot(5, owner);
        lot.Park(car);
        Assertions.assertThrows(AlreadyParkedException.class,()-> lot.Park(car));


    }

    @Test
    public void UnparktheCarthrowsErrorInvalidCar() {
        Car car = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot lot = new ParkingLot(5, owner);

        Assertions.assertThrows(InvalidCarException.class, ()->  lot.Unpark(car));
    }

    @Test
    public void SuccesfullUnparking() throws Exception{
        Car car = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot lot = new ParkingLot(5, owner);

        lot.Park(car);

        Assertions.assertDoesNotThrow(()-> lot.Unpark(car));

    }

    @Test
    public void SucesfulCarIsPresentCheck() throws Exception {
        Car car = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot pLot = new ParkingLot(2, owner);

        pLot.Park(car);

        Assertions.assertTrue(pLot.IsParked(car));
    }

    @Test
    public void SucesfulCarIsNotPresentCheck() throws Exception {
        Car car = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot pLot = new ParkingLot(2, owner);

        Assertions.assertFalse(pLot.IsParked(car));
    }

    @Test
    public  void ParkOneCarUnparkanotherCar() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot lot = new ParkingLot(5, owner);

        lot.Park(car1);

        Assertions.assertThrows(InvalidCarException.class, ()->  lot.Unpark(car2));
        Mockito.mock();
    }

    @Test
    public void ParkingFullNotification()throws Exception{
        Car car1 = new Car();

        Owner owner = Mockito.mock(Owner.class);

        ParkingLot lot = new ParkingLot(1, owner);

        lot.Park(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();

    }

    @Test
    public void ParkingFullNotificationShouldNotCalled()throws Exception{
        Car car1 = new Car();

        Owner owner = Mockito.mock(Owner.class);

        ParkingLot lot = new ParkingLot(2, owner);

        lot.Park(car1);
        Mockito.verifyNoInteractions(owner);
    }

    @Test
    public void ParkingAvailableNotification()throws Exception{
        Car car1 = new Car();

        Owner owner = Mockito.mock(Owner.class);

        ParkingLot lot = new ParkingLot(1, owner);

        lot.Park(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();
    }

    @Test
    public void ShouldNotfiyParkingFullAndAvailableNotification() throws Exception {
        Car car1 = new Car();

        Owner owner = Mockito.mock(Owner.class);

        ParkingLot lot = new ParkingLot(1, owner);

        lot.Park(car1);

       Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();

        lot.Unpark(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingAvailable();
    }

    @Test
    public void ShouldNotfiyParkingFullAndAvailableNotification1() throws Exception {
        Car car1 = new Car();

        Owner owner = Mockito.mock(Owner.class);

        ParkingLot lot = new ParkingLot(1, owner);

        lot.Park(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();

        lot.Unpark(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingAvailable();
    }

    @Test
    public void Only1Notificationafterunparking2() throws Exception{

        Car car1 = new Car();
        Car car2 = new Car();
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot lot = new ParkingLot(2, owner);
        lot.Park(car1);
        lot.Park(car2);
        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();
        lot.Unpark(car1);
        lot.Unpark(car2);
        Mockito.verify(owner, Mockito.times(1)).NotifyParkingAvailable();
    }
}
