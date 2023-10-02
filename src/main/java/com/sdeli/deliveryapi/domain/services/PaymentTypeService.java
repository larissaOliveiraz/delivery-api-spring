package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.exceptions.EntityAlreadyExistsException;
import com.sdeli.deliveryapi.domain.exceptions.EntityInUseException;
import com.sdeli.deliveryapi.domain.exceptions.PaymentTypeNotFoundException;
import com.sdeli.deliveryapi.domain.model.PaymentType;
import com.sdeli.deliveryapi.domain.repositories.PaymentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentTypeService {

    private final PaymentTypeRepository repository;

    public PaymentType save(PaymentType paymentType) {
        try {
            return repository.save(paymentType);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityAlreadyExistsException("Payment type", paymentType.getDescription());
        }
    }

    public void delete(Long id) {
        try {
            findByIdOrThrow(id);
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException("Payment type", id);
        }
    }

    public PaymentType findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PaymentTypeNotFoundException(id));
    }

}
