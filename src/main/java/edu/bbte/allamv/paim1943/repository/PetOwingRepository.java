package edu.bbte.allamv.paim1943.repository;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.model.PetOwing;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PetOwingRepository extends ArangoRepository<PetOwing, String> {
    @Query("FOR pet, owing IN OUTBOUND\n" +
            " @username owing\n" +
            " RETURN {\n" +
            "    pet_id: pet._id,\n" +
            "    img: pet.img,\n" +
            "    minim_point: pet.minim_point,\n" +
            "    feed_point: pet.feed_point,\n" +
            "    pet_point: pet.pet_point,\n" +
            "    name: owing.name,\n" +
            "    food_percentage: owing.food_percentage,\n" +
            "    happynes_percenytage: owing.happynes_percenytage,\n" +
            "    edge_id: owing._id\n"+
            " }")
    Iterable<PetOwing> getPetsUser(@Param("username") String username);

    @Query("FOR o IN owing\n" +
            "  FILTER o._id == @edge_id\n" +
            "  UPDATE o WITH { food_percentage: MIN([o.food_percentage + 10, 100]) } IN owing")
    void feedPet(@Param("edge_id") String edge_id);

    @Query("FOR o IN owing\n" +
            "  FILTER o._id == @edge_id\n" +
            "  UPDATE o WITH { happynes_percenytage: MIN([o.happynes_percenytage + 10, 100]) } IN owing")
    void petPet(@Param("edge_id") String edge_id);

    @Query("FOR pet IN pets\n" +
            "  FILTER pet._id NOT IN (FOR pet2, owing IN OUTBOUND @userid owing RETURN pet2._id)\n" +
            "  SORT pet.minim_point\n" +
            "  RETURN pet\n" +
            "  ")
    Iterable<Pet> getShopPets(@Param("userid") String userid);
}

