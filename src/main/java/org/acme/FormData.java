package org.acme;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class FormData {
    @RestForm("myfile")
    public FileUpload file;
}
