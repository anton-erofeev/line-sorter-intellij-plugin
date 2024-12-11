package com.github.antonerofeev.linesorterintellijplugin.utils

import com.github.antonerofeev.linesorterintellijplugin.enums.SortOrder
import com.github.antonerofeev.linesorterintellijplugin.enums.SortType
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import java.util.*
import java.util.stream.Collectors

object LinesSorter {

    /**
     * Main sorting method. Sorts lines based on the specified sort type.
     *
     * @param project The current project.
     * @param editor The current editor instance.
     * @param sortType Specifies the type of sorting (alphabetical or by length).
     * @param sortOrder Specifies the sorting order (ascending or descending).
     */
    fun sort(project: Project?, editor: Editor?, sortType: SortType, sortOrder: SortOrder) {
        if (editor != null) {
            val document = editor.document
            val fileExtension = editor.virtualFile.extension
            var start = editor.selectionModel.selectionStart
            var end = editor.selectionModel.selectionEnd

            val textToSort: String

            if (editor.selectionModel.hasSelection()) {
                textToSort = document.text.substring(start, end)
            } else {
                textToSort = document.text
                start = 0
                end = document.textLength
            }

            val sortedText = if (fileExtension == "json") {
                sortJson(textToSort, sortType, sortOrder)
            } else {
                sortText(textToSort, sortType, sortOrder)
            }
            WriteCommandAction.runWriteCommandAction(project) {
                document.replaceString(start, end, sortedText)
            }
            editor.selectionModel.removeSelection()
        } else {
            Messages.showMessageDialog(project, "Editor not found", "Error", Messages.getErrorIcon())
        }
    }

    private fun sortText(textToSort: String, sortType: SortType, sortOrder: SortOrder): String {
        val sortedText = when (sortType) {
            SortType.ALPHABETICAL -> sortAlphabetically(textToSort, sortOrder)
            SortType.BY_LENGTH -> sortByLength(textToSort, sortOrder)
            SortType.SHUFFLE -> shuffle(textToSort)
        }
        return sortedText
    }

    /**
     * Sorts lines alphabetically.
     *
     * @param text The text to sort.
     * @param sortOrder Specifies the sorting order (ascending or descending).
     * @return The sorted text.
     */
    private fun sortAlphabetically(text: String, sortOrder: SortOrder): String {
        return Arrays.stream(text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            .sorted { str1, str2 ->
                when (sortOrder) {
                    SortOrder.ASCENDING -> str1.compareTo(str2, ignoreCase = true)
                    SortOrder.DESCENDING -> str2.compareTo(str1, ignoreCase = true)
                }
            }
            .collect(Collectors.joining("\n"))
    }

    /**
     * Sorts lines by their length.
     *
     * @param text The text to sort.
     * @param sortOrder Specifies the sorting order (ascending or descending).
     * @return The sorted text.
     */
    private fun sortByLength(text: String, sortOrder: SortOrder): String {
        return Arrays.stream(text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            .sorted { str1, str2 ->
                when (sortOrder) {
                    SortOrder.ASCENDING -> str1.length.compareTo(str2.length)
                    SortOrder.DESCENDING -> str2.length.compareTo(str1.length)
                }
            }
            .collect(Collectors.joining("\n"))
    }

    /**
     * Shuffles lines randomly.
     *
     * @param text The text to shuffle.
     * @return The shuffled text.
     */
    private fun shuffle(text: String): String {
        val lines = text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toMutableList()
        lines.shuffle()
        return lines.joinToString("\n")
    }

    private fun sortJson(text: String, sortType: SortType, sortOrder: SortOrder): String {
        return try {
            val json = Json { prettyPrint = true }
            val jsonObject = json.parseToJsonElement(text) as JsonObject

            val sortedJsonObject = when (sortType) {
                SortType.ALPHABETICAL -> {
                    JsonObject(
                        jsonObject.entries.sortedBy { it.key }.let {
                            if (sortOrder == SortOrder.DESCENDING) it.reversed() else it
                        }.associate { it.key to it.value }
                    )
                }
                SortType.BY_LENGTH -> {
                    JsonObject(
                        jsonObject.entries.sortedBy { (_, value) ->
                            (value as? JsonPrimitive)?.content?.length ?: 0
                        }.let {
                            if (sortOrder == SortOrder.DESCENDING) it.reversed() else it
                        }.associate { it.key to it.value }
                    )
                }
                SortType.SHUFFLE -> {
                    JsonObject(
                        jsonObject.entries.shuffled().associate { it.key to it.value }
                    )
                }
            }

            json.encodeToString(JsonObject.serializer(), sortedJsonObject)
        } catch (e: Exception) {
            Messages.showMessageDialog(null, "Invalid JSON format", "Error", Messages.getErrorIcon())
            text
        }
    }
}
