package com.sdeli.deliveryapi.application.controllers.order;

import com.sdeli.deliveryapi.application.dto.PaymentTypeDTO;
import com.sdeli.deliveryapi.application.mappers.PaymentTypeMapper;
import com.sdeli.deliveryapi.application.dto.input.PaymentTypeInput;
import com.sdeli.deliveryapi.domain.model.PaymentType;
import com.sdeli.deliveryapi.domain.repositories.PaymentTypeRepository;
import com.sdeli.deliveryapi.domain.services.PaymentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payment-types")
public class PaymentTypeController {

    private final PaymentTypeRepository repository;
    private final PaymentTypeService service;
    private final PaymentTypeMapper makeDTO;

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
