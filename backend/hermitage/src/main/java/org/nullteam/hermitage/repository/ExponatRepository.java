package org.nullteam.hermitage.repository;

import org.nullteam.hermitage.models.Exponat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource
public interface ExponatRepository extends MongoRepository<Exponat, String> {

}
