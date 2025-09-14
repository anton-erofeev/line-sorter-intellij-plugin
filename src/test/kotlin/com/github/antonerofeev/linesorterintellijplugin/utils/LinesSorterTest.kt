package com.github.antonerofeev.linesorterintellijplugin.utils

import com.github.antonerofeev.linesorterintellijplugin.enums.SortOrder
import com.github.antonerofeev.linesorterintellijplugin.enums.SortType
import org.junit.Assert.assertEquals
import org.junit.Test

class LinesSorterTest {

    @Test
    fun `sorts lines alphabetically ascending`() {
        val input = "banana\napple\ncarrot"
        val expected = "apple\nbanana\ncarrot"
        val result = LinesSorter.sortText(input, SortType.ALPHABETICAL, SortOrder.ASCENDING)
        assertEquals(expected, result)
    }

    @Test
    fun `sorts lines alphabetically descending`() {
        val input = "banana\napple\ncarrot"
        val expected = "carrot\nbanana\napple"
        val result = LinesSorter.sortText(input, SortType.ALPHABETICAL, SortOrder.DESCENDING)
        assertEquals(expected, result)
    }

    @Test
    fun `sorts lines by length ascending`() {
        val input = "pear\nwatermelon\nfig"
        val expected = "fig\npear\nwatermelon"
        val result = LinesSorter.sortText(input, SortType.BY_LENGTH, SortOrder.ASCENDING)
        assertEquals(expected, result)
    }

    @Test
    fun `sorts lines by length descending`() {
        val input = "pear\nwatermelon\nfig"
        val expected = "watermelon\npear\nfig"
        val result = LinesSorter.sortText(input, SortType.BY_LENGTH, SortOrder.DESCENDING)
        assertEquals(expected, result)
    }

    @Test
    fun `shuffles lines returns all lines`() {
        val input = "a\nb\nc\nd\ne"
        val result = LinesSorter.sortText(input, SortType.SHUFFLE, SortOrder.ASCENDING)
        val inputLines = input.lines().sorted()
        val resultLines = result.lines().sorted()
        assertEquals(inputLines, resultLines)
    }

    @Test
    fun `sorts nested json alphabetically ascending`() {
        val input = """
                    {
                        "z": 1,
                        "a": {
                            "d": 4,
                            "b": 2,
                            "c": {
                                "y": 25,
                                "x": 24
                            }
                        },
                        "b": 3
                    }
            """.trimIndent()
        val expected = """
                    {
                        "a": {
                            "b": 2,
                            "c": {
                                "x": 24,
                                "y": 25
                            },
                            "d": 4
                        },
                        "b": 3,
                        "z": 1
                    }
            """.trimIndent()
        val result = LinesSorter.sortJson(input, SortType.ALPHABETICAL, SortOrder.ASCENDING)
        assertEquals(
            expected.replace("\\s".toRegex(), ""),
            result.replace("\\s".toRegex(), "")
        )
    }
}
