package com.testapps.testserver;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileInfo {
    private String fileName;
    private Long fileLength;
    private String contentType;
    private Boolean readable;
    private Boolean isFileEmpty;
    private byte[] fileData;


}
