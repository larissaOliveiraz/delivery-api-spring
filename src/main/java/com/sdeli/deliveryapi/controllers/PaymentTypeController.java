package com.sdeli.deliveryapi.controllers;

import com.sdeli.deliveryapi.dto.PaymentTypeDTO;
import com.sdeli.deliveryapi.dto.factories.MakePaymentTypeDTO;
import com.sdeli.deliveryapi.dto.input.PaymentTypeInput;
import com.sdeli.deliveryapi.model.PaymentType;
import com.sdeli.deliveryapi.repositories.PaymentTypeRepository;
import com.sdeli.deliveryapi.services.PaymentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-types")
public class PaymentTypeController {

    private final PaymentTypeRepository repository;
    private final PaymentTypeService service;
    private final MakePaymentTypeDTO makeDTO;

    public PaymentTypeController(PaymentTypeRepository repository, PaymentTypeService service, MakePaymentTypeDTO makeDTO) {
        this.repository = repository;
        this.service = service;
        this.makeDTO = makeDTO;
    }

    @GetMapping
    public List<PaymentTypeDTO> findAll() {
        List<PaymentType> paymentTypes = repository.findAll();
        return makeDTO.toCollectionDTO(paymentTypes);
    }

    @GetMapping("/{id}")
    public PaymentTypeDTO findById(@PathVariable Long id) {
        PaymentType paymentType = service.findByIdOrThrow(id);
        return makeDTO.toDTO(paymentType);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentTypeDTO save(@RequestBody PaymentTypeInput paymentTypeInput) {
        PaymentType paymentType = makeDTO.toDomain(paymentTypeInput);

        paymentType = service.save(paymentType);

        return makeDTO.toDTO(paymentType);
    }

    @PutMapping("/{id}")
    public PaymentTypeDTO update(@PathVariable Long id,
                                 @RequestBody PaymentTypeInput paymentTypeInput) {
        PaymentType paymentType = service.findByIdOrThrow(id);

        makeDTO.copyToDomain(paymentTypeInput, paymentType);
        paymentType = service.save(paymentType);

        return makeDTO.toDTO(paymentType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
