import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ParkingLot implements Observable{

    private final int capacity;

    ArrayList<Car> carlist = new ArrayList<>();;
    private Set<ParkingLotObserver> observerList = new HashSet<ParkingLotObserver>();

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
           this.notifyAllParkingAvailable();
        }
    }

    @Override
    public void notfiyAllParkingFull() {
        for (ParkingLotObserver item: this.observerList
        ) {
            item.NotifyParkingFull();
        }
    }

    @Override
    public void notifyAllParkingAvailable() {
        for (ParkingLotObserver item: this.observerList
        ) {
            item.NotifyParkingAvailable();
        }
    }

    public void Unpark(Car car) throws Exception{

        if(!this.carlist.contains(car)){
            throw  new InvalidCarException();
        }
        carlist.remove(car);

        if (this.carlist.size() +1 == this.capacity) {
            this.notifyAllParkingAvailable();
        }


    }

    public boolean IsParked(Car car) {
        return this.carlist.contains(car);
    }

    public void addObserver(ParkingLotObserver observer) {
        observerList.add(observer);
    }

    public boolean IsLotFull() {
        return this.carlist.size() == this.capacity;
    }

    public int getAvailableParkingCount() {
        return this.carlist.size();
    }

    public int getCapacity() {
        return this.capacity;
    }
}
