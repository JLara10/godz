package GodzGym.Godz.repos;

import GodzGym.Godz.domain.Workout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepo extends CrudRepository<Workout, Long> {
    //@Query(value = "select a.* from workout w, user u Where a.id = ? And a.user_id = u.id",nativeQuery = true)
    List<Workout> findByUserId(Long userId);

}
