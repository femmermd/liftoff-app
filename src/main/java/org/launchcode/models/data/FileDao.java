package org.launchcode.models.data;

import org.launchcode.models.Objects.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FileDao extends CrudRepository<Photo, Integer> {

}
