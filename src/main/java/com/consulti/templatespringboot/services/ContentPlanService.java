package com.consulti.templatespringboot.services;

import com.consulti.templatespringboot.models.ContentPlanModel;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public interface ContentPlanService {
  public JsonNode[] getContentPlanModelsbyUser(String idProfile)
    throws Exception;

  public ContentPlanModel saveContentPlan(String name, String externalEndpoint)
    throws Exception;

  public ContentPlanModel updateContenPlan(
    String name,
    String externalEndpoint
  ) throws Exception;

  public Boolean deleteContentPlan(Long contentPlanId) throws Exception;
}
