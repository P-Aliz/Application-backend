package edu.bbte.allamv.paim1943.repository;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import edu.bbte.allamv.paim1943.dto.UserOutDto;
import edu.bbte.allamv.paim1943.model.Friend;
import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends ArangoRepository<User, String> {
    Collection<User> findByEmail(String email);

    @Query("INSERT {_to: @username1, _from: @username2} INTO friends")
    Iterable<Pet> addFriend(@Param("username1") String username1, @Param("username2") String username2);

    @Query("for user in users\n" +
            "    sort user.level desc, user.all_points desc\n" +
            "    limit 5\n" +
            "    return user")
    Iterable<UserOutDto> getTop();

    @Query("FOR user IN  users\n" +
            "  FILTER user._id == @username\n" +
            "  UPDATE user WITH {level: user.level+1} in users OPTIONS { ignoreErrors: true }")
    void levelUp(@Param("username") String username);

    @Query("FOR user IN  users\n" +
            "  FILTER user._id == @username\n" +
            "  UPDATE user WITH {\n" +
            "    current_points: user.current_points+@point,\n" +
            "    all_points: user.all_points+@point,\n" +
            "    } in users")
    void addPoint(@Param("username") String username, @Param("point") Integer point);

    @Query("FOR user IN  users\n" +
            "  FILTER user._id == @username\n" +
            "  UPDATE user WITH {\n" +
            "    current_points: user.current_points-@point,\n" +
            "    } in users" )
    UserOutDto removePoint(@Param("username") String username, @Param("point") Integer point);

    @Query("FOR user, friends IN ANY\n" +
            " @username friends\n" +
            " filter friends.happened!= false\n" +
            " SORT RAND()\n" +
            " RETURN DISTINCT user")
    Iterable<UserOutDto> getFriends(@Param("username") String username);

    @Query("FOR user, friends IN INBOUND\n" +
            " @username friends\n" +
            " filter friends.happened== false\n" +
            " SORT RAND()\n" +
            " RETURN DISTINCT friends")
    Iterable<Friend> getFriendRequests(@Param("username") String username);

    @Query("LET doc = DOCUMENT(@username)\n" +
            "UPDATE doc WITH {\n" +
            "  resolvedproblems: APPEND(doc.resolvedproblems, [@problem], true)\n" +
            "} IN users")
    void resolveProblem(@Param("username") String username, @Param("problem") String problem);

    @Query("FOR user IN 2..6 ANY\n" +
            "@username friends\n" +
            "FILTER user._id NOT IN (\n" +
            "FOR user2 IN ANY\n" +
            "@username friends\n" +
            "return user2._id\n" +
            ")\n" +
            "RETURN DISTINCT user")
    Iterable<UserOutDto> getRecommendedFriends(@Param("username") String username);
}

