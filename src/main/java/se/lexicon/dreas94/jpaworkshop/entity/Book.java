package se.lexicon.dreas94.jpaworkshop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Book
{
    @Column(nullable = false)
    String isbn;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    int maxLoanDays;
    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Book()
    {
    }

    public Book(String isbn, String title, int maxLoanDays)
    {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getMaxLoanDays()
    {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays)
    {
        this.maxLoanDays = maxLoanDays;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId() == book.getId() && getMaxLoanDays() == book.getMaxLoanDays() && getIsbn().equals(book.getIsbn()) && getTitle().equals(book.getTitle());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getIsbn(), getTitle(), getMaxLoanDays());
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + getId() +
                ", isbn='" + getIsbn() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", maxLoanDays=" + getMaxLoanDays() +
                '}';
    }
}
