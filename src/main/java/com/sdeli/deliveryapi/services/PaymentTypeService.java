package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.EntityAlreadyExistsException;
import com.sdeli.deliveryapi.exceptions.EntityInUseException;
import com.sdeli.deliveryapi.exceptions.PaymentTypeNotFoundException;
import com.sdeli.deliveryapi.model.PaymentType;
import com.sdeli.deliveryapi.repositories.PaymentTypeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeService {

    private final PaymentTypeRepository repository;

    public PaymentTypeService(PaymentTypeRepository repository) {
        this.repository = repository;
    }

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
