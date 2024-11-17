# Lines Sorter Pro IntelliJ IDEA plugin

![Build](https://github.com/anton-erofeev/line-sorter-intellij-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/com.github.antonerofeev.linesorterintellijplugin)](https://plugins.jetbrains.com/plugin/25718-lines-sorter-pro)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/com.github.antonerofeev.linesorterintellijplugin.svg)](https://plugins.jetbrains.com/plugin/25718-lines-sorter-pro)

## Description
<!-- Plugin description -->
Lines Sorter Pro is a handy tool for developers that lets you quickly sort or shuffle lines within a file or just within a selected area.
The plugin adds multiple string sorting options, including alphabetical, by line length, and shuffle, to the editor's right-click Refactor menu, making it easy and intuitive to organize or mix up your content.

### **Key Features:**
- **Alphabetical Sorting:** Sort lines alphabetically. Supports both ascending (A-Z) and descending (Z-A) order.
- **Sorting by Line Length:** Sort lines based on their length. Supports both ascending (shortest to longest) and descending (longest to shortest) order.
- **Selected Lines Sorting:** If text is highlighted, the plugin sorts only the selected lines, leaving the rest of the file unchanged.
- **Full-File Sorting:** If no text is selected, all lines in the file are sorted alphabetically.
- **Shuffle Lines:** Randomly shuffle the lines in the selection or the entire file, providing a quick way to mix up content without sorting.

**Ideal for:**
Developers working with configuration files, property lists, text data, or any files where organized, readable line order improves workflow and readability.

**How to Use:**
Install the plugin and restart IntelliJ IDEA.
Right-click in the editor and select "Sort Lines" in the Refactor menu.
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "line-sorter-intellij-plugin"</kbd> >
  <kbd>Install</kbd>
  
- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/anton-erofeev/line-sorter-intellij-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
