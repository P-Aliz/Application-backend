package edu.bbte.allamv.paim1943.repository;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import edu.bbte.allamv.paim1943.model.Duel;
import edu.bbte.allamv.paim1943.model.Pet;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DuelRepository extends ArangoRepository<Duel, String> {
    @Query("FOR user,duel IN INBOUND\n" +
            " @username duels\n" +
            " FILTER duel.date >= DATE_NOW()-120000 && duel.happened==false\n" +
            " SORT duel.date DESC\n" +
            " LIMIT 1\n" +
            " RETURN \n" +
            "    duel\n" )
    Iterable<Duel> getDuelInvitationLast(@Param("username") String username);


    @Query("FOR user,duel IN OUTBOUND\n" +
            " @username duels\n" +
            " FILTER duel.date >= DATE_NOW()-120000 && duel.accepted==true\n" +
            " SORT duel.date DESC\n" +
            " LIMIT 1\n" +
            " RETURN \n" +
            "    duel\n" )
    Iterable<Duel> getDuelInvitatedLast(@Param("username") String username);


}
