package com.sdeli.deliveryapi.application.mappers;

import com.sdeli.deliveryapi.application.dto.PaymentTypeDTO;
import com.sdeli.deliveryapi.application.dto.input.PaymentTypeInput;
import com.sdeli.deliveryapi.domain.model.PaymentType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentTypeMapper {

    private final ModelMapper modelMapper;

    public PaymentTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PaymentTypeDTO toDTO(PaymentType paymentType) {
        return modelMapper.map(paymentType, PaymentTypeDTO.class);
    }

    public List<PaymentTypeDTO> toCollectionDTO(Collection<PaymentType> paymentTypes) {
        return paymentTypes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PaymentType toDomain(PaymentTypeInput paymentTypeInput) {
        return modelMapper.map(paymentTypeInput, PaymentType.class);
    }

    public void copyToDomain(PaymentTypeInput paymentTypeInput, PaymentType paymentType) {
        modelMapper.map(paymentTypeInput, paymentType);
    }
}
