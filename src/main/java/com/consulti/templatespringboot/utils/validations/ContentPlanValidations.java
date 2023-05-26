package com.consulti.templatespringboot.utils.validations;

import com.consulti.templatespringboot.repositories.ContentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentPlanValidations {

  @Autowired
  ContentPlanRepository contentPlanRepository;
}
