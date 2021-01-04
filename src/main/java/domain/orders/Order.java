package domain.orders;

public class Order {

    private final int orderID;
    private final String orderStatus;
    private final int length;
    private final int width;
    private final String customerPhone;
    private final String customerEmail;
    private final double price;
    private final int salesmanID;
    private final String roofType;
    private final int shedOrNo;
    private final int wallsOrNo;
    private final int shedLength;
    private final int shedWidth;
    private final String svg;

    public Order(int orderID, String orderStatus, int length, int width, String customerPhone, String customerEmail, double price, int salesmanID, String roofType, int shedOrNo, int wallsOrNo, int shedLength, int shedWidth, String svg) {
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.length = length;
        this.width = width;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.price = price;
        this.salesmanID = salesmanID;
        this.roofType = roofType;
        this.shedOrNo = shedOrNo;
        this.wallsOrNo = wallsOrNo;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.svg = svg;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public double getPrice() {
        return price;
    }

    public int getSalesmanID() {
        return salesmanID;
    }

    public String getRoofType() {
        return roofType;
    }

    public int getShedOrNo() {
        return shedOrNo;
    }

    public int getWallsOrNo() {
        return wallsOrNo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderStatus='" + orderStatus + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", price=" + price +
                ", salesmanID=" + salesmanID +
                '}';
    }
}
