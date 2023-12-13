package com.votingapi.foxer.web.mapper;

import com.votingapi.foxer.model.User;
import com.votingapi.foxer.web.dto.user.UserCreationDto;
import com.votingapi.foxer.web.dto.user.UserDto;
import com.votingapi.foxer.web.dto.user.UserUpdateDto;
import org.mapstruct.*;

@Mapper
public interface UserMapper {
    UserDto toPayload(User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toEntity(UserCreationDto userCreationDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserUpdateDto userDto, @MappingTarget User user);
}
