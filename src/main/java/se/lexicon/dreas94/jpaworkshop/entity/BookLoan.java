package se.lexicon.dreas94.jpaworkshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BookLoan
{
    @Column(nullable = false)
    LocalDate loanDate;
    @Column(nullable = false)
    LocalDate dueDate;
    @Column(nullable = false)
    boolean returned;
    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private AppUser borrower;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public BookLoan()
    {
        setLoanDate(LocalDate.now());
    }

    public BookLoan(boolean returned, AppUser borrower, Book book)
    {
        this();
        setBorrower(borrower);
        setBook(book);
        this.createDueDate();
        setReturned(returned);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDate getLoanDate()
    {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate)
    {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate)
    {
        this.dueDate = dueDate;
    }

    private void createDueDate()
    {
        this.dueDate = getLoanDate().plusDays(getBook().getMaxLoanDays());
    }

    public boolean isReturned()
    {
        return returned;
    }

    public void setReturned(boolean returned)
    {
        this.returned = returned;
    }

    public AppUser getBorrower()
    {
        return borrower;
    }

    public void setBorrower(AppUser borrower)
    {
        this.borrower = borrower;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof BookLoan)) return false;
        BookLoan bookLoan = (BookLoan) o;
        return getId() == bookLoan.getId() && isReturned() == bookLoan.isReturned() && getLoanDate().equals(bookLoan.getLoanDate()) && getDueDate().equals(bookLoan.getDueDate()) && getBorrower().equals(bookLoan.getBorrower()) && getBook().equals(bookLoan.getBook());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getLoanDate(), getDueDate(), isReturned(), getBorrower(), getBook());
    }

    @Override
    public String toString()
    {
        return "BookLoan{" +
                "id=" + getId() +
                ", loanDate=" + getLoanDate() +
                ", dueDate=" + getDueDate() +
                ", returned=" + isReturned() +
                ", appUser=" + getBorrower() +
                ", books=" + getBook() +
                '}';
    }


}
