package se.lexicon.dreas94.jpaworkshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class AppUser
{
    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;

    @Column(nullable = false, unique=true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "detailsId")
    private Details details;

    public AppUser()
    {
        setRegDate(LocalDate.now());
    }

    public AppUser(String username, String password, Details details)
    {
        this();
        setUsername(username);
        setPassword(password);
        setDetails(details);
    }

    public AppUser(String username, String password)
    {
        this();
        setUsername(username);
        setPassword(password);
    }

    public int getAppUserId()
    {
        return appUserId;
    }

    public void setAppUserId(int appUserId)
    {
        this.appUserId = appUserId;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        AppUser appUser = (AppUser) o;
        return getAppUserId() == appUser.getAppUserId() && getUsername().equals(appUser.getUsername()) && getPassword().equals(appUser.getPassword()) && getRegDate().equals(appUser.getRegDate()) && getDetails().equals(appUser.getDetails());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getAppUserId(), getUsername(), getPassword(), getRegDate(), getDetails());
    }

    @Override
    public String toString()
    {
        return "AppUser{" +
                "appUserId=" + getAppUserId() +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", regDate=" + getRegDate() +
                ", details=" + getDetails() +
                '}';
    }
}
