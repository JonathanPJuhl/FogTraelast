package domain.orders;

public class Order {

    private Status orderStatus = Status.New;

    enum Status {
        New,
        Processing,
        Done
    }
}
