package com.votingapi.foxer.service;

import com.votingapi.foxer.model.Voting;
import com.votingapi.foxer.repository.VotingRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VotingService {
    VotingRepository votingRepository;

    public ResponseEntity<List<Voting>>  getAllVoting(){
        try {
            return new ResponseEntity<>(votingRepository.findAll(), HttpStatus.OK) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;
    }

    public ResponseEntity< String> addVoting(Voting voting) {
        votingRepository.save(voting);
        return new ResponseEntity<>("success",HttpStatus.CREATED) ;
    }


}
