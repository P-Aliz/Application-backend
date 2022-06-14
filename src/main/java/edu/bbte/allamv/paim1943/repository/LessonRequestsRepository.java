package edu.bbte.allamv.paim1943.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import edu.bbte.allamv.paim1943.model.Lesson;
import edu.bbte.allamv.paim1943.model.LessonRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRequestsRepository extends ArangoRepository<LessonRequest, String> {
}

