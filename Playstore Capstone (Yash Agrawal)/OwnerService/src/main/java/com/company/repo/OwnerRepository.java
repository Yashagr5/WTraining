package com.company.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.company.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByOwnerName(String ownerName);

    Optional<Owner> findByEmail(String email);
    Optional<Owner> findByPhone(String phone);
    Optional<Owner> findByOwnerName(String ownerName);

    @Query("SELECT o FROM Owner o WHERE o.ownerName = :usernameOrEmail OR o.email = :usernameOrEmail")
    Optional<Owner> findByOwnerNameOrEmail(@Param("usernameOrEmail") String usernameOrEmail1, @Param("usernameOrEmail") String usernameOrEmail2);

    default Optional<Owner> findByEmailOrPhone(String identifier) {
        Optional<Owner> owner = findByEmail(identifier);
        return owner.isPresent() ? owner : findByPhone(identifier);
    }
}
