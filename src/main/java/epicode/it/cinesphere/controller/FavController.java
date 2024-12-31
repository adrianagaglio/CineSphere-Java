package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.user.IGetUserResponse;
import epicode.it.cinesphere.entity.user.UpdateFavRequest;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FavController {

    @Autowired
    private UserService userService;

    @PutMapping(value="/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public IGetUserResponse updateFavourite(@PathParam("id") Long id, @RequestBody UpdateFavRequest request) throws Exception {
      return userService.updateFav(request);
    }


}
