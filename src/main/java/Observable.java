public interface Observable {
    public void addObserver(ParkingLotObserver observer);
    public void notfiyAllParkingFull();

    public void notifyAllParkingAvailable();
}
