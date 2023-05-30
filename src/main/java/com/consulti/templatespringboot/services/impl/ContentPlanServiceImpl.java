package com.consulti.templatespringboot.services.impl;

import com.consulti.templatespringboot.models.ContentPlanModel;
import com.consulti.templatespringboot.models.ProfilesModel;
import com.consulti.templatespringboot.models.UsersModel;
import com.consulti.templatespringboot.repositories.ContentPlanRepository;
import com.consulti.templatespringboot.repositories.ProfileRepository;
import com.consulti.templatespringboot.repositories.UserRepository;
import com.consulti.templatespringboot.services.ContentPlanService;
import com.consulti.templatespringboot.utils.*;
import com.consulti.templatespringboot.utils.validations.PaymentsValidations;
import com.consulti.templatespringboot.utils.validations.ProfileValidations;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentPlanServiceImpl implements ContentPlanService {

  @Autowired
  FetchContent fetchContent;

  @Autowired
  ContentPlanRepository contentPlanRepository;

  @Autowired
  ProfileRepository profileRepository;

  @Autowired
  ProfileValidations profileValidations;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PaymentsValidations paymentsValidations;

  @Override
  public ContentPlanModel saveContentPlan(String name, String externalEndpoint)
    throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'saveContentPlan'"
    );
  }

  @Override
  public ContentPlanModel updateContenPlan(
    String name,
    String externalEndpoint
  ) throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'updateContenPlan'"
    );
  }

  @Override
  public Boolean deleteContentPlan(Long contentPlanId) throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
      "Unimplemented method 'deleteContentPlan'"
    );
  }

  @Override
  public JsonNode[] getContentPlanModelsbyUser(String Idprofile)
    throws Exception {
    Long parseLongIdProfile = Long.valueOf(Idprofile);

    ProfilesModel profile = profileRepository
      .findById(parseLongIdProfile)
      .get();
    UsersModel user = profile.getUser();

    paymentsValidations.paymentsValidationFreeAccountUse(user.getEmail());

    Boolean AdultOrKidAge = profileValidations.validationAge(
      profile.getDateOfBirth()
    );

    List<ContentPlanModel> userPlanContent = user.getPlan().getContent();
    List<String> allContent = new ArrayList<>();

    if (AdultOrKidAge == true) {
      for (ContentPlanModel content : userPlanContent) {
        allContent.add(content.getExternalEndpoint());
      }
    } else {
      for (ContentPlanModel content : userPlanContent) {
        if (!content.getName().equalsIgnoreCase("Contenido para Adultos")) {
          allContent.add(content.getExternalEndpoint());
        }
      }
    }

    JsonNode[] content = fetchContent.fetchContent(allContent);
    return content;
  }
}
