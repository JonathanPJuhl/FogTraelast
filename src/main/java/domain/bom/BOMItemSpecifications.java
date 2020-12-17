package domain.bom;

public interface BOMItemSpecifications {

    public int length();

    public int quantity();

    public String description(String adminDescription);
}
