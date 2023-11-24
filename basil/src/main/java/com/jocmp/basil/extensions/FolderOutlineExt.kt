package com.jocmp.basil.extensions

import com.jocmp.basil.Folder
import com.jocmp.basil.opml.Outline

internal val Outline.FolderOutline.asFolder: Folder
    get() {
        return Folder(title = folder.title ?: "")
    }