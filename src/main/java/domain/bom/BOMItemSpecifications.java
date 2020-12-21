package domain.bom;

public interface BOMItemSpecifications{

    public abstract int length();

    public abstract int quantity();

    public abstract String description(String adminDescription);
}
