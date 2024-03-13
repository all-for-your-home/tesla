package com.example.allforyourhome.payload;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.allforyourhome.entity.Attachment}
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO implements Serializable {
    private String originalName;
    private long size;
    private String contentType;
    private String path;
}