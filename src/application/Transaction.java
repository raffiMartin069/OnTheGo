package application;

public class Transaction {
    private String transId;
    private String cxId;
    private String fromTo;
    private String goTo;
    private String dateToDay;
    private String time;
    private String busType;
    private String fareCost;
    private String bookCost;
    private String total;
    private String pymntNo;
    private String refferNum;
    private String stat;

	
	public Transaction(String transId, String cxId, String fromTo, String goTo, String dateToDay, String time,
			String busType, String fareCost, String bookCost, String total, String pymntNo, String refferNum, String stat) {
		super();
		this.transId = transId;
		this.cxId = cxId;
		this.fromTo = fromTo;
		this.goTo = goTo;
		this.dateToDay = dateToDay;
		this.time = time;
		this.busType = busType;
		this.fareCost = fareCost;
		this.bookCost = bookCost;
		this.total = total;
		this.pymntNo = pymntNo;
		this.refferNum = refferNum;
		this.stat = stat;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getCxId() {
		return cxId;
	}
	public void setCxId(String cxId) {
		this.cxId = cxId;
	}
	public String getFromTo() {
		return fromTo;
	}
	public void setFromTo(String fromTo) {
		this.fromTo = fromTo;
	}
	public String getGoTo() {
		return goTo;
	}
	public void setGoTo(String goTo) {
		this.goTo = goTo;
	}
	public String getDateToDay() {
		return dateToDay;
	}
	public void setDateToDay(String dateToDay) {
		this.dateToDay = dateToDay;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getFareCost() {
		return fareCost;
	}
	public void setFareCost(String fareCost) {
		this.fareCost = fareCost;
	}
	public String getBookCost() {
		return bookCost;
	}
	public void setBookCost(String bookCost) {
		this.bookCost = bookCost;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPymntNo() {
		return pymntNo;
	}
	public void setPymntNo(String pymntNo) {
		this.pymntNo = pymntNo;
	}
	public String getRefferNum() {
		return refferNum;
	}
	public void setRefferNum(String refferNum) {
		this.refferNum = refferNum;
	}

    
}
