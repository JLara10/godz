package GodzGym.Godz.service;

import GodzGym.Godz.domain.User;
import GodzGym.Godz.exception.ResourceNotFoundException;
import GodzGym.Godz.repos.UserRepo;
import GodzGym.Godz.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User createUser(User user){
        return userRepo.save(user);
    }

    public Iterable<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(Long id){
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error fetching user with this id"));
    }

//    public void deleteUser(Long id){
//        userRepo.deleteById(id);
//    }
    public ResponseEntity<?> deleteUser(Long userId) {
        User userToBeDeleted = userRepo.findById(userId).orElse(null);
        if (userToBeDeleted == null) {
            throw new ResourceNotFoundException("This user does not exist");
        } else {
            userRepo.delete(userToBeDeleted);
            return ResponseHandler.generateResponse("User has been deleted");
        }
    }

    public ResponseEntity<User> updateUser(Long userId, User userDetails){
        return userRepo.findById(userId)
                .map(user -> {
                   user.setFirstName(userDetails.getFirstName());
                   user.setLastName(userDetails.getLastName());
                   User updateUser = userRepo.save(user);
                   return ResponseEntity.ok().body(updateUser);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
