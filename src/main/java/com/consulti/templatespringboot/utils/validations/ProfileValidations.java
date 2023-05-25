package com.consulti.templatespringboot.utils.validations;

import com.consulti.templatespringboot.models.PlanModel;
import com.consulti.templatespringboot.models.ProfilesModel;
import com.consulti.templatespringboot.models.UsersModel;
import com.consulti.templatespringboot.repositories.ProfileRepository;
import com.consulti.templatespringboot.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileValidations {

  @Autowired
  ProfileRepository profileRepository;

  @Autowired
  UserRepository userRepository;

  public void saveProfileValidation(Long userId) throws Exception {
    Optional<UsersModel> existingUser = userRepository.findById(userId);

    if (existingUser.get() != null) {
      UsersModel user = existingUser.get();

      PlanModel userPlan = user.getPlan();

      List<ProfilesModel> userProfiles = user.getProfiles();

      int activeProfiles = 0;
      for (int i = 0; i < userProfiles.size(); i++) {
        if (userProfiles.get(i).getIsActive()) {
          activeProfiles++;
        }
      }

      if (userPlan.getId() == 1 && activeProfiles == 2) {
        throw new Exception(
          "El usuario ya posee el maximo de perfiles de acuerdo a su plan, cambie de plan para tener acceso a mas perfiles"
        );
      }
      if (userPlan.getId() == 2 && activeProfiles == 3) {
        throw new Exception(
          "El usuario ya posee el maximo de perfiles de acuerdo a su plan, cambie de plan para tener acceso a mas perfiles"
        );
      }
      if (userPlan.getId() == 3 && activeProfiles == 5) {
        throw new Exception(
          "El usuario ya posee el maximo de perfiles permitidos por usuarios, adquiera una nueva cuenta para tener acceso a mas perfiles"
        );
      }
    } else throw new Exception("No existe el usuario");
  }
}
