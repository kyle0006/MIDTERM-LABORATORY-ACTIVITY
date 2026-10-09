package Library.business;

public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String id, String title, int issueNumber) {
        super(id, title);
        if (issueNumber <= 0) throw new IllegalArgumentException("Issue number must be positive.");
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { return issueNumber; }
    public void setIssueNumber(int issueNumber) {
        if (issueNumber <= 0) throw new IllegalArgumentException("Issue number must be positive.");
        this.issueNumber = issueNumber;
    }

    @Override
    public String getItemType() { return "Magazine"; }

    @Override
    public int getLoanDays() { return 7; }

    @Override
    public String getDescription() { return super.getDescription() + " | Issue: " + issueNumber; }
}
