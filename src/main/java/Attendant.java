public class Attendant {
    private ParkingLot parkingLot;

    public void assignParkingLot(ParkingLot lot) {
        parkingLot = lot;
    }

    public void park(Car car) throws Exception {
        this.parkingLot.Park(car);
    }
}
