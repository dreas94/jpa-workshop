package se.lexicon.dreas94.jpaworkshop.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
@Entity
public class Author
{
    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author"
            ,joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")})
    private Set<Book> writtenBooks = new HashSet<>();

    public Author()
    {
    }

    public Author(String firstName, String lastName)
    {
        this();
        setFirstName(firstName);
        setLastName(lastName);
        setWrittenBooks(writtenBooks);
    }

    public Author(String firstName, String lastName, Set<Book> writtenBooks)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setWrittenBooks(writtenBooks);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Set<Book> getWrittenBooks()
    {
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks)
    {
        this.writtenBooks = writtenBooks;
    }

    public void addBook(Book book)
    {
        if(!writtenBooks.contains(book)) writtenBooks.add(book);
    }

    public void removeBook(Book book)
    {
        if(writtenBooks.contains(book)) writtenBooks.remove(book);
    }

    public void remove()
    {
        for (Book book : new HashSet<>(writtenBooks)) removeBook(book);
    }

    public boolean equalsForAuthor(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return getId() == author.getId() && getFirstName().equals(author.getFirstName()) && getLastName().equals(author.getLastName());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        boolean isEquals = getId() == author.getId() && getFirstName().equals(author.getFirstName()) && getLastName().equals(author.getLastName());

        Iterator<Book> it = getWrittenBooks().iterator();
        Iterator<Book> il = author.getWrittenBooks().iterator();

        isEquals = isEquals && getWrittenBooks().size() == author.getWrittenBooks().size();

        while(isEquals && it.hasNext() && il.hasNext())
            isEquals = it.next().equalsForAuthor(il.next());

        return isEquals;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getFirstName(), getLastName());
    }

    public String toStringForBook()
    {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", writtenBooks=");

        int count = 0;
        for(Book book : writtenBooks)
        {
            count++;
            if(count < writtenBooks.size())
            {
                str.append(book.toStringForAuthor()).append('\'').append(", ");
            }
            else
            {
                str.append(book.toStringForAuthor()).append('}');
            }
        }

        return str.toString();
    }
}
