import java.util.ArrayList;

public class Attendant {

    private ParkingLotSelector parkingLotSelector;

    private ArrayList<ParkingLot> lotList = new ArrayList<ParkingLot>();

    public Attendant(ParkingLotSelector parkingLotSelector) {
        this.parkingLotSelector = parkingLotSelector;
    }


    public void assignParkingLot(ParkingLot lot) {
        lotList.add(lot);
    }

    public void park(Car car) throws Exception {

        if (this.isParked(car)) {
            throw new AlreadyParkedException();
        }

      ParkingLot parkingLot = this.parkingLotSelector.GetSuitableParkingLot(this.lotList);

        parkingLot.Park(car);
    }

    public boolean isParked(Car car) {
        return this.lotList
                .stream()
                .anyMatch(parkingLot -> parkingLot.IsParked(car));
    }

    public void unpark(Car car) throws Exception{

        if (!this.isParked(car)) {
            throw new InvalidCarException();
        }

        for (ParkingLot lot: lotList){
            if(lot.IsParked(car)){
                lot.Unpark(car);
                return;
            }
        }
    }


}
