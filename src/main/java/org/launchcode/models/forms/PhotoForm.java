package org.launchcode.models.forms;

import javassist.bytecode.ByteArray;
import org.launchcode.models.Objects.User;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;


public class PhotoForm {


    private File photo;


    public PhotoForm(){}


    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
}
