package edu.bbte.allamv.paim1943.repository;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends ArangoRepository<User, String> {
    Collection<User> findByEmail(String email);

    @Query("INSERT {_to: @username1, _from: @username2} INTO friends \n\n\n INSERT {_to: @username2, _from: @username1} INTO friends")
    Iterable<Pet> addFriend(@Param("username1") String username1, @Param("username2") String username2);
}

