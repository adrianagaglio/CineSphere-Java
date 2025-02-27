package epicode.it.cinesphere.entity.user;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieRepo;
import epicode.it.cinesphere.entity.user.dto.IGetUserResponse;
import epicode.it.cinesphere.entity.user.dto.UpdateFavRequest;
import epicode.it.cinesphere.entity.user.dto.UpdateUserRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        return userRepo.save(user);
    }

    public List<User> save(List<User> users) {
        return userRepo.saveAll(users);
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public String delete(Long id) {
        userRepo.deleteById(id);
        return "User deleted successfully";

    }

    public String delete(User user) {

        userRepo.delete(user);
        return "User deleted successfully";
    }

    public User findByUsername(String username) {
        return userRepo.findFirstByUsernameIgnoreCase(username);
    }

    public User findByEmail(String email) {
        return userRepo.findFirstByEmailIgnoreCase(email);
    }

    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepo.findAllByFirstNameAndLastName(firstName, lastName);
    }

    public int count() {
        return (int) userRepo.count();
    }

    public void updateFav(User user, Movie movie) {
        User managedUser = findById(user.getId());
        if (managedUser != null) {
            managedUser.getFavMovies().add(movie);
        }
        userRepo.save(managedUser);
    }

    public void deleteFav(User user, Movie movie) {
        User managedUser = findById(user.getId());
        if (managedUser != null) {
            managedUser.getFavMovies().remove(movie);
        }
        userRepo.save(managedUser);
    }

    public List<IGetUserResponse> getAllUsers() {
        return userRepo.findGetUsersResponseBy();
    }

    public IGetUserResponse findUserById(Long id) {
        return userRepo.findByIdGetUserResponse(id);
    }



    public IGetUserResponse updateFav(UpdateFavRequest request) {
        User u = findById(request.getUserId());
        if (u == null) throw new EntityNotFoundException("User not found");
        Movie m = movieRepo.findById(request.getMovieId()).orElse(null);
        if (m == null) throw new EntityNotFoundException("Movie not found");
        if (!u.getFavMovies().contains(m)) {
            u.getFavMovies().add(m);
        } else {
            u.getFavMovies().remove(m);
        }
        u = userRepo.save(u);
        return userRepo.findByIdGetUserResponse(u.getId());
    }

    public List<Movie> findFavMoviesByUserId(Long id) {
        return userRepo.findFavMoviesByUserId(id);
    }

}
