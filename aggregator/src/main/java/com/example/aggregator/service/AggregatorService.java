package com.example.aggregator.service;

import com.example.aggregator.client.AggregatorRestClient;
import com.example.aggregator.model.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AggregatorService {

    private AggregatorRestClient restClient;

    public AggregatorService(AggregatorRestClient restClient) {
        this.restClient = restClient;
    }

    public Entry getDefinitionFor(String word) {
        return restClient.getDefinitionFor(word);
    }

    // README Aggregator Step 2: getAllPalindromes service method.
    // Extra Credit version — no streams used anywhere.
    // Uses a char loop (a–z) instead of IntStream, and a for-each loop
    // instead of .filter() + .collect() for the palindrome check.
    public List<Entry> getAllPalindromes() {

        List<Entry> candidates = new ArrayList<>();

        // Iterate from a to z
        for (char c = 'a'; c <= 'z'; c++) {

            String letter = Character.toString(c);

            // get words starting and ending with character
            List<Entry> startsWith = restClient.getWordsStartingWith(letter);
            List<Entry> endsWith = restClient.getWordsEndingWith(letter);

            // keep entries that exist in both lists
            List<Entry> startsAndEndsWith = new ArrayList<>(startsWith);
            startsAndEndsWith.retainAll(endsWith);

            // store list with existing entries
            candidates.addAll(startsAndEndsWith);
        }

        // test each entry for palindrome
        List<Entry> palindromes = new ArrayList<>();
        for (Entry entry : candidates) {
            String word = entry.getWord();
            String reverse = new StringBuilder(word).reverse().toString();
            if (word.equals(reverse)) {
                palindromes.add(entry);
            }
        }

        // sort and return
        Collections.sort(palindromes);
        return palindromes;
    }
}