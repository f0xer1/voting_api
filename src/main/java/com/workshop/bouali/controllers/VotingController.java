package com.workshop.bouali.controllers;

import com.workshop.bouali.pojo.Voting;
import com.workshop.bouali.service.VotingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("voting")
public class VotingController {
    VotingService votingService;
    @GetMapping("")
            public  String test(){
        return "хуй";
    }

    @GetMapping("all")
    public ResponseEntity<List<Voting>> getAllVoting(){
        return votingService.getAllVoting();
    }
    @PostMapping("create")
    public ResponseEntity<String> addVoting(@RequestBody Voting voting){
        return votingService.addVoting(voting);
    }
}
