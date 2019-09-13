package org.launchcode.models.data;

import org.launchcode.models.Objects.DBFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.File;

@Repository
@Transactional
public interface FileDao extends CrudRepository<DBFile, Integer> {

    DBFile findById(int id);

}
