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

    public List<Entry> getAllPalindromes() {

        List<Entry> palindromes = new ArrayList<>();

        for (char c = 'a'; c <= 'z'; c++) {

            String letter = Character.toString(c);

            List<Entry> startsWith = restClient.getWordsStartingWith(letter);
            List<Entry> endsWith = restClient.getWordsEndingWith(letter);

            // manually check if word exists in both lists by comparing word string
            for (Entry start : startsWith) {
                for (Entry end : endsWith) {
                    if (start.getWord().equals(end.getWord())) {
                        // check if palindrome
                        String word = start.getWord();
                        String reverse = new StringBuilder(word).reverse().toString();
                        if (word.equals(reverse)) {
                            palindromes.add(start);
                        }
                        break;
                    }
                }
            }
        }

        Collections.sort(palindromes);
        return palindromes;
    }
}