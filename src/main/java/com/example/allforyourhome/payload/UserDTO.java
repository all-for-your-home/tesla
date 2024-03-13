package com.example.allforyourhome.payload;

import com.example.allforyourhome.enums.RoleEnum;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.example.allforyourhome.entity.User}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private LocalDateTime createdAt;
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private RoleEnum role;
    private AttachmentDTO avatar;
}