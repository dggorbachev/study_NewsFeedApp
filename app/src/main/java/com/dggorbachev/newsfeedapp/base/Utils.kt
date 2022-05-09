package com.dggorbachev.newsfeedapp.base

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.browser.customtabs.CustomTabsIntent
import com.dggorbachev.newsfeedapp.MainActivity
import com.dggorbachev.newsfeedapp.base.functional.Either
import com.dggorbachev.newsfeedapp.feature.main_screen.domain.model.Article


inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

fun mapToList(
    oldList: List<Article>,
    newList: List<Article>
): List<Article> {
    return oldList.map { article ->
        article.copy(isFavorite = newList.map { it.url }.contains(article.url))
    }
}

fun openWeb(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    builder.setShowTitle(true)
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}