package com.consulti.templatespringboot.controllers;

import com.consulti.templatespringboot.models.ProfilesModel;
import com.consulti.templatespringboot.services.ProfileService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

  @Autowired
  ProfileService profileService;

  @GetMapping("/all")
  public ResponseEntity<List<ProfilesModel>> getAllProfiles(
    @RequestBody Map<String, String> idRequester
  ) throws Exception {
    String id = idRequester.get("idRequester");

    List<ProfilesModel> list = profileService.getAllProfiles(id);
    return ResponseEntity.status(HttpStatus.OK).body(list);
  }

  @PostMapping("/save-profile")
  public ResponseEntity<ProfilesModel> createProfile(
    @RequestBody Map<String, String> newProfileData
  ) throws Exception {
    String userId = newProfileData.get("idCreator");
    String newProfileName = newProfileData.get("name");
    String newProfileDateBorn = newProfileData.get("dateBorn");

    ProfilesModel newProfile = profileService.createProfile(
      userId,
      newProfileName,
      newProfileDateBorn
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(newProfile);
  }

  @PutMapping("/update-profile/{profileId}")
  public ResponseEntity<ProfilesModel> updateUser(
    @RequestBody Map<String, String> newDataprofile,
    @PathVariable Long profileId
  ) throws Exception {
    String userId = newDataprofile.get("idModifier");
    String newProfileName = newDataprofile.get("name");
    String newProfileDateBorn = newDataprofile.get("dateBorn");
    ProfilesModel updatedProfile = profileService.updateProfile(
      profileId,
      userId,
      newProfileName,
      newProfileDateBorn
    );
    return ResponseEntity.status(HttpStatus.OK).body(updatedProfile);
  }

  @DeleteMapping("delete/{profileId}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable Long profileId)
    throws Exception {
    Boolean deletedProfile = profileService.deleteProfile(profileId);
    return ResponseEntity.status(HttpStatus.OK).body(deletedProfile);
  }
}
