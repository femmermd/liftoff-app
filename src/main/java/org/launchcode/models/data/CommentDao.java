package org.launchcode.models.data;

import org.launchcode.models.Objects.Comment;
import org.launchcode.models.Objects.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentDao extends CrudRepository<Comment, Integer> {


}
