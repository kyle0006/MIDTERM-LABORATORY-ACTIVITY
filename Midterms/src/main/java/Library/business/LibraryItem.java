package Library.business;

public abstract class LibraryItem implements Borrowable {
    private final String id;
    private String title;
    private boolean available;

    public LibraryItem(String id, String title) {
        if (id == null || id.isBlank() || title == null || title.isBlank()) {
            throw new IllegalArgumentException("ID and title cannot be blank.");
        }
        this.id = id.trim();
        this.title = title.trim();
        this.available = true;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be blank.");
        this.title = title.trim();
    }

    @Override
    public boolean isAvailable() { return available; }

    @Override
    public void borrowItem() {
        if (!available) throw new IllegalStateException("Item is already borrowed.");
        available = false;
    }

    @Override
    public void returnItem() {
        if (available) throw new IllegalStateException("Item has not been borrowed.");
        available = true;
    }

    public abstract String getItemType();
    public abstract int getLoanDays();

    public String getDescription() {
        return String.format("%s | %s | %s | %s", id, title, getItemType(), available ? "Available" : "Borrowed");
    }
}
