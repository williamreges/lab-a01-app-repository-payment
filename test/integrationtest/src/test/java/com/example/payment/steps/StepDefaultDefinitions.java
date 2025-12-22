package com.example.payment.steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class StepDefaultDefinitions {

    @Before
    public void init() {
        System.out.println("Inicio de Feature");
    }

    @After
    public void exit() {
        System.out.println("Fin de Feature");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Runs before all scenarios");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Runs after all scenarios");
    }

    @BeforeStep("not @zukini")
    public void before(Scenario scenario) {
        scenario.log("Inicio de Step");
    }

    @AfterStep
    public void after(Scenario scenario) {
        scenario.log("Fim de Step");
    }

}
