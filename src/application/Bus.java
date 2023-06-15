package application;

public class Bus {

    // Status no algorithm yet.

    private String busNumber;
    private String busType;
    private String routeFrom;
    private String routeTo;
    private String seatCapacity;
    private String date;
    private String departureTime;
    private String DriverInfo;

    public Bus(String busNumber, String DriverInfo, String routeFrom, String routeTo, String busType, String seatCapacity, String date, String departureTime) {
        this.busNumber = busNumber;
        this.DriverInfo = DriverInfo;
        this.busType = busType;
        this.routeFrom = routeFrom;
        this.routeTo = routeTo;
        this.seatCapacity = seatCapacity;
        this.date = date;
        this.departureTime = departureTime;
    }

	public String getDriverInfo() {
		return DriverInfo;
	}

	public void setDriverInfo(String driverInfo) {
		DriverInfo = driverInfo;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getRouteFrom() {
		return routeFrom;
	}

	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}

	public String getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(String seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
}
