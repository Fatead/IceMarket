package com.example.infrastructure.convert;

import com.example.dto.user.UserLoginReqDTO;
import com.example.dto.user.UserRegisterReqDTO;
import com.example.dto.user.UserUpdateReqDTO;
import com.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    User convert(UserRegisterReqDTO bean);

    User convert(UserLoginReqDTO bean);

    User convert(UserUpdateReqDTO bean);

}