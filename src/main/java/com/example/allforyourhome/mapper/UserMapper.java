package com.example.allforyourhome.mapper;

import com.example.allforyourhome.entity.User;
import com.example.allforyourhome.payload.SignUpDTO;
import com.example.allforyourhome.payload.UserDTO;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UserMapper {
    User toUser(UserDTO userDTO);

    User toUser(SignUpDTO signUpDTO);


    UserDTO toUserDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(UserDTO userDTO, @MappingTarget User user);
}