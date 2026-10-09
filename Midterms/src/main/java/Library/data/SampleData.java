
package Library.data;

import Library.business.Book;
import Library.business.Library;
import Library.business.Magazine;
import Library.business.Member;

public final class SampleData 
{
    private SampleData() {}

    public static void load(Library library) {
        library.addItem(new Book("B001", "Introduction to Java", "A. Santos"));
        library.addItem(new Book("B002", "Data Structures Basics", "M. Reyes"));
        library.addItem(new Book("B003", "Learning Algorithms", "J. Cruz"));
        library.addItem(new Magazine("M001", "Science Monthly", 12));
        library.addItem(new Magazine("M002", "Technology Today", 5));
        library.registerMember(new Member("U001", "Alex Dela Cruz"));
        library.registerMember(new Member("U002", "Sam Rivera"));
    }
}
