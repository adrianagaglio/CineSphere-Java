package epicode.it.cinesphere.entity.user;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.user.dto.IGetUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u.id as id, u.firstName as firstName, u.lastName as lastName, au.email as email, au.username as username, au.roles as role FROM User u JOIN AppUser au ON au.id = u.appUser.id WHERE au.username = :username")
    public IGetUserResponse findFirstByUsernameIgnoreCase(String username);

    @Query("SELECT u.id as id,  u.firstName as firstName, u.lastName as lastName, au.email as email, au.username as username, au.roles as role FROM User u JOIN AppUser au ON au.id = u.appUser.id WHERE au.email = :email")
    public IGetUserResponse findFirstByEmailIgnoreCase(String email);

    @Query("SELECT u FROM User u Join AppUser au ON au.id = u.appUser.id WHERE LOWER(au.email) = LOWER(:emailOrUsername) OR LOWER(au.username) = LOWER(:emailOrUsername)")
    public User findFirstByEmailOrUsername(@Param("emailOrUsername") String emailOrUsername);

    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) = LOWER(:firstName) AND LOWER(u.lastName) = LOWER(:lastName)")
    public List<User> findAllByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);


    @Query("SELECT u.id as id, u.firstName as firstName, u.lastName as lastName, au.username as username, au.email as email, au.roles as role FROM User u JOIN AppUser au ON au.id = u.appUser.id")
    public List<IGetUserResponse> findGetUsersResponseBy();

    @Query("SELECT u.id as id, u.firstName as firstName, u.lastName as lastName, au.username as username, au.email as email, au.roles as role FROM User u JOIN AppUser au ON u.id = au.id WHERE u.id = :id")
    public IGetUserResponse findByIdGetUserResponse(@Param("id") Long id);

    @Query("SELECT m FROM User u JOIN u.favMovies m WHERE u.id = :id")
    public List<Movie> findFavMoviesByUserId(@Param("id") Long id);


}
