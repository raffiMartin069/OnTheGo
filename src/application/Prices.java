package application;

public class Prices {
    private int id;
    private String fromLocation;
    private String toLocation;
    private String busType;
    private int price;

    /*public Prices(String fromLocation, String toLocation, String busType, int price) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.busType = busType;
        this.price = price;
    }*/
    
    public Prices(int id, String fromLocation, String toLocation, String busType, int price) {
        this.id = id;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.busType = busType;
        this.price = price;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
