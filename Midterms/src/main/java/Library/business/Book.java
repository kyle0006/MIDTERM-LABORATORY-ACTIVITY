package Library.business;

public class Book extends LibraryItem {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title);
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Author cannot be blank.");
        this.author = author.trim();
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Author cannot be blank.");
        this.author = author.trim();
    }

    @Override
    public String getItemType() { return "Book"; }

    @Override
    public int getLoanDays() { return 14; }

    @Override
    public String getDescription() { return super.getDescription() + " | Author: " + author; }
}
