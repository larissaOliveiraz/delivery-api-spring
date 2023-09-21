package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.PaymentTypeDTO;
import com.sdeli.deliveryapi.dto.factories.MakePaymentTypeDTO;
import com.sdeli.deliveryapi.dto.input.PaymentTypeInput;
import com.sdeli.deliveryapi.model.PaymentType;
import com.sdeli.deliveryapi.services.PaymentTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-types")
public class PaymentTypeController {

    private final PaymentTypeService service;
    private final MakePaymentTypeDTO makeDTO;

    public PaymentTypeController(PaymentTypeService service, MakePaymentTypeDTO makeDTO) {
        this.service = service;
        this.makeDTO = makeDTO;
    }

    @PostMapping
    public PaymentTypeDTO save(@RequestBody PaymentTypeInput paymentTypeInput) {
        PaymentType paymentType = makeDTO.toDomain(paymentTypeInput);

        paymentType = service.save(paymentType);

        return makeDTO.toDTO(paymentType);
    }
}
