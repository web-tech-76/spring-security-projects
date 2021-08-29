package provider02.api.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "id")
    private Roles roles;

    @ManyToOne
    @JoinColumn(name = "id")
    private Modules modules;
}
