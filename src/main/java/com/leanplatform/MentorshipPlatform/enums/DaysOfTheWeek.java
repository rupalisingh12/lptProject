package com.leanplatform.MentorshipPlatform.enums;

public enum DaysOfTheWeek {
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
    Sunday(7);

    private final int value;

    DaysOfTheWeek(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

