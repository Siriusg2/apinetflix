package com.consulti.templatespringboot.services.impl;

import com.consulti.templatespringboot.models.*;
import com.consulti.templatespringboot.repositories.ProfileRepository;
import com.consulti.templatespringboot.repositories.UserRepository;
import com.consulti.templatespringboot.services.*;
import com.consulti.templatespringboot.utils.validations.ProfileValidations;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

  @Autowired
  ProfileRepository profileRepository;

  @Autowired
  ProfileValidations profileValidations;

  @Autowired
  UserRepository userRepository;

  @Override
  public List<ProfilesModel> getAllProfiles(String idRequester)
    throws Exception {
    Long parseId = Long.valueOf(idRequester);
    Optional<UsersModel> queryUser = userRepository.findById(parseId);
    UsersModel user = queryUser.get();

    if (user == null) throw new Exception("no existe el usuario ");

    List<ProfilesModel> userProfiles = user.getProfiles();
    List<ProfilesModel> allProfiles = profileRepository.findAll();

    if (user.getRole().getId() == 1) return allProfiles;

    if (user.getRole().getId() == 2) return userProfiles;

    return null;
  }

  @Override
  public ProfilesModel createProfile(
    String userId,
    String newProfileName,
    String newProfileDateBorn
  ) throws Exception {
    Long parseId = Long.valueOf(userId);

    Optional<UsersModel> queryUser = userRepository.findById(parseId);
    UsersModel user = queryUser.get();

    profileValidations.saveProfileValidation(parseId);

    ProfilesModel newProfile = new ProfilesModel();

    newProfile.setName(newProfileName);
    newProfile.setDateOfBirth(newProfileDateBorn);
    newProfile.setCreatedBy(user.getEmail());
    newProfile.setUser(user);

    ProfilesModel savedProfile = profileRepository.save(newProfile);
    return savedProfile;
  }

  @Override
  public ProfilesModel updateProfile(
    Long profileId,
    String userId,
    String newProfileName,
    String newProfileDateBorn
  ) throws Exception {
    Optional<ProfilesModel> profileQuery = profileRepository.findById(
      profileId
    );

    ProfilesModel profile = profileQuery.get();

    profile.setDateOfBirth(newProfileDateBorn);
    profile.setName(newProfileName);
    ProfilesModel updatedProfile = profileRepository.save(profile);

    return updatedProfile;
  }

  @Override
  public Boolean deleteProfile(Long profileId) throws Exception {
    Optional<ProfilesModel> profileQuery = profileRepository.findById(
      profileId
    );
    ProfilesModel profile = profileQuery.get();
    profile.setIsActive(false);
    profileRepository.save(profile);
    return true;
  }
}
