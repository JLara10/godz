package GodzGym.Godz.controller;

import GodzGym.Godz.domain.Workout;
import GodzGym.Godz.response.ResponseHandler;
import GodzGym.Godz.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/users/{userId}/workouts")
    public ResponseEntity<Object> createWorkout(@RequestBody Workout workout) {
        Workout newWorkout = workoutService.createWorkout(workout);
        return ResponseHandler.generateResponse("Workout created successfully!");
    }

    @GetMapping("/workouts")
    public Iterable<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();

    }

    @GetMapping("/workouts/{workoutId}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long workoutId) {
        return new ResponseEntity<>(workoutService.getWorkoutById(workoutId), HttpStatus.OK);

    }


    @DeleteMapping("/workouts/{workoutId}")
    public ResponseEntity<?> deleteWorkoutById(@PathVariable Long workoutId) {
        return workoutService.deleteWorkout(workoutId);
    }

    @PutMapping("/workouts/{workoutId}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long workoutId, @RequestBody Workout workout){
        return workoutService.updateWorkout(workoutId, workout);
    }
}
