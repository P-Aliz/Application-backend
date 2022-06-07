package edu.bbte.allamv.paim1943.repository;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.model.Problem;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProblemRepository extends ArangoRepository<Problem, String> {
    @Query("FOR problem IN INBOUND\n" +
            " @lesson problemtolesson\n" +
            " RETURN problem")
    Iterable<Problem> getProblemsOfLesson(@Param("lesson") String username);

    @Query("INSERT {" +
            "_from: @problem, _to:@lesson" +
            "} into problemtolesson")
    void setProblemsOfLesson(@Param("lesson") String lesson, @Param("problem") String problem);

    @Query("FOR problem in problems\n" +
            "    FILTER problem.duel == true\n" +
            "    SORT RAND()\n" +
            "    LIMIT 1\n" +
            "    RETURN problem")
    Iterable<Problem> getRandomProblemDuel();

    @Query("FOR problem in problems\n" +
            "    SORT RAND()\n" +
            "    LIMIT 1\n" +
            "    return problem")
    Iterable<Problem> getRandomProblem();


}

