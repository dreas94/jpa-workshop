package se.lexicon.dreas94.jpaworkshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Details
{
    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailsId;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    public Details()
    {

    }

    public Details(String email, String name, LocalDate birthday)
    {
        setEmail(email);
        setName(name);
        setBirthday(birthday);
    }

    public int getDetailsId()
    {
        return detailsId;
    }

    public void setDetailsId(int detailsId)
    {
        this.detailsId = detailsId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Details)) return false;
        Details details = (Details) o;
        return getDetailsId() == details.getDetailsId() && getEmail().equals(details.getEmail()) && getName().equals(details.getName()) && getBirthday().equals(details.getBirthday());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getDetailsId(), getEmail(), getName(), getBirthday());
    }

    @Override
    public String toString()
    {
        return "Details{" +
                "detailsId=" + getDetailsId() +
                ", email='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", birthday=" + getBirthday() +
                '}';
    }
}
