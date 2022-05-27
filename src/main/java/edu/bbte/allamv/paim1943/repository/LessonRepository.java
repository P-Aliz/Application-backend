package edu.bbte.allamv.paim1943.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import edu.bbte.allamv.paim1943.model.Lesson;
import edu.bbte.allamv.paim1943.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LessonRepository extends ArangoRepository<Lesson, String> {
}

