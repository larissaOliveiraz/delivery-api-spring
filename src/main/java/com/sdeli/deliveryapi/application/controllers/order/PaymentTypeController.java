package com.sdeli.deliveryapi.application.controllers.order;

import com.sdeli.deliveryapi.application.dto.PaymentTypeDTO;
import com.sdeli.deliveryapi.application.mappers.PaymentTypeMapper;
import com.sdeli.deliveryapi.application.dto.input.PaymentTypeInput;
import com.sdeli.deliveryapi.domain.model.PaymentType;
import com.sdeli.deliveryapi.domain.repositories.PaymentTypeRepository;
import com.sdeli.deliveryapi.domain.services.PaymentTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@RestController
@RequestMapping("/payment-types")
public class PaymentTypeController {

    private final PaymentTypeRepository repository;
    private final PaymentTypeService service;
    private final PaymentTypeMapper mapper;

    @GetMapping
    public ResponseEntity<List<PaymentTypeDTO>> findAll() {
        List<PaymentType> paymentTypes = repository.findAll();
        List<PaymentTypeDTO> paymentTypesDTO = mapper.toCollectionDTO(paymentTypes);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(paymentTypesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentTypeDTO> findById(@PathVariable Long id) {
        PaymentType paymentType = service.findByIdOrThrow(id);
        PaymentTypeDTO paymentTypeDTO = mapper.toDTO(paymentType);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(paymentTypeDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentTypeDTO save(@RequestBody @Valid PaymentTypeInput paymentTypeInput) {
        PaymentType paymentType = mapper.toDomain(paymentTypeInput);

        paymentType = service.save(paymentType);

        return mapper.toDTO(paymentType);
    }

    @PutMapping("/{id}")
    public PaymentTypeDTO update(@PathVariable Long id,
                                 @RequestBody @Valid PaymentTypeInput paymentTypeInput) {
        PaymentType paymentType = service.findByIdOrThrow(id);

        mapper.copyToDomain(paymentTypeInput, paymentType);
        paymentType = service.save(paymentType);

        return mapper.toDTO(paymentType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
