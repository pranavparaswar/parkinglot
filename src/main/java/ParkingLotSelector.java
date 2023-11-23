import java.util.ArrayList;

public interface ParkingLotSelector {

    ParkingLot GetSuitableParkingLot(ArrayList<ParkingLot> lotList)  throws ParkingLotFullException ;
}
