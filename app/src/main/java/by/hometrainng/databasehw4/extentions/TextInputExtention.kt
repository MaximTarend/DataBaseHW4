package by.hometrainng.databasehw4.extentions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getTextOrSetError(): String? {
    return editText?.text?.toString()
        ?.takeIf { it.isNotBlank() }
        .also { text ->
            error = if (text.isNullOrBlank()) {
                "Empty field"
            } else {
                null
            }
        }
}