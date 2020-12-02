package roberts.inventory.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "inventory", catalog = "inventory_tracker")
public class UserEntity {
    private String username;
    private String type;
    private String password;
    private String salt;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(username, that.username) && Objects.equals(type, that.type) && Objects.equals(password, that.password) && Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, type, password, salt);
    }
}
