package my.project.kargotakipsistemi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 9500172
 * @project springsec
 * @since Thursday - October - 2023 - 16:50:34
 */

@Entity
@Getter
@Setter
@Table(name = "authorities")
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT", name = "id")
    private int id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users = new HashSet<>();

    public Authority(String name) {
        this.name = name;
    }
}