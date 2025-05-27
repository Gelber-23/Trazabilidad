package com.course.traceability.domain.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class EfficiencyTest {

    @Test
    void testAllArgsConstructorAndGettersSetters() {
        Duration duration = Duration.ofMinutes(12);
        Efficiency efficiency = new Efficiency(100L, 200L, duration, "12min");

        assertEquals(100L, efficiency.getIdOrder());
        assertEquals(200L, efficiency.getIdEmployee());
        assertEquals(duration, efficiency.getDurationOrder());
        assertEquals("12min", efficiency.getDurationString());

        efficiency.setIdOrder(101L);
        efficiency.setIdEmployee(201L);
        efficiency.setDurationOrder(Duration.ofMinutes(30));
        efficiency.setDurationString("30min");

        assertEquals(101L, efficiency.getIdOrder());
        assertEquals(201L, efficiency.getIdEmployee());
        assertEquals(Duration.ofMinutes(30), efficiency.getDurationOrder());
        assertEquals("30min", efficiency.getDurationString());
    }

    @Test
    void testNoArgsConstructor() {
        Efficiency efficiency = new Efficiency();
        assertNotNull(efficiency);
    }
}