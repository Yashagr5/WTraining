package com.example.validate.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.validate.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{

}

// Repository is use to persist the data into database using hibernate and jdbc
// Repository are of two types : CrudRepository (CRUD Operation)
//and JpaRepository is for(CRUD + paginationAndSorting)