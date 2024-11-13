package com.github.antonerofeev.linesorterintellijplugin.actions

import com.github.antonerofeev.linesorterintellijplugin.enums.SortOrder
import com.github.antonerofeev.linesorterintellijplugin.enums.SortType
import com.github.antonerofeev.linesorterintellijplugin.utils.LinesSorter
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class SortLinesAlphabeticallyDescendingAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)

        LinesSorter.sort(project, editor, SortType.ALPHABETICAL, SortOrder.DESCENDING)
    }
}