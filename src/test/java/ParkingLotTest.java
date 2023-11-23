import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ParkingLotTest {

    @Test
    public void ParkTheCar() throws Exception{
        Car car = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(1);
        lot.addObserver(owner);

        Assertions.assertDoesNotThrow(()-> lot.Park(car));
    }

    @Test
    public void ParkTheCarreturnsParkingFull() throws Exception{
        Car car = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(0);
        lot.addObserver(owner);

        Assertions.assertThrows(ParkingLotFullException.class,()-> lot.Park(car));
    }

    @Test
        public void ParkTheSameCar() throws Exception{
        Car car = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(5);
        lot.addObserver(owner);
        lot.Park(car);
        Assertions.assertThrows(AlreadyParkedException.class,()-> lot.Park(car));


    }

    @Test
    public void UnparktheCarthrowsErrorInvalidCar() {
        Car car = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(5);

        Assertions.assertThrows(InvalidCarException.class, ()->  lot.Unpark(car));
    }

    @Test
    public void SuccesfullUnparking() throws Exception{
        Car car = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(5);

        lot.Park(car);

        Assertions.assertDoesNotThrow(()-> lot.Unpark(car));

    }

    @Test
    public void SucesfulCarIsPresentCheck() throws Exception {
        Car car = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot pLot = new ParkingLot(2);
        pLot.addObserver(owner);

        pLot.Park(car);

        Assertions.assertTrue(pLot.IsParked(car));
    }

    @Test
    public void SucesfulCarIsNotPresentCheck() throws Exception {
        Car car = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot pLot = new ParkingLot(2);
        pLot.addObserver(owner);

        Assertions.assertFalse(pLot.IsParked(car));
    }

    @Test
    public  void ParkOneCarUnparkanotherCar() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(5);
        lot.addObserver(owner);

        lot.Park(car1);

        Assertions.assertThrows(InvalidCarException.class, ()->  lot.Unpark(car2));
        Mockito.mock();
    }

    @Test
    public void ParkingFullNotification()throws Exception{
        Car car1 = new Car();

        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);

        ParkingLot lot = new ParkingLot(1);
        lot.addObserver(owner);

        lot.Park(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();

    }

    @Test
    public void ParkingFullNotificationShouldNotCalled()throws Exception{
        Car car1 = new Car();

        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);

        ParkingLot lot = new ParkingLot(2);
        lot.addObserver(owner);

        lot.Park(car1);
        Mockito.verifyNoInteractions(owner);
    }

    @Test
    public void ParkingAvailableNotification()throws Exception{
        Car car1 = new Car();

        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);

        ParkingLot lot = new ParkingLot(1);
        lot.addObserver(owner);

        lot.Park(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();
    }

    @Test
    public void ShouldNotfiyParkingFullAndAvailableNotification() throws Exception {
        Car car1 = new Car();

        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);

        ParkingLot lot = new ParkingLot(1);
        lot.addObserver(owner);

        lot.Park(car1);

       Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();

        lot.Unpark(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingAvailable();
    }

    @Test
    public void ShouldNotfiyParkingFullAndAvailableNotification1() throws Exception {
        Car car1 = new Car();

        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);

        ParkingLot lot = new ParkingLot(1);
        lot.addObserver(owner);

        lot.Park(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();

        lot.Unpark(car1);

        Mockito.verify(owner, Mockito.times(1)).NotifyParkingAvailable();
    }

    @Test
    public void Only1Notificationafterunparking2() throws Exception{

        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(2);
        lot.addObserver(owner);
        lot.Park(car1);
        lot.Park(car2);
        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();
        lot.Unpark(car1);
        lot.Unpark(car2);
        Mockito.verify(owner, Mockito.times(1)).NotifyParkingAvailable();
    }

    @Test
    public void AddOwnerAsSubscriberForParkingLot() throws  Exception{
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(2);
        lot.addObserver(owner);

        lot.Park(car1);
        lot.Park(car2);
        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();
        lot.Unpark(car1);
        lot.Unpark(car2);
        Mockito.verify(owner, Mockito.times(1)).NotifyParkingAvailable();
    }

    @Test
    public void AddCopAsSubscriberForParkingLot() throws  Exception{
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLotObserver cop = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(2);
        lot.addObserver(cop);

        lot.Park(car1);
        lot.Park(car2);
        Mockito.verify(cop, Mockito.times(1)).NotifyParkingFull();
        lot.Unpark(car1);
        lot.Unpark(car2);
        Mockito.verify(cop, Mockito.times(1)).NotifyParkingAvailable();
    }


    @Test
    public void AddingOwnerAndCopForParkingFullNotification() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
        ParkingLotObserver cop = Mockito.mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(2);
        lot.addObserver(owner);
        lot.addObserver(cop);

        lot.Park(car1);
        lot.Park(car2);
        Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();
        Mockito.verify(cop, Mockito.times(1)).NotifyParkingFull();
    }

    @Test
    public void OwnerAndCopGetsParkingFullAndPArkingAvailableNotification() throws Exception {

            Car car1 = new Car();
            Car car2 = new Car();
            ParkingLotObserver owner = Mockito.mock(ParkingLotObserver.class);
            ParkingLotObserver cop = Mockito.mock(ParkingLotObserver.class);
            ParkingLot lot = new ParkingLot(2);
            lot.addObserver(owner);
            lot.addObserver(cop);

            lot.Park(car1);
            lot.Park(car2);
            Mockito.verify(owner, Mockito.times(1)).NotifyParkingFull();
            Mockito.verify(cop, Mockito.times(1)).NotifyParkingFull();
            lot.Unpark(car1);
            lot.Unpark(car2);
            Mockito.verify(owner, Mockito.times(1)).NotifyParkingAvailable();
            Mockito.verify(cop, Mockito.times(1)).NotifyParkingAvailable();
        }
    }

