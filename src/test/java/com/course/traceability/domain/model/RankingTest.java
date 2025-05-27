package com.course.traceability.domain.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class RankingTest {
    @Test
    void testRankingAllArgsAndGettersSetters() {
        Duration duration = Duration.ofMinutes(15);
        Ranking ranking = new Ranking(1L, 10, duration, "5min");

        assertEquals(1L, ranking.getIdEmployee());
        assertEquals(10, ranking.getOrderQuantity());
        assertEquals(duration, ranking.getDurationOrder());
        assertEquals("5min", ranking.getAverage());

        ranking.setIdEmployee(2L);
        ranking.setOrderQuantity(20);
        ranking.setDurationOrder(Duration.ofMinutes(30));
        ranking.setAverage("10min");

        assertEquals(2L, ranking.getIdEmployee());
        assertEquals(20, ranking.getOrderQuantity());
        assertEquals(Duration.ofMinutes(30), ranking.getDurationOrder());
        assertEquals("10min", ranking.getAverage());
    }

    @Test
    void testNoArgsConstructor() {
        Ranking ranking = new Ranking();
        assertNotNull(ranking);
    }

}