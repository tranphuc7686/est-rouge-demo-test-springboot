package com.example.estrougedemospringboot.api.v1.work;

import com.example.estrougedemospringboot.models.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkRepository extends JpaRepository<Work,Integer> {

}
