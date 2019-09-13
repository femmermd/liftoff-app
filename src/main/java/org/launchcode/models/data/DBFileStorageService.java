package org.launchcode.models.data;

import org.launchcode.models.Objects.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileDao dbFileDao;

    public DBFile storeFile (MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

        return dbFileDao.save(dbFile);

    }

    public DBFile getFile(int fileId){
        return dbFileDao.findById(fileId);
    }


}
