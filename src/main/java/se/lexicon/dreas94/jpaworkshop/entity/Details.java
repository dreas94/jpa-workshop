package se.lexicon.dreas94.jpaworkshop.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Details
{
    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
        return getId() == details.getId() && getEmail().equals(details.getEmail()) && getName().equals(details.getName()) && getBirthday().equals(details.getBirthday());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getEmail(), getName(), getBirthday());
    }

    @Override
    public String toString()
    {
        return "Details{" +
                "detailsId=" + getId() +
                ", email='" + getEmail() + '\'' +
                ", name='" + getName() + '\'' +
                ", birthday=" + getBirthday() +
                '}';
    }
}
