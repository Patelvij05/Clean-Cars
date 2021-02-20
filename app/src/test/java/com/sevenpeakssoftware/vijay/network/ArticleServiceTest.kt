package com.sevenpeakssoftware.vijay.network

import com.sevenpeakssoftware.vijay.ZoneDateTimeProvider
import com.sevenpeakssoftware.vijay.ZoneDateTimeProvider.loadTimeZone
import com.sevenpeakssoftware.vijay.data.source.remote.ArticleService
import com.sevenpeakssoftware.vijay.domain.model.Articles
import com.sevenpeakssoftware.vijay.domain.model.adapter.ZonedDateTimeAdapter
import com.squareup.moshi.Moshi
import io.reactivex.rxjava3.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
class ArticleServiceTest {

    private lateinit var service: ArticleService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun init() {
        ZoneDateTimeProvider.loadTimeZone()

        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(ZonedDateTimeAdapter())
            .build()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ArticleService::class.java)

    }

    @After
    fun cleanup() {
        mockWebServer.shutdown()
    }

    @Test
    fun getArticlesList() {
        enqueueResponse("articles.json")

        val testObserver = TestObserver<Articles>()
        service.getArticlesList().subscribe(testObserver)

        testObserver.await()
            .assertValue {
                return@assertValue it.content[0].id == 119302L
            }
            .assertValue {
                return@assertValue it.content[0].title == "Q7 - Greatness starts, when you don't stop."
            }
            .assertValue {
                return@assertValue it.content[0].ingress == "The Audi Q7 is the result of an ambitious idea: never cease to improve."
            }
            .assertValue {
                return@assertValue it.content[0].image == "https://www.apphusetreach.no/sites/default/files/audi_q7.jpg"
            }
            .assertComplete()
            .assertNoErrors()

        val takeRequest = mockWebServer.takeRequest()
        assertThat(takeRequest.path, `is`("/article/get_articles_list"))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()

        for((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }

        mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
    }

}