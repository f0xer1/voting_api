package com.votingapi.foxer.controllers;

import com.votingapi.foxer.model.Voting;
import com.votingapi.foxer.service.VotingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("voting")
public class VotingController {
    VotingService votingService;

    @GetMapping("all")
    public ResponseEntity<List<Voting>> getAllVoting(){
        return votingService.getAllVoting();
    }
    @PostMapping("create")
    public ResponseEntity<String> addVoting(@RequestBody Voting voting){
        return votingService.addVoting(voting);
    }

}
