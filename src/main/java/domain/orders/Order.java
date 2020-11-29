package domain.orders;

public class Order {

    private final int orderID;
    private final String orderStatus;
    private final double length;
    private final double width;
    private final String customerPhone;
    private final String customerEmail;
    private final double price;
    private final int salesmanID;



    public Order(int orderID, String orderStatus, double length, double width, String customerPhone, String customerEmail, double price, int salesmanID) {
        this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.length = length;
        this.width = width;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.price = price;
        this.salesmanID = salesmanID;
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
}
