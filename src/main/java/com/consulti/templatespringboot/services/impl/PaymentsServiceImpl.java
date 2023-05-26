package com.consulti.templatespringboot.services.impl;

import com.consulti.templatespringboot.models.*;
import com.consulti.templatespringboot.repositories.PaymentRepository;
import com.consulti.templatespringboot.repositories.UserRepository;
import com.consulti.templatespringboot.services.*;
import com.consulti.templatespringboot.utils.validations.PaymentsValidations;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {

  @Autowired
  PaymentRepository paymentRepository;

  @Autowired
  PaymentsValidations paymentsValidations;

  @Autowired
  UserRepository userRepository;

  @Override
  public List<PaymentsModel> listar() throws Exception {
    return paymentRepository.findAll();
  }

  @Override
  public PaymentsModel save(String idUser) throws Exception {
    Long userId = Long.parseLong(idUser);
    Optional<UsersModel> userQuery = userRepository.findById(userId);

    UsersModel user = userQuery.get();

    PaymentsModel payment = new PaymentsModel();

    payment.setCreatedBy(user.getEmail());
    payment.setPayment(user);
    PaymentsModel savedPayment = paymentRepository.save(payment);

    return savedPayment;
  }
}
