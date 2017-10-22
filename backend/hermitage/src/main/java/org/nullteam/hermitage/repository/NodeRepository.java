package org.nullteam.hermitage.repository;

import org.nullteam.hermitage.models.Node;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigInteger;

@CrossOrigin
@RepositoryRestResource
public interface NodeRepository extends MongoRepository<Node, BigInteger> {

}
