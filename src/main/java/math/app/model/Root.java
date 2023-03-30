package math.app.model;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roots")
public class Root {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double root;

    public Root() {
    }

    public Root(Double root) {
        this.root = root;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRoot() {
        return root;
    }

    public void setRoot(Double root) {
        this.root = root;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Root root1 = (Root) object;
        return Objects.equals(id, root1.id)
                && Objects.equals(root, root1.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, root);
    }

    @Override
    public String toString() {
        return "Root{"
                + "id=" + id
                + ", root=" + root
                + '}';
    }
}
