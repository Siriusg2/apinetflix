package com.consulti.templatespringboot.controllers;

import com.consulti.templatespringboot.models.PlanModel;
import com.consulti.templatespringboot.services.PlanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanController {

  @Autowired
  PlanService planService;

  @GetMapping("/all")
  public List<PlanModel> getAllPlan() throws Exception {
    return planService.listar();
  }

  @DeleteMapping("delete/{planId}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable Long planId)
    throws Exception {
    return ResponseEntity.status(HttpStatus.OK).body(true);
  }

  @PostMapping("/save-plan")
  public ResponseEntity<PlanModel> savePayment(@RequestBody PlanModel newPlan)
    throws Exception {
    PlanModel savedPayment = planService.save(newPlan);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
  }

  @PutMapping("/update-plan")
  public ResponseEntity<PlanModel> updatePlan(
    @RequestBody PlanModel planDetails
  ) throws Exception {
    PlanModel updatedPlan = planService.update(planDetails);
    return ResponseEntity.ok(updatedPlan);
  }
}
