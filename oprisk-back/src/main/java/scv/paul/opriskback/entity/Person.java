package scv.paul.opriskback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность описывающая физическое лицо, имеющее отнощение к систем, а именно?
 *  - клиент
 *  - внутренний пользователь
 */
@Entity(name = "employees")
public class Person extends AGenericOpriskEntity {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    protected Date birthDate;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "r_person_phones",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_phone"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_phone"}))
    //@JsonManagedReference(value = "person-phones")
    @Fetch(FetchMode.SELECT)
    protected Set<Phone> phones = new HashSet<>();

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String fullName) {
        this.setFullName(fullName);
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    @JsonIgnore
    public String getFullName() {
        return lastName+" "+firstName + " "+ middleName ;
    }

    public void setFullName(String fullName) {
        String[] arrName = fullName.split(" ",3);
        this.setLastName(arrName[0]);
        if (arrName[2].isEmpty()) {
            this.setFirstName(arrName[1]);
        }
        else{
            this.setMiddleName(arrName[2]);
            this.setFirstName(arrName[1]);
        }
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public void addPhone(String phone) {
        Phone aPhone = new Phone(phone);
        this.phones.add(aPhone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = trim(email);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person uperson = (Person) o;

        if (!getId().equals(uperson.getId())) return false;
        if (getFullName() != null ? !getFullName().equals(uperson.getFullName()) : uperson.getFullName() != null) return false;
        if (getBirthDate() != null ? !getBirthDate().equals(uperson.getBirthDate()) : uperson.getBirthDate() != null)
            return false;
        return getPhones() != null ? getPhones().equals(uperson.getPhones()) : uperson.getPhones() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + (getPhones() != null ? getPhones().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Phone phone : phones) {
            sb.append(phone.getPhoneNumber());
            sb.append("\n");
        }
        return "Person{" +
                "id=" + id +
                ", full name='" + getFullName() + '\'' +
                ", birthDate=" + (birthDate == null ? "": birthDate.toString())+
                ", phones=" + sb.toString() +
                '}';
    }
}

