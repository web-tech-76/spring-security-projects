package provider02.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import provider02.api.resources.Otp;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

    @Query(value = "select o from Otp o where o.userid=?1 and o.expiry > ?2 ")
    Optional<Otp> findOtpByUseridAndExpiry(int id, Timestamp timestamp);
}
