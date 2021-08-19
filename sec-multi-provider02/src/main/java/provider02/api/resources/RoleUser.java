package provider02.api.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public @Data class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private int roleid;

    private int userid;

}
