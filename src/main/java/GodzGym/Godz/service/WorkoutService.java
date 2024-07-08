package GodzGym.Godz.service;

import GodzGym.Godz.domain.User;
import GodzGym.Godz.domain.Workout;
import GodzGym.Godz.exception.ResourceNotFoundException;
import GodzGym.Godz.repos.WorkoutRepo;
import GodzGym.Godz.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepo workoutRepo;


    public Workout createWorkout(Workout workout) {
        return workoutRepo.save(workout);
    }
    public Iterable<Workout> getAllWorkouts() {
        return workoutRepo.findAll();
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error fetching workout with this id"));
    }
//    public void deleteWorkout(Long id) {
//        workoutRepo.deleteById(id);
//    }
    public ResponseEntity<?> deleteWorkout(Long workoutId){
        Workout workoutToBeDeleted = workoutRepo.findById(workoutId).orElse(null);
        if(workoutToBeDeleted == null){
            throw new ResourceNotFoundException("this workout does not exist");
        }else {
            workoutRepo.delete(workoutToBeDeleted);
            return ResponseHandler.generateResponse("Workout deleted");
        }
    }

    public ResponseEntity<Workout> updateWorkout(Long workoutId,Workout workoutDetails){
        return workoutRepo.findById(workoutId)
                .map(workout -> {
                    workout.setName(workoutDetails.getName());
                    workout.setSets(workoutDetails.getSets());
                    workout.setReps(workoutDetails.getReps());
                    workout.setDay(workoutDetails.getDay());
                    workout.setBodyPart(workoutDetails.getBodyPart());
                    Workout updateWorkout = workoutRepo.save(workout);
                    return ResponseEntity.ok().body(updateWorkout);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
