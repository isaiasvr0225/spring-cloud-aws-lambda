package com.spring.aws.config;

import com.spring.aws.domain.Character;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class AwsLambdaConfig {

    @Bean
    public Filter securityFilter() {
        return new SecurityFilter();
    }

    //Supplier exposes a GET http endpoint
    @Bean
    public Supplier<String> greeting() {
        return () -> "Hello World";
    }

    //Consumer exposes a POST http endpoint
    @Bean
    public Consumer<String> print() {
        return System.out::println;
    }

    @Bean
    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }

    //-------------------------------------------


    // Automatically, Spring converts the return value to JSON
    @Bean
    public Supplier<Map<String,Object>> getCharacter() {
        return () -> {
            Map<String,Object> character = new HashMap<>();
            character.put("name", "Luke Skywalker");
            character.put("job", "Jedi");
            return character;
        };
    }

    // Automatically, Spring converts the input JSON to a Map
    @Bean
    public Function<Map<String, Object>, String> getJSONCharacter(){
        return (input) -> {
            input.forEach((key, value) -> System.out.println(key + ":" + value));

            return "OK";
        };
    }


    @Bean
    public Function<Character, Character> receiveAnObject(){
        return (character) -> character;
    }

    @Bean
    public Function<Map<String, Object>, Map<String, Object>> receiveAJSON(){
        return (input) -> {

            input.forEach((key, value) -> System.out.println(key + ":" + value));

            Map<String, Object> newCharacter = new HashMap<>();
            newCharacter.put("name", "Luke Skywalker");
            newCharacter.put("job", "Jedi");
            newCharacter.put("healthPoints", 100);

            return newCharacter;
        };
    }
}
