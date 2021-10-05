package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Person;

public interface PeopleRepository extends JpaRepository<Person,Long> {
    public Person findBySno(Integer sno);
}
