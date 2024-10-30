package com.github.antonerofeev.linesorterintellijplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.ui.Messages
import java.util.*
import java.util.stream.Collectors

class SortLinesAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)

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

            val sortedText = Arrays
                    .stream(textToSort.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                    .sorted { obj: String, str: String? -> obj.compareTo(str!!, ignoreCase = true) }
                    .collect(Collectors.joining("\n"))

            WriteCommandAction.runWriteCommandAction(project) { document.replaceString(start, end, sortedText) }
            editor.selectionModel.removeSelection()
        } else {
            Messages.showMessageDialog(project, "Editor not found", "Error", Messages.getErrorIcon())
        }
    }
}