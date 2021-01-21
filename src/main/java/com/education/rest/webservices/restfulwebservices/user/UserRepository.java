package com.education.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
The the repository extends from JpaRepository, and we declared as the Entity that will manage
which is USER and the primary will be Integer
* */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
