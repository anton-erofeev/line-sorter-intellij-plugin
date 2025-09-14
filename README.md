# Lines Sorter Pro IntelliJ IDEA plugin

![Build](https://github.com/anton-erofeev/line-sorter-intellij-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/com.github.antonerofeev.linesorterintellijplugin)](https://plugins.jetbrains.com/plugin/25718-lines-sorter-pro)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/com.github.antonerofeev.linesorterintellijplugin.svg)](https://plugins.jetbrains.com/plugin/25718-lines-sorter-pro)

## Description
<!-- Plugin description -->
Lines Sorter Pro is a powerful and easy-to-use plugin for IntelliJ IDEA that helps you instantly organize your text, code, or configuration files. Whether you need to sort lines alphabetically, by length, shuffle them, or even recursively sort keys in complex JSON files — this tool does it in just a couple of clicks.

- Supports plain text, code, and JSON files
- Works with selected lines or the entire file
- Recursively sorts nested JSON objects
- Integrates seamlessly into the editor’s right-click <kbd>Refactor</kbd> menu


### 🚀 Key Features

- 🅰️ **Alphabetical Sorting**  
  Sort lines alphabetically (A-Z or Z-A).

- 🔢 **Sorting by Line Length**  
  Sort lines by their length (shortest to longest or vice versa).

- 🔀 **Shuffle Lines**  
  Randomly shuffle selected lines or the entire file.

- 📄 **Flexible Scope**  
  Works with selected text or the whole file if nothing is selected.

- 🗂️ **JSON File Sorting**  
  - Sort JSON object keys alphabetically, by value length, or shuffle.
  - Supports recursive sorting for nested JSON objects.

- ⚡ **Easy Access**  
  All features are available via the right-click <kbd>Refactor</kbd> menu in the editor.

**Ideal for:**
Developers working with configuration files, property lists, text data, or any files where organized, readable line order improves workflow and readability.

## How to Use

1. **Install the plugin** and restart IntelliJ IDEA.
2. **Open any text or JSON file** in the editor.
3. **Select the lines** you want to sort (or leave nothing selected to sort the whole file).
4. **Right-click** in the editor and choose <kbd>Refactor</kbd> → <kbd>Sort Lines...</kbd>.
5. **Pick the desired sorting option** from the menu:
   - Sort Alphabetically (A-Z or Z-A)
   - Sort by Line Length (ascending/descending)
   - Shuffle Lines
   - Sort JSON keys (alphabetically, by value length, or shuffle)

<details>
<summary>Example: Sorting JSON</summary>


**Before:**
```json
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
```

**After (alphabetical):**
```json
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
```
</details>


<details>
<summary>Example: Sorting plain text lines</summary>

**Before:**
```text
banana
apple
carrot
```

**After (alphabetical):**
```text
apple
banana
carrot
```
</details>

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
Author: [anton-erofeev](https://github.com/antonerofeev)

[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
