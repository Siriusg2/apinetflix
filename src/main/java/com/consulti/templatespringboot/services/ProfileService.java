package com.consulti.templatespringboot.services;

import com.consulti.templatespringboot.models.ProfilesModel;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
  List<ProfilesModel> getAllProfiles(String idClient) throws Exception;

  ProfilesModel createProfile(
    String userId,
    String newProfileName,
    String newProfileDateBorn
  ) throws Exception;
  ProfilesModel updateProfile(
    Long profileId,
    String userId,
    String newProfileName,
    String newProfileDateBorn
  ) throws Exception;
  Boolean deleteProfile(Long profileId) throws Exception;
}
