package roberts.inventory.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inventory_item", schema = "inventory", catalog = "inventory_tracker")
@IdClass(InventoryItemEntityPK.class)
public class InventoryItemEntity {
    private String username;
    private int quantity;
    private String itemName;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Id
    @Column(name = "item_name")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemEntity that = (InventoryItemEntity) o;
        return quantity == that.quantity && Objects.equals(username, that.username) && Objects.equals(itemName, that.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, quantity, itemName);
    }
}
