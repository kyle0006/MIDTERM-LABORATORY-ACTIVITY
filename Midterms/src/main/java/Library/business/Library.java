package Library.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final ArrayList<LibraryItem> items = new ArrayList<>();
    private final ArrayList<Member> members = new ArrayList<>();
    private final ArrayList<Loan> loans = new ArrayList<>();
    private static final int MAX_ACTIVE_LOANS_PER_MEMBER = 3;

    public void addItem(LibraryItem item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null.");
        if (findItem(item.getId()) != null) throw new IllegalArgumentException("Item ID already exists.");
        items.add(item);
    }

    public void registerMember(Member member) {
        if (member == null) throw new IllegalArgumentException("Member cannot be null.");
        if (findMember(member.getMemberId()) != null) throw new IllegalArgumentException("Member ID already exists.");
        members.add(member);
    }

    public LibraryItem findItem(String id) {
        for (LibraryItem item : items) if (item.getId().equalsIgnoreCase(id)) return item;
        return null;
    }

    public Member findMember(String id) {
        for (Member member : members) if (member.getMemberId().equalsIgnoreCase(id)) return member;
        return null;
    }

    public List<LibraryItem> getItems() { return List.copyOf(items); }
    public List<Member> getMembers() { return List.copyOf(members); }
    public List<Loan> getActiveLoans() {
        ArrayList<Loan> active = new ArrayList<>();
        for (Loan loan : loans) if (loan.isActive()) active.add(loan);
        return List.copyOf(active);
    }

    public Loan borrow(String itemId, String memberId) {
        LibraryItem item = findItem(itemId);
        Member member = findMember(memberId);
        if (item == null) throw new IllegalArgumentException("Item not found.");
        if (member == null) throw new IllegalArgumentException("Member not found.");
        if (!item.isAvailable()) throw new IllegalStateException("Item is currently borrowed.");
        int memberLoans = 0;
        for (Loan loan : loans) {
            if (loan.isActive() && loan.getMember().getMemberId().equalsIgnoreCase(memberId)) memberLoans++;
        }
        if (memberLoans >= MAX_ACTIVE_LOANS_PER_MEMBER) throw new IllegalStateException("Member has reached the 3-item borrowing limit.");
        item.borrowItem();
        Loan loan = new Loan(item, member, LocalDate.now());
        loans.add(loan);
        return loan;
    }

    public Loan returnItem(String itemId) {
        for (Loan loan : loans) {
            if (loan.isActive() && loan.getItem().getId().equalsIgnoreCase(itemId)) {
                loan.getItem().returnItem();
                loan.close(LocalDate.now());
                return loan;
            }
        }
        throw new IllegalArgumentException("No active loan found for that item.");
    }
}
