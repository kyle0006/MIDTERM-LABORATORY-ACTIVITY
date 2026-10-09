package Library.business;

public interface Borrowable 
{
    boolean isAvailable();
    void borrowItem();
    void returnItem();
}
