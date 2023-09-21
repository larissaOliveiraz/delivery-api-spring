package com.sdeli.deliveryapi.services;

import com.sdeli.deliveryapi.exceptions.EntityAlreadyExistsException;
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

}
