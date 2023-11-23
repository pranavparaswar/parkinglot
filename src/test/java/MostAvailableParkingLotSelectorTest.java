import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class MostAvailableParkingLotSelectorTest {

    @Test
    void ShouldNotThrowExceptionReturnTheMostAvailableCars() throws Exception {
        ParkingLot mockParkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot mockParkingLot2 = Mockito.mock(ParkingLot.class);

        ArrayList<ParkingLot> parkingLotList=new ArrayList();
        parkingLotList.add(mockParkingLot1);
        parkingLotList.add(mockParkingLot2);

        Mockito.when(mockParkingLot1.getAvailableParkingCount()).thenReturn(5);
        Mockito.when(mockParkingLot2.getAvailableParkingCount()).thenReturn(4);

        MostAvailableParkingLotSelectorImpl selector = new MostAvailableParkingLotSelectorImpl();
        Assertions.assertDoesNotThrow(()-> selector.GetSuitableParkingLot(parkingLotList));
    }

    @Test
    void ShouldReturnTheMostAvailableCars() throws Exception {

        ParkingLot mockParkingLot1 = Mockito.mock(ParkingLot.class);
        ParkingLot mockParkingLot2 = Mockito.mock(ParkingLot.class);
        ArrayList<ParkingLot> parkingLotList=new ArrayList();
        parkingLotList.add(mockParkingLot1);
        parkingLotList.add(mockParkingLot2);
        Mockito.when(mockParkingLot1.getAvailableParkingCount()).thenReturn(5);
        Mockito.when(mockParkingLot2.getAvailableParkingCount()).thenReturn(4);
        MostAvailableParkingLotSelectorImpl selector = new MostAvailableParkingLotSelectorImpl();
        ParkingLot result=selector.GetSuitableParkingLot(parkingLotList);
        Assertions.assertEquals(mockParkingLot1,result);
    }

}
