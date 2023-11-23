import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private final int capacity;

    ArrayList<Car> carlist = new ArrayList<>();;
    private Set<ParkingLotObservable> observerList = new HashSet<ParkingLotObservable>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }


    public void Park(Car car) throws Exception {

        if (this.capacity == this.carlist.size()){
            throw new ParkingLotFullException();
        }
        System.out.println(this.carlist.size());

        if (this.carlist.contains(car)){
            throw new AlreadyParkedException();
        }
        carlist.add(car);

        if (this.capacity == this.carlist.size()) {
            for (ParkingLotObservable item: this.observerList
            ) {
                item.NotifyParkingFull();
            }
        }
    }

    public void Unpark(Car car) throws Exception{

        if(!this.carlist.contains(car)){
            throw  new InvalidCarException();
        }
        carlist.remove(car);

        if (this.carlist.size() +1 == this.capacity) {
            for (ParkingLotObservable item: this.observerList
                 ) {
                item.NotifyParkingAvailable();
            }
        }


    }

    public boolean IsParked(Car car) {
        return this.carlist.contains(car);
    }

    public void addObserver(ParkingLotObservable owner) {
        observerList.add(owner);
    }
}
