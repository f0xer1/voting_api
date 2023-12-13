package com.votingapi.foxer.web.mapper;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.votingapi.foxer.web.dto.auth.JwtToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface JwtTokenMapper {
    @Mapping(target = "expiresAt", expression = "java(jwt.getExpiresAt().getTime() / 1000)")
    JwtToken toPayload(DecodedJWT jwt);
}
