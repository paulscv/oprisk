package scv.paul.opriskback.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Сущность описывающая роли пользователей
 *   - администратор
 *   - владелец автомойки
 *   - менеджер
 *   - клиент
 *   -???
 */
@Entity
@Table(name = "roles")
public class Role extends AGenericOpriskEntity   {
    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = false, unique = true)
    private String descruiption;

    public Role() {
    }

    public Role(String name, String descruiption) {
        this.name = name;
        this.descruiption = descruiption;
    }

    public long getId() {
        return id;
    }

  //  public void setId(long id) {
  //      this.id = id;
  //  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescruiption() {
        return descruiption;
    }

    public void setDescruiption(String descruiption) {
        this.descruiption = descruiption;
    }
}
