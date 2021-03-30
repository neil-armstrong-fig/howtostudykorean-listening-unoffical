package com.howtostudykorean.downloading

enum class Page(val url: String) {
    LESSON_1("https://www.howtostudykorean.com/unit1/unit-1-lessons-1-8/unit-1-lesson-1/"),
    LESSON_2("https://www.howtostudykorean.com/unit1/unit-1-lessons-1-8/unit-1-lesson-2/"),
    LESSON_3("https://www.howtostudykorean.com/unit1/unit-1-lessons-1-8/unit-1-lesson-3/"),
    LESSON_4("https://www.howtostudykorean.com/unit1/unit-1-lessons-1-8/unit-1-lesson-4/"),
    LESSON_5("https://www.howtostudykorean.com/unit1/unit-1-lessons-1-8/unit-1-lesson-5/"),
    LESSON_6("https://www.howtostudykorean.com/unit1/unit-1-lessons-1-8/unit-1-lesson-6/"),
    LESSON_7("https://www.howtostudykorean.com/unit1/unit-1-lessons-1-8/unit-1-lesson-7/"),
    LESSON_8("https://www.howtostudykorean.com/unit1/unit-1-lessons-1-8/unit-1-lesson-8/"),
    LESSON_9("https://www.howtostudykorean.com/unit1/unit-1-lessons-9-16/unit-1-lesson-9/"),
    LESSON_10("https://www.howtostudykorean.com/unit1/unit-1-lessons-9-16/unit-1-lesson-10/"),
    LESSON_11("https://www.howtostudykorean.com/unit1/unit-1-lessons-9-16/lesson-11/"),
    LESSON_12("https://www.howtostudykorean.com/unit1/unit-1-lessons-9-16/lesson-12/"),
    LESSON_13("https://www.howtostudykorean.com/unit1/unit-1-lessons-9-16/lesson-13/"),
    LESSON_14("https://www.howtostudykorean.com/unit1/unit-1-lessons-9-16/lesson-14-korean-passive-verbs/"),
    LESSON_15("https://www.howtostudykorean.com/unit1/unit-1-lessons-9-16/lesson-15/"),
    LESSON_16("https://www.howtostudykorean.com/unit1/unit-1-lessons-9-16/lesson-16/"),
    LESSON_17("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-17/"),
    LESSON_18("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-18/"),
    LESSON_19("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-19/"),
    LESSON_20("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-20/"),
    LESSON_21("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-21-asking-questions-in-korean-why-when-where-and-who/"),
    LESSON_22("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-22/"),
    LESSON_23("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-23/"),
    LESSON_24("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-24/"),
    LESSON_25("https://www.howtostudykorean.com/unit1/unit-1-lessons-17-25-2/lesson-25/"),
}

fun getPageForLessonNumber(index: Int) = when (index) {
    1 -> Page.LESSON_1
    2 -> Page.LESSON_2
    3 -> Page.LESSON_3
    4 -> Page.LESSON_4
    5 -> Page.LESSON_5
    6 -> Page.LESSON_6
    7 -> Page.LESSON_7
    8 -> Page.LESSON_8
    9 -> Page.LESSON_9
    10 -> Page.LESSON_10
    11 -> Page.LESSON_11
    12 -> Page.LESSON_12
    13 -> Page.LESSON_13
    14 -> Page.LESSON_14
    15 -> Page.LESSON_15
    16 -> Page.LESSON_16
    17 -> Page.LESSON_17
    18 -> Page.LESSON_18
    19 -> Page.LESSON_19
    20 -> Page.LESSON_20
    21 -> Page.LESSON_21
    22 -> Page.LESSON_22
    23 -> Page.LESSON_23
    24 -> Page.LESSON_24
    25 -> Page.LESSON_25
    else -> throw RuntimeException("Unknown page")
}