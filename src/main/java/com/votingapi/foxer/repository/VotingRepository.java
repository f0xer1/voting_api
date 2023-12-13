package com.votingapi.foxer.repository;

import com.votingapi.foxer.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {
}
