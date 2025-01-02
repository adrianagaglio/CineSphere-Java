package epicode.it.cinesphere.entity.user;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieRepo;
import epicode.it.cinesphere.entity.rate.Rate;
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
        return userRepo.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public String delete(Long id) throws IllegalAccessException {
        if (findUserById(id) == null)
            throw new IllegalAccessException("User not found");

        userRepo.deleteById(id);
        return "User deleted successfully";

    }

    public String delete(User user) {
        if (findUserById(user.getId()) == null)
            throw new IllegalArgumentException("User not found");
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

    public IGetUserResponse update(UpdateUserRequest request) throws Exception {
        User u = findById(request.getId());
        if(u==null) throw new Exception("User not found");
        if (request.getFirstName() != null) u.setFirstName(request.getFirstName());
        if (request.getLastName() != null) u.setLastName(request.getLastName());
        if (request.getUsername() != null) u.setUsername(request.getUsername());
        if (request.getEmail() != null) u.setEmail(request.getEmail());
        if(request.getActualPassword() != null && request.getNewPassword() != null)
            if(passwordEncoder.matches(request.getActualPassword(), u.getPassword())) u.setPassword(passwordEncoder.encode(request.getNewPassword()));
        save(u);
        return userRepo.findByIdGetUserResponse(request.getId());
    }

    public IGetUserResponse updateFav(UpdateFavRequest request) throws Exception {
        User u = findById(request.getUserId());
        if (u == null) throw new Exception("User not found");
        Movie m = movieRepo.findById(request.getMovieId()).orElse(null);
        if (m == null) throw new Exception("Movie not found");
        if (!u.getFavMovies().contains(m)) {
            u.getFavMovies().add(m);
        } else {
            u.getFavMovies().remove(m);
        }
        u=userRepo.save(u);
        return userRepo.findByIdGetUserResponse(u.getId());
    }

    public List<Movie> findFavMoviesByUserId(Long id) {
        return userRepo.findFavMoviesByUserId(id);
    }

}
