package co.wedevx.digitalbank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.cucumber.java.hu.Ha;

import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.Map;

public class MockData {
    private FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-US"), new RandomService());

//    public String generateRandomEmail() {
//        String email = fakeValuesService.bothify(new Faker().name().firstName() + "####@gmail.com");
//        return email;
//    }

    public Map<String, String>  generateRandomNameAndEmail() {
//        int randomInt = new Random().nextInt(4);
//        String email = name+randomInt+"@gmail.com";
        String name = fakeValuesService.bothify(new Faker().name().firstName());
        String email = fakeValuesService.bothify(name + "###@gmail.com");

        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);

        return data;
    }

    public String generateRandomSsn() {
        String ssn = String.format("%09d", new Random().nextInt(1000000000));
        return ssn;
    }


//    public static void main (String[] args) {
//        MockData mockData = new MockData();
////        //System.out.println(mockData.generateRandomEmail());
//
//        Map<String, String> data = mockData.generateRandomNameAndEmail();
//
//        System.out.println(data.get("name"));
//        System.out.println(data.get("email"));
//
//        System.out.println(mockData.generateRandomSsn());
//    }
}

