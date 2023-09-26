package com.sdeli.deliveryapi.config;

import com.sdeli.deliveryapi.dto.AddressDTO;
import com.sdeli.deliveryapi.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var addressDtoType = modelMapper.createTypeMap(Address.class, AddressDTO.class);

        addressDtoType.<String>addMapping(
                address -> address.getCity().getState().getName(),
                (dest, value) -> dest.getCity().setState(value)
        );

        return modelMapper;
    }

}
