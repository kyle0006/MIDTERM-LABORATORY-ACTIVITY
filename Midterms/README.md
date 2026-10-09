# Community Library Borrowing and Inventory System

A Java 17+ console application to manage books, magazines, library members, borrowing, and returns.

## Features
- Show and search library items.
- Register and list members.
- Borrow and return items with due dates.
- Prevent duplicate IDs, repeat borrowing, and more than three active loans per member.
- Add books and magazines.
- Different borrowing periods: books 14 days, magazines 7 days.

## Run (from this project folder)

```bash
javac -d out src/business/*.java src/data/*.java src/hosts/*.java
java -cp out hosts.Main
```



## Sample data
- Books: B001, B002, B003
- Magazines: M001, M002
- Members: U001, U002

## Limitations
- Data is held in memory only and resets whenever the program stops.
- No login system or fines.
- Uses the computer's current date when creating/closing loans.
- A single physical copy per item ID.
- All text input is read from the terminal.

## Project folders
- `src/business`: OOP classes and business rules.
- `src/data`: initial hardcoded sample data.
- `src/hosts`: main menu / console input.
- `docs`: UML diagram and testing plan.
