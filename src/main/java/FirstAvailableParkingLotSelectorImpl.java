import java.util.ArrayList;

public class FirstAvailableParkingLotSelectorImpl implements ParkingLotSelector {
    @Override
    public ParkingLot GetSuitableParkingLot(ArrayList<ParkingLot> lotList) throws ParkingLotFullException {
        for (ParkingLot lot: lotList){
            if(!lot.IsLotFull()){
                return lot;
            }
        }
        throw new ParkingLotFullException();
    }
}
