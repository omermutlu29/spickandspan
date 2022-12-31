package com.spickandspan.user.user.domain.entity;

import com.spickandspan.user.product.domain.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keycloakId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<ProductEntity> products;

}
