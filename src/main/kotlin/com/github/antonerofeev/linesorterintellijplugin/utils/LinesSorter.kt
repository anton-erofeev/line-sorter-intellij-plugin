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

    internal fun sortText(textToSort: String, sortType: SortType, sortOrder: SortOrder): String {
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
        val lines = text.lines().filter { it.isNotEmpty() }
        val sorted = when (sortOrder) {
            SortOrder.ASCENDING -> lines.sortedWith(String.CASE_INSENSITIVE_ORDER)
            SortOrder.DESCENDING -> lines.sortedWith(String.CASE_INSENSITIVE_ORDER.reversed())
        }
        return sorted.joinToString("\n")
    }

    /**
     * Sorts lines by their length.
     *
     * @param text The text to sort.
     * @param sortOrder Specifies the sorting order (ascending or descending).
     * @return The sorted text.
     */
    private fun sortByLength(text: String, sortOrder: SortOrder): String {
        val lines = text.lines().filter { it.isNotEmpty() }
        val sorted = when (sortOrder) {
            SortOrder.ASCENDING -> lines.sortedBy { it.length }
            SortOrder.DESCENDING -> lines.sortedByDescending { it.length }
        }
        return sorted.joinToString("\n")
    }

    /**
     * Shuffles lines randomly.
     *
     * @param text The text to shuffle.
     * @return The shuffled text.
     */
    private fun shuffle(text: String): String =
        text.lineSequence()
            .filter { it.isNotEmpty() }
            .shuffled()
            .joinToString("\n")


    
    /**
     * Sorts a JSON string according to the specified type and order. Supports recursive sorting of nested JSON objects.
     *
     * @param text The JSON string to sort.
     * @param sortType The type of sorting.
     * @param sortOrder The sorting order.
     * @return The sorted JSON string, or the original text if parsing fails.
     */
    internal fun sortJson(text: String, sortType: SortType, sortOrder: SortOrder): String {
        return try {
            val json = Json { prettyPrint = true }
            val jsonObject = json.parseToJsonElement(text) as JsonObject

            /**
             * Recursively sorts a JsonObject according to the specified type and order.
             *
             * @param obj The JsonObject to sort.
             * @return A new, sorted JsonObject.
             */
            fun sortJsonObject(obj: JsonObject): JsonObject {
                val sortedEntries = when (sortType) {
                    SortType.ALPHABETICAL -> obj.entries.sortedBy { it.key }
                        .let { if (sortOrder == SortOrder.DESCENDING) it.reversed() else it }
                    SortType.BY_LENGTH -> obj.entries.sortedBy { (_, value) ->
                        (value as? JsonPrimitive)?.content?.length ?: 0
                    }.let { if (sortOrder == SortOrder.DESCENDING) it.reversed() else it }
                    SortType.SHUFFLE -> obj.entries.shuffled()
                }
                return JsonObject(
                    sortedEntries.associate { (k, v) ->
                        k to if (v is JsonObject) sortJsonObject(v) else v
                    }
                )
            }

            val sortedJsonObject = sortJsonObject(jsonObject)
            json.encodeToString(JsonObject.serializer(), sortedJsonObject)
        } catch (e: Exception) {
            Messages.showMessageDialog(null, "Invalid JSON format", "Error", Messages.getErrorIcon())
            text
        }
    }
}
