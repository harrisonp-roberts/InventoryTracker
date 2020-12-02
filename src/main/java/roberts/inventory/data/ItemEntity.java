package roberts.inventory.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "inventory", catalog = "inventory_tracker")
public class ItemEntity {
    private String name;


    @Id
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
