import java.util.HashSet;
import java.util.Set;

public class Attendant {

    private Set<ParkingLot> lotList = new HashSet<ParkingLot>();



    public void assignParkingLot(ParkingLot lot) {
        lotList.add(lot);
    }

    public void park(Car car) throws Exception {

        if (this.isParked(car)) {
            throw new AlreadyParkedException();
        }

        for (ParkingLot lot: lotList){
                if(!lot.IsLotFull()){
                    lot.Park(car);
                    return;
                }
        }


        throw new ParkingLotFullException();
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
