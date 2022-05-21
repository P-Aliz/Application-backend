package edu.bbte.allamv.paim1943.repository;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import edu.bbte.allamv.paim1943.model.Pet;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PetRepository extends ArangoRepository<Pet, String> {
    @Query("FOR pet IN OUTBOUND\n" +
            " @username owing\n" +
            " RETURN pet")
    Iterable<Pet> getPetsUser(@Param("username") String username);
}

