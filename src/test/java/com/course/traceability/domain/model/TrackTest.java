package com.course.traceability.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {


    @Test
    void testAllArgsConstructorAndGettersSetters() {
        Date date = new Date();
        Track track = new Track(
                "track123",
                1L,
                2L,
                3L,
                4L,
                "client@example.com",
                date,
                "PENDING",
                "DELIVERED",
                "employee@example.com"
        );

        assertEquals("track123", track.getId());
        assertEquals(1L, track.getIdOrder());
        assertEquals(2L, track.getIdClient());
        assertEquals(3L, track.getIdEmployee());
        assertEquals(4L, track.getIdRestaurant());
        assertEquals("client@example.com", track.getEmail());
        assertEquals(date, track.getDate());
        assertEquals("PENDING", track.getPreviousState());
        assertEquals("DELIVERED", track.getNewState());
        assertEquals("employee@example.com", track.getEmailEmployee());

        // setters
        track.setId("newId");
        track.setIdOrder(10L);
        track.setIdClient(20L);
        track.setIdEmployee(30L);
        track.setIdRestaurant(40L);
        track.setEmail("newclient@example.com");
        Date newDate = new Date();
        track.setDate(newDate);
        track.setPreviousState("PREPARING");
        track.setNewState("SERVED");
        track.setEmailEmployee("newemployee@example.com");

        assertEquals("newId", track.getId());
        assertEquals(10L, track.getIdOrder());
        assertEquals(20L, track.getIdClient());
        assertEquals(30L, track.getIdEmployee());
        assertEquals(40L, track.getIdRestaurant());
        assertEquals("newclient@example.com", track.getEmail());
        assertEquals(newDate, track.getDate());
        assertEquals("PREPARING", track.getPreviousState());
        assertEquals("SERVED", track.getNewState());
        assertEquals("newemployee@example.com", track.getEmailEmployee());
    }

    @Test
    void testNoArgsConstructor() {
        Track track = new Track();
        assertNotNull(track);
    }

}