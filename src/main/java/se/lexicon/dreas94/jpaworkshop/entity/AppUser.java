package se.lexicon.dreas94.jpaworkshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser
{
    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private Details details;

    @OneToMany(mappedBy = "borrower")
    private List<BookLoan> loans;

    public AppUser()
    {
        setRegDate(LocalDate.now());
    }

    public AppUser(String username, String password)
    {
        this();
        setUsername(username);
        setPassword(password);
    }

    public AppUser(String username, String password, Details details)
    {
        this(username, password);
        setDetails(details);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public LocalDate getRegDate()
    {
        return regDate;
    }

    public void setRegDate(LocalDate regDate)
    {
        this.regDate = regDate;
    }

    public Details getDetails()
    {
        return details;
    }

    public void setDetails(Details details)
    {
        this.details = details;
    }

    public List<BookLoan> getLoans()
    {
        if (loans == null) loans = new ArrayList<>();
        return loans;
    }

    public void setLoans(List<BookLoan> loans)
    {
        this.loans = loans;
    }

    // convenience methods for manipulating with list
    public void createBookLoan(BookLoan bookLoan)
    {
        if (bookLoan == null) throw new IllegalArgumentException("book data is null");
        if (loans == null) loans = new ArrayList<>();

        loans.add(bookLoan);
        bookLoan.setBorrower(this);

    }

    public void removeBookLoan(BookLoan bookLoan)
    {
        if (bookLoan == null) throw new IllegalArgumentException("book data is null");
        if (loans == null) loans = new ArrayList<>();

        bookLoan.setBorrower(null);
        loans.remove(bookLoan);
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        AppUser appUser = (AppUser) o;
        return getId() == appUser.getId() && getUsername().equals(appUser.getUsername()) && getPassword().equals(appUser.getPassword()) && getRegDate().equals(appUser.getRegDate()) && getDetails().equals(appUser.getDetails());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getUsername(), getPassword(), getRegDate(), getDetails());
    }

    @Override
    public String toString()
    {
        return "AppUser{" +
                "appUserId=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", regDate=" + getRegDate() +
                ", details=" + getDetails() +
                '}';
    }
}
