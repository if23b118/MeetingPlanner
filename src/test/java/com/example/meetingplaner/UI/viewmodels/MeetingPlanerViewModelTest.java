package com.example.meetingplaner.UI.viewmodels;

import com.example.meetingplaner.BL.models.DataModel;
import com.example.meetingplaner.BL.models.Meeting;
import com.example.meetingplaner.BL.models.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MeetingPlanerViewModelTest {

    @Mock
    private DataModel model;

    @InjectMocks
    private MeetingPlanerViewModel meetingPlanerViewModel;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting(0, "newTitle", "2024-11-21 12:00", "2024-11-21 12:30", "agenda"));
        meetings.add(new Meeting(1, "Title", "2024-11-10 12:00", "2024-11-10 12:30", "agenda"));
        when(model.getAllMeetings()).thenReturn(meetings);

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(0,"asdf"));
        notes.add(new Note(0,"qwert"));
        notes.add(new Note(1,"new"));
        notes.add(new Note(1,"qwer"));
        when(model.getAllNotes()).thenReturn(notes);

    }



    @Test
    void onSearchButton_inBothTitles() {
        //Given
        meetingPlanerViewModel.getSearchField().set("title");
        meetingPlanerViewModel.loadMeetings();
        meetingPlanerViewModel.loadNotes();

        //When
        meetingPlanerViewModel.onSearchButton();

        //Then
        assertFalse(meetingPlanerViewModel.getMeetingsListView().isEmpty());
        assertEquals(2, meetingPlanerViewModel.getMeetingsListView().size());
    }

    @Test
    void onSearchButton_inBothAgendas() {
        //Given
        meetingPlanerViewModel.getSearchField().set("agenda");
        meetingPlanerViewModel.loadMeetings();
        meetingPlanerViewModel.loadNotes();

        //When
        meetingPlanerViewModel.onSearchButton();

        //Then
        assertFalse(meetingPlanerViewModel.getMeetingsListView().isEmpty());
        assertEquals(2, meetingPlanerViewModel.getMeetingsListView().size());
    }

    @Test
    void onSearchButton_oneInTitleOtherInNote() {
        //Given
        meetingPlanerViewModel.getSearchField().set("new");
        meetingPlanerViewModel.loadMeetings();
        meetingPlanerViewModel.loadNotes();

        //When
        meetingPlanerViewModel.onSearchButton();

        //Then
        assertFalse(meetingPlanerViewModel.getMeetingsListView().isEmpty());
        assertEquals(2, meetingPlanerViewModel.getMeetingsListView().size());
    }

    @Test
    void fillMeetingListViews_emptyMeetings() {
        //Given

        //When
        meetingPlanerViewModel.fillMeetingListViews();

        //Then
        assertTrue(meetingPlanerViewModel.getMeetingsListView().isEmpty());
    }

    @Test
    void fillMeetingListViews_normalMeetings() {
        //Given
        meetingPlanerViewModel.loadMeetings();

        //When
        meetingPlanerViewModel.fillMeetingListViews();

        //Then
        assertFalse(meetingPlanerViewModel.getMeetingsListView().isEmpty());
        assertEquals(2, meetingPlanerViewModel.getMeetingsListView().size());
    }

    @Test
    void newMeeting() {
        //Given
        meetingPlanerViewModel.loadMeetings();

        //When
        meetingPlanerViewModel.newMeeting();

        //Then
        assertEquals(3, meetingPlanerViewModel.getMeetingsListView().size());
        assertEquals("(Title)", meetingPlanerViewModel.getMeetings().getLast().getTitle());
        assertEquals("(YYYY-MM-DD TT:TT)", meetingPlanerViewModel.getMeetings().getLast().getFromDate());
        assertEquals("(YYYY-MM-DD TT:TT)", meetingPlanerViewModel.getMeetings().getLast().getToDate());
        assertEquals("(agenda)", meetingPlanerViewModel.getMeetings().getLast().getAgenda());
    }

    @Test
    void deleteChosenMeeting_deleteNotesDecreaseIds() {
        //Given
        meetingPlanerViewModel.loadMeetings();
        meetingPlanerViewModel.loadNotes();
        meetingPlanerViewModel.selectMeeting("newTitle");

        //When
        meetingPlanerViewModel.deleteChosenMeeting();

        assertEquals(1, meetingPlanerViewModel.getMeetingsListView().size());
        assertEquals(1, meetingPlanerViewModel.getMeetings().size());
        assertEquals(0, meetingPlanerViewModel.getNotesListView().size());
        assertEquals(2, meetingPlanerViewModel.getNotes().size());
        assertEquals("Title", meetingPlanerViewModel.getMeetings().getFirst().getTitle());
    }

    @Test
    void saveMeeting_withEmptyStrings() {
        //Given
        meetingPlanerViewModel.newMeeting();
        meetingPlanerViewModel.getTitleField().set("");
        meetingPlanerViewModel.getFromTimeField().set("");
        meetingPlanerViewModel.getToTimeField().set("");
        meetingPlanerViewModel.getAgendaTextArea().set("");

        //When
        meetingPlanerViewModel.saveMeeting();

        //Then
        assertFalse(meetingPlanerViewModel.getMeetings().isEmpty());
        assertFalse(meetingPlanerViewModel.getMeetingsListView().isEmpty());
        assertEquals(1, meetingPlanerViewModel.getMeetings().size());
        assertEquals(" ", meetingPlanerViewModel.getMeetings().getFirst().getTitle());
        assertEquals(" ", meetingPlanerViewModel.getMeetings().getFirst().getFromDate());
        assertEquals(" ", meetingPlanerViewModel.getMeetings().getFirst().getToDate());
        assertEquals(" ", meetingPlanerViewModel.getMeetings().getFirst().getAgenda());
    }

    @Test
    void saveMeeting_sameTitle() {
        //Given
        meetingPlanerViewModel.loadMeetings();
        meetingPlanerViewModel.getTitleField().set("Title");
        meetingPlanerViewModel.getFromTimeField().set("2024-11-21 13:00");
        meetingPlanerViewModel.getToTimeField().set("2024-11-21 13:30");
        meetingPlanerViewModel.getAgendaTextArea().set("same Title");
        meetingPlanerViewModel.newMeeting();

        //When
        meetingPlanerViewModel.saveMeeting();

        //Then
        assertFalse(meetingPlanerViewModel.getMeetings().isEmpty());
        assertEquals(3, meetingPlanerViewModel.getMeetings().size());
        assertEquals("(Title)", meetingPlanerViewModel.getMeetings().getLast().getTitle());
        assertEquals("", meetingPlanerViewModel.getTitleField().get());
    }

    @Test
    void updateNote_updateExistingNote() {
        //Given
        meetingPlanerViewModel.loadMeetings();
        meetingPlanerViewModel.loadNotes();
        meetingPlanerViewModel.selectMeeting("newTitle");
        meetingPlanerViewModel.getNewNoteField().set("updatedNote");

        //When
        meetingPlanerViewModel.updateNote("asdf");

        //Then
        assertEquals(2, meetingPlanerViewModel.getMeetingsListView().size());
        assertEquals(2, meetingPlanerViewModel.getMeetings().size());
        assertEquals(2, meetingPlanerViewModel.getNotesListView().size());
        assertEquals(4, meetingPlanerViewModel.getNotes().size());
        assertEquals("updatedNote", meetingPlanerViewModel.getNotes().getFirst().getContent());
        assertEquals("updatedNote", meetingPlanerViewModel.getNotesListView().getFirst());
    }

    @Test
    void updateNote_deleteNote() {
        //Given
        meetingPlanerViewModel.loadMeetings();
        meetingPlanerViewModel.loadNotes();
        meetingPlanerViewModel.selectMeeting("newTitle");
        meetingPlanerViewModel.getNewNoteField().set("");

        //When
        meetingPlanerViewModel.updateNote("asdf");

        //Then
        assertEquals(2, meetingPlanerViewModel.getMeetingsListView().size());
        assertEquals(2, meetingPlanerViewModel.getMeetings().size());
        assertEquals(1, meetingPlanerViewModel.getNotesListView().size());
        assertEquals(3, meetingPlanerViewModel.getNotes().size());
        assertEquals("qwert", meetingPlanerViewModel.getNotes().getFirst().getContent());
        assertEquals("qwert", meetingPlanerViewModel.getNotesListView().getFirst());
    }
}