import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Attendant {

    private Set<ParkingLot> lotList = new HashSet<ParkingLot>();



    public void assignParkingLot(ParkingLot lot) {
        lotList.add(lot);
    }

    public void park(Car car) throws Exception {

        for (ParkingLot lot: lotList){
            if(!lot.IsLotFull()) {
                lot.Park(car);
                return;
            }
        }
        throw new ParkingLotFullException();
    }
}
