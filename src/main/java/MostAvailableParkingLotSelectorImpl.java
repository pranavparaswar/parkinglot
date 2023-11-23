import java.util.ArrayList;
import java.util.Comparator;

public class MostAvailableParkingLotSelectorImpl implements ParkingLotSelector{

    @Override
    public ParkingLot GetSuitableParkingLot(ArrayList<ParkingLot> lotList) throws ParkingLotFullException {
        ParkingLot parkingLot= lotList.stream().max(Comparator.comparing(ParkingLot::getAvailableParkingCount)).get();
        return parkingLot;
    }
}
