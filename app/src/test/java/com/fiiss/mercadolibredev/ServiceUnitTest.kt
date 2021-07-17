package com.fiiss.mercadolibredev

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fiiss.mercadolibredev.api.ApiHelperImpl
import com.fiiss.mercadolibredev.api.RetrofitBuilder
import junit.framework.Assert
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

@Suppress("DEPRECATION")
class ServiceUnitTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiHelper: ApiHelperImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiHelper = ApiHelperImpl(RetrofitBuilder.apiInterface)

    }

    @Test
    fun `read sample success json file`() {
        val reader = MockResponseFileReader("success_response.json")
        Assert.assertNotNull(reader.content)
    }

    @Test
    fun `fetch service and check response Code 200 returned`() {
        // Assign
        val response = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = apiHelper.getserviceListProduct("C").execute()
        // Assert
        assertEquals(response.toString().contains("200"), actualResponse.code().toString().contains("200"))
    }

    @Test
    fun `fetch service and check response success returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()
        // Act
        val  actualResponse = apiHelper.getserviceListProduct("C").execute()
        // Assert
        assertEquals(mockResponse?.let { `parse mocked JSON response`(it) }, actualResponse.body()?.site_id)
    }

    private fun `parse mocked JSON response`(mockResponse: String): String {
        val reader = JSONObject(mockResponse)
        return reader.getString("site_id")
    }

    /*Para esta prueba unitaria no pude hacer fallar el servicio ya que no conozco la firma y que campos son requeridos */
    @Test
    @Ignore
    fun `fetch service for failed response 400 returned`(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(MockResponseFileReader("failed_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = apiHelper.getserviceListProduct("todo").execute()
        // Assert
        assertEquals(response.toString().contains("400"),actualResponse.toString().contains("400"))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}