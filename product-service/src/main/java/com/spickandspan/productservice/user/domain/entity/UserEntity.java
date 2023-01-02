package com.spickandspan.productservice.user.domain.entity;

import com.spickandspan.productservice.product.domain.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<ProductEntity> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(keycloakId, that.keycloakId) && status == that.status && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keycloakId, status, products);
    }

}
