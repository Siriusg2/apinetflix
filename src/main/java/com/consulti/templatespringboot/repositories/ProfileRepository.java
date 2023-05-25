package com.consulti.templatespringboot.repositories;

import com.consulti.templatespringboot.models.ProfilesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfilesModel, Long> {}
