package com.github.antonerofeev.linesorterintellijplugin.actions

import com.github.antonerofeev.linesorterintellijplugin.enums.SortOrder
import com.github.antonerofeev.linesorterintellijplugin.enums.SortType

class ShuffleLinesAction : BaseSortLinesAction(SortType.SHUFFLE, SortOrder.ASCENDING)