package org.launchcode.models.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class DBFile {

    @Id
    @GeneratedValue
    private int id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    public DBFile() {}

    public DBFile(String fileName, String fileType, byte[] data){
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
