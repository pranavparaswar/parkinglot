import java.util.ArrayList;

public class ParkingLot {

    private final int capacity;

    ArrayList<Car> carlist = new ArrayList<>();;
    private Owner owner;

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.owner = owner;
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
            this.owner.NotifyParkingFull();
        }
    }

    public void Unpark(Car car) throws Exception{

        if(!this.carlist.contains(car)){
            throw  new InvalidCarException();
        }
        carlist.remove(car);

        if (this.carlist.size() +1 == this.capacity) {
            owner.NotifyParkingAvailable();
        }


    }

    public boolean IsParked(Car car) {
        return this.carlist.contains(car);
    }
}
