package com.consulti.templatespringboot.controllers;

import com.consulti.templatespringboot.models.PaymentsModel;
import com.consulti.templatespringboot.services.PaymentsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

  @Autowired
  PaymentsService paymentsService;

  @GetMapping("/all")
  public List<PaymentsModel> getAllPayments() throws Exception {
    return paymentsService.listar();
  }

  @PostMapping("/save-payment")
  public ResponseEntity<PaymentsModel> savePayment(
    @RequestBody Map<String, String> newPayment
  ) throws Exception {
    String idUser = newPayment.get("idUser");

    PaymentsModel savedPayment = paymentsService.save(idUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
  }
}
