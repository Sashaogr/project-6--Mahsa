package com.example.aggregator.client;

import com.example.aggregator.model.Entry;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AggregatorRestClient {

    private RestTemplate restTemplate;

    public AggregatorRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Entry getDefinitionFor(String word) {

        String uri = "http://localhost:9091/getWord/" + word;

        Entry result = restTemplate.getForObject(uri, Entry.class);

        return result;
    }

    // Calls the existing dictionary endpoint (note: typo "Starging" is in the
    // original DictionaryController URL and must be matched exactly)
    public List<Entry> getWordsStartingWith(String value) {

        String uri = "http://localhost:9091/getWordsStargingWith/" + value;

        Entry[] result = restTemplate.getForObject(uri, Entry[].class);

        return Arrays.asList(result);
    }

    // README Aggregator Step 1: copied getWordsStartingWith, renamed to
    // getWordsEndingWith, changed URI to point to new dictionary endpoint
    public List<Entry> getWordsEndingWith(String value) {

        String uri = "http://localhost:9091/getWordsEndingWith/" + value;

        Entry[] result = restTemplate.getForObject(uri, Entry[].class);

        return Arrays.asList(result);
    }
}
