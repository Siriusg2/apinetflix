package com.consulti.templatespringboot.controllers;

import com.consulti.templatespringboot.services.ContentPlanService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content-plan")
public class ContentPlanController {

  @Autowired
  ContentPlanService contentPlanService;

  @GetMapping("/get/{idProfile}")
  public ResponseEntity<JsonNode[]> getContent(@PathVariable String idProfile)
    throws Exception {
    JsonNode[] content = contentPlanService.getContentPlanModelsbyUser(
      idProfile
    );
    return ResponseEntity.status(HttpStatus.OK).body(content);
  }
}
