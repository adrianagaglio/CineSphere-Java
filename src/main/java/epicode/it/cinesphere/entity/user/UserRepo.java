package epicode.it.cinesphere.entity.user;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.user.dto.IGetUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    public User findFirstByUsernameIgnoreCase(String username);

    public User findFirstByEmailIgnoreCase(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:emailOrUsername) OR LOWER(u.username) = LOWER(:emailOrUsername)")
    public User findFirstByEmailOrUsername(@Param("emailOrUsername") String emailOrUsername);

    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) = LOWER(:firstName) AND LOWER(u.lastName) = LOWER(:lastName)")
    public List<User> findAllByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);

    public List<IGetUserResponse> findGetUsersResponseBy();

    @Query("SELECT u, au.role FROM User u JOIN AppUser au ON u.id = au.id WHERE u.id = :id")
    public IGetUserResponse findByIdGetUserResponse(@Param("id") Long id);

    @Query("SELECT m FROM User u JOIN u.favMovies m WHERE u.id = :id")
    public List<Movie> findFavMoviesByUserId(@Param("id") Long id);



}
