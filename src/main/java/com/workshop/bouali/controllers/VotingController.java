package com.workshop.bouali.controllers;

import com.workshop.bouali.pojo.Voting;
import com.workshop.bouali.service.VotingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController("voting")
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
