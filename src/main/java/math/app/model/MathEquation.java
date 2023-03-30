package math.app.model;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "math_equation")
public class MathEquation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String equation;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "math_equations_roots",
            joinColumns = @JoinColumn(name = "math_equation_id"),
            inverseJoinColumns = @JoinColumn(name = "root_id"))
    @Cascade({CascadeType.MERGE, CascadeType.PERSIST})
    private List<Root> root;

    public MathEquation() {
    }

    public MathEquation(String equation) {
        this.equation = equation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public List<Root> getRoot() {
        return root;
    }

    public void setRoot(List<Root> root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "MathEquation{"
                + "id=" + id
                + ", equation='" + equation + '\''
                + ", root=" + root
                + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MathEquation mathEquation = (MathEquation) object;
        return Objects.equals(id, mathEquation.id)
                && Objects.equals(equation, mathEquation.equation)
                && Objects.equals(root, mathEquation.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equation, root);
    }
}
