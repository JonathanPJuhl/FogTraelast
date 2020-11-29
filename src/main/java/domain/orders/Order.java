package domain.orders;

import domain.construction.Construction;

public class Order {

    private Status orderStatus = Status.New;



    //private Construction construction = new Construction()

    enum Status {
        New,
        Processing,
        Done
    }
}
