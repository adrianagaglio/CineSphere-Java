package epicode.it.cinesphere.auth.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    @Query("SELECT u FROM AppUser u WHERE u.email = :identifier OR u.username = :identifier")
    Optional<AppUser> findByEmailOrUsername(@Param("identifier") String identifier);

    boolean existsByEmailOrUsername(String email, String username);

}
