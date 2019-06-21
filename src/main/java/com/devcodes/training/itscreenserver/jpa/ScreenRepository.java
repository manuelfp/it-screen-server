package com.devcodes.training.itscreenserver.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends CrudRepository<Screen, String> {

}
