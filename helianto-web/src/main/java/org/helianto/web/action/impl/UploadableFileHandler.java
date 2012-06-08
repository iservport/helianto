package org.helianto.web.action.impl;

import java.io.IOException;
import java.io.Serializable;

import org.helianto.core.def.Uploadable;
import org.springframework.web.multipart.MultipartFile;

/**
 * File handler.
 * 
 * @author mauriciofernandesdecastro
 */
public class UploadableFileHandler implements Serializable {

	private static final long serialVersionUID = 1L;

//	public void processFile(Uploadable uploadable) throws IOException {
//		uploadable.setContent(file.getBytes());
//		uploadable.setMultipartFileContentType(file.getContentType());
//    }

    // collabs
    
    private transient MultipartFile file;
    
    public void setFile(MultipartFile file) {
        this.file = file;
    } 
}
