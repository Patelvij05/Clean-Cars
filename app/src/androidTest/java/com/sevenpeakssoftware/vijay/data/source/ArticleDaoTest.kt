package com.sevenpeakssoftware.vijay.data.source

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sevenpeakssoftware.vijay.data.source.local.ArticleDatabase
import com.sevenpeakssoftware.vijay.presentation.util.TestUtil
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    private lateinit var mDatabase: ArticleDatabase

    @Before
    fun createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, ArticleDatabase::class.java)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun isArticleListEmpty(){
        assertEquals(0, mDatabase.articleDao().getCarArticles().blockingFirst().size)
    }

    @Test
    @Throws(Exception::class)
    fun insertArticle(){
        val article = TestUtil.createCarsArticleList(7)
        val insertedArticle = mDatabase.articleDao().insertArticles(article)
        assertNotNull(insertedArticle)
    }

    @Test
    @Throws(Exception::class)
    fun retrieveCarsArticle(){
        val article = TestUtil.createCarsArticleList(5)
        mDatabase.articleDao().insertArticles(article)

        val loadedCarsArticle = mDatabase.articleDao().getCarArticles().blockingFirst()
        assertEquals(article, loadedCarsArticle)
    }

}