package com.example.aggregator.controller;

import com.example.aggregator.model.Entry;
import com.example.aggregator.service.AggregatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AggregatorController {

    private AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/")
    public List<Entry> helloWorld() {
        List<Entry> entries = new ArrayList<>();
        entries.add(aggregatorService.getDefinitionFor("hello"));
        entries.add(aggregatorService.getDefinitionFor("world"));
        return entries;
    }

    @GetMapping("/getDefinitionFor/{word}")
    public Entry getDefinitionFor(@PathVariable String word) {
        return aggregatorService.getDefinitionFor(word);
    }

    // README Aggregator Step 3: copied getDefinitionFor, changed @GetMapping to
    // /getAllPalindromes, removed path variable, changed return type to List<Entry>,
    // changed service call to getAllPalindromes()
    @GetMapping("/getAllPalindromes")
    public List<Entry> getAllPalindromes() {
        return aggregatorService.getAllPalindromes();
    }
}