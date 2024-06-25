package com.rsvpagentprocessing.rsvpagentprocessing.repository;

import com.rsvpagentprocessing.rsvpagentprocessing.model.RSVP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RSVPRepository extends JpaRepository<RSVP, Integer> {

}
