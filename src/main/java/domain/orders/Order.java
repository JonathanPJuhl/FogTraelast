package domain.orders;

public class Order {

    private final String orderStatus;
    private final double length;
    private final double width;
    private final String customerPhone;
    private final String customerEmail;
    private final double price;
    private final int salesmanID;



    public Order(String orderStatus, double length, double width, String customerPhone, String customerEmail, double price, int salesmanID) {
        this.orderStatus = orderStatus;
        this.length = length;
        this.width = width;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.price = price;
        this.salesmanID = salesmanID;
    }

}
