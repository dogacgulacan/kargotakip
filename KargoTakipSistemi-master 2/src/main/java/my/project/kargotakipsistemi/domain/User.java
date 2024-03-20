package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(name = "users")
@Entity
@NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    protected String name;

    @Basic
    @Column(name = "password", nullable = true, length = 100)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            foreignKey = @ForeignKey(name = "FK_user_authorities_users"),
            inverseForeignKey = @ForeignKey(name = "FK_user_authorities_authorities"),
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", table = "users", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_user_authorities_users"), columnDefinition = "INT"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", table = "authorities", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_user_authorities_authorities"), columnDefinition = "INT")
    )
    private Set<Authority> authorities = new HashSet<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
