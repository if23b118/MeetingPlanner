package com.example.meetingplaner;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

class MeetingPlanerTest extends ApplicationTest {

    public void start(Stage stage) throws Exception {
        new MeetingPlaner().start(stage);
    }

    @BeforeEach
    void setUp() throws Exception {
        FxToolkit.setupStage(Stage::show);
    }

    @AfterEach
    void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void testSearchButtonEnabled() {
        verifyThat("#searchButton", isEnabled());
    }

    @Test
    void testShouldContainButtonWithText() {
        verifyThat("#searchButton", LabeledMatchers.hasText("Search"));
    }

    @Test
    void testOnclickButtonStillEnabled() {
        clickOn("#searchButton");
        verifyThat("#searchButton", isEnabled());
    }

    @Test
    void testOnclickButtonStillWithTest() {
        clickOn("#searchButton");
        verifyThat("#searchButton", LabeledMatchers.hasText("Search"));
    }
}