package com.deodev.PaymentServiceProvider.mapper;

import com.deodev.PaymentServiceProvider.dto.UserRegistrationDTO;
import com.deodev.PaymentServiceProvider.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRegistrationDTO dto);
}
