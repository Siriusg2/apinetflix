package com.consulti.templatespringboot.utils.validations;

import com.consulti.templatespringboot.models.PaymentsModel;
import com.consulti.templatespringboot.models.UsersModel;
import com.consulti.templatespringboot.repositories.PaymentRepository;
import com.consulti.templatespringboot.repositories.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentsValidations {

  @Autowired
  PaymentRepository paymentRepository;

  @Autowired
  UserRepository userRepository;

  public void paymentsValidationFreeAccountUse(String email) throws Exception {
    UsersModel user = userRepository.findByEmail(email);

    // Obtener el valor del campo dateAccountCreated como Date
    Date dateAccountCreated = user.getCreatedDate();

    List<PaymentsModel> userPayments = user.getPayment();

    // Convertir Date a LocalDateTime
    LocalDateTime accountCreatedDateTime = LocalDateTime.ofInstant(
      dateAccountCreated.toInstant(),
      ZoneId.systemDefault()
    );

    // Obtener la fecha actual en LocalDate
    LocalDate currentDate = LocalDate.now();

    // Obtener la parte de fecha de LocalDateTime
    LocalDate accountCreatedDate = accountCreatedDateTime.toLocalDate();

    if (userPayments.size() == 0) {
      // Calcular los días transcurridos
      long daysElapsed = ChronoUnit.DAYS.between(
        accountCreatedDate,
        currentDate
      );

      if (daysElapsed > 7) {
        throw new Exception(
          "Ha caducado tu periodo de prueba de 7 dias, para continuar disfrutando del contenido realiza un pago"
        );
      }
    } else {
      int lastUserPaymentIndex = userPayments.size() - 1;

      PaymentsModel lastUserPayment = userPayments.get(lastUserPaymentIndex);

      Date dateLastPayment = lastUserPayment.getCreatedDate();

      // Convertir Date a LocalDateTime
      LocalDateTime lastPaymentCreatedDateTime = LocalDateTime.ofInstant(
        dateLastPayment.toInstant(),
        ZoneId.systemDefault()
      );

      // Obtener la parte de fecha de LocalDateTime
      LocalDate lastPaymentGetLocalDate = lastPaymentCreatedDateTime.toLocalDate();

      long daysElapsedAfterLastPayment = ChronoUnit.DAYS.between(
        lastPaymentGetLocalDate,
        currentDate
      );

      if (daysElapsedAfterLastPayment > 30) {
        throw new Exception(
          "Han transcurrido " +
          daysElapsedAfterLastPayment +
          " dias desde tu ultimo pago, realiza un nuevo pago para continuar disfrutando del servicio"
        );
      }
    }
  }
}
