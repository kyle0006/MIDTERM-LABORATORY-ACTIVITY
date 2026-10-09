package Library.business;

public class Member {
    private final String memberId;
    private String name;

    public Member(String memberId, String name) {
        if (memberId == null || memberId.isBlank() || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Member ID and name cannot be blank.");
        }
        this.memberId = memberId.trim();
        this.name = name.trim();
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank.");
        this.name = name.trim();
    }
}
