package org.launchcode.models.data;

import org.launchcode.models.Objects.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReviewDao extends CrudRepository<Review, Integer> {
    Review findByUserId(int userId);

    Review findById(int id);

}
