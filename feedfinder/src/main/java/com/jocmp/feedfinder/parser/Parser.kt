package com.jocmp.feedfinder.parser

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.net.URL

internal object Parser {
    class NoFeedFoundError : Throwable()

    // Parse as XML
    //   return result if feed is valid
    // if result is not valid, attempt to detect encoding
    // if encoding is present and encoding detection confidence is high,
    //   reparse XML
    //   return result if feed is valid
    // if result is not present, parse as JSON
    //   return result if feed is valid
    // if no result, raise a NotFeed error

    // Parser
    // - XMLFeed
    // - JSONFeed
    // - HTML
    @Throws(NoFeedFoundError::class)
    suspend fun parse(body: String, url: URL, validate: Boolean): Result {
        val xmlFeed = XMLFeed.from(url, body)

        if (xmlFeed.isValid()) {
            return Result.ParsedFeed(xmlFeed)
        }

        val document = tryHTML(url, body)

        if (document != null) {
            return Result.HTMLDocument(document)
        }

        if (validate) {
            throw NoFeedFoundError()
        }

        return Result.ParsedFeed(xmlFeed)
    }

    private fun tryHTML(url: URL, body: String): Document? {
        return try {
            return Jsoup.parse(body, url.toString())
        } catch (e: IOException) {
            null
        }
    }

    sealed class Result {
        class ParsedFeed(val feed: Feed): Result()
        class HTMLDocument(val document: Document): Result()
    }
}