package com.github.antonerofeev.linesorterintellijplugin.utils

import com.github.antonerofeev.linesorterintellijplugin.enums.SortOrder
import com.github.antonerofeev.linesorterintellijplugin.enums.SortType
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
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

            val sortedText = when (sortType) {
                SortType.ALPHABETICAL -> sortAlphabetically(textToSort, sortOrder)
                SortType.BY_LENGTH -> sortByLength(textToSort, sortOrder)
            }

            WriteCommandAction.runWriteCommandAction(project) {
                document.replaceString(start, end, sortedText)
            }
            editor.selectionModel.removeSelection()
        } else {
            Messages.showMessageDialog(project, "Editor not found", "Error", Messages.getErrorIcon())
        }
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
}
