package com.trade;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.fail;

public class PortfolioServiceTest {

    private static int port = 8080;

    private static SockJsClient sockJsClient;

    private static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
    private static final Logger logger = Logger.getLogger(PortfolioServiceTest.class);

    @BeforeClass
    public static void setup() throws Exception {
        loginAndSaveJsessionIdCookie("superadmin", "test123", headers);

        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        RestTemplateXhrTransport xhrTransport = new RestTemplateXhrTransport(new RestTemplate());
        transports.add(xhrTransport);

        sockJsClient = new SockJsClient(transports);
    }

    @Test
    public void getOptions() throws Exception {

        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<Throwable> failure = new AtomicReference<>();

        StompSessionHandler handler = new AbstractTestSessionHandler(failure) {

            @Override
            public void afterConnected(final StompSession session, StompHeaders connectedHeaders) {
                session.subscribe("/app/optionPositions", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return byte[].class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        String json = new String((byte[]) payload);
                        logger.debug("Got " + json);
                        try {
                            new JsonPathExpectationsHelper("$[0].company").assertValue(json, "Citrix Systems, Inc.");
                            new JsonPathExpectationsHelper("$[1].company").assertValue(json, "Dell Inc.");
                            new JsonPathExpectationsHelper("$[2].company").assertValue(json, "Microsoft");
                            new JsonPathExpectationsHelper("$[3].company").assertValue(json, "Oracle");
                        }
                        catch (Throwable t) {
                            failure.set(t);
                        }
                        finally {
                            session.disconnect();
                            latch.countDown();
                        }
                    }
                });
            }
        };

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.connect("http://localhost:{port}/Options/optionsPortfolio", headers, handler, port);

        if (failure.get() != null) {
            throw new AssertionError("", failure.get());
        }

        if (!latch.await(5, TimeUnit.SECONDS)) {
            fail("Portfolio positions not received");
        }
    }

    @Ignore    @Test
    public void getPositions() throws Exception {

        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<Throwable> failure = new AtomicReference<>();

        StompSessionHandler handler = new AbstractTestSessionHandler(failure) {

            @Override
            public void afterConnected(final StompSession session, StompHeaders connectedHeaders) {
                session.subscribe("/app/positions", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return byte[].class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        String json = new String((byte[]) payload);
                        logger.debug("Got " + json);
                        try {
                            new JsonPathExpectationsHelper("$[0].company").assertValue(json, "Citrix Systems, Inc.");
                            new JsonPathExpectationsHelper("$[1].company").assertValue(json, "Dell Inc.");
                            new JsonPathExpectationsHelper("$[2].company").assertValue(json, "Microsoft");
                            new JsonPathExpectationsHelper("$[3].company").assertValue(json, "Oracle");
                        }
                        catch (Throwable t) {
                            failure.set(t);
                        }
                        finally {
                            session.disconnect();
                            latch.countDown();
                        }
                    }
                });
            }
        };

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.connect("ws://localhost:{port}/Options/portfolio", headers, handler, port);

        if (failure.get() != null) {
            throw new AssertionError("", failure.get());
        }

        if (!latch.await(10, TimeUnit.SECONDS)) {
            fail("Portfolio positions not received");
        }
    }

    private static abstract class AbstractTestSessionHandler extends StompSessionHandlerAdapter {

        private final AtomicReference<Throwable> failure;


        public AbstractTestSessionHandler(AtomicReference<Throwable> failure) {
            this.failure = failure;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            logger.error("STOMP ERROR frame: " + headers.toString());
            this.failure.set(new Exception(headers.toString()));
        }

        @Override
        public void handleException(StompSession s, StompCommand c, StompHeaders h, byte[] p, Throwable ex) {
            logger.error("Handler exception", ex);
            this.failure.set(ex);
        }

        @Override
        public void handleTransportError(StompSession session, Throwable ex) {
            logger.error("Transport failure", ex);
            this.failure.set(ex);
        }
    }

    private static void loginAndSaveJsessionIdCookie(final String user, final String password,
                                                     final HttpHeaders headersToUpdate) {

        String url = "http://localhost:" + port + "/Options/loginnew";

        new RestTemplate().execute(url, HttpMethod.POST,

                request -> {
                    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                    map.add("username", user);
                    map.add("password", password);
                    new FormHttpMessageConverter().write(map, MediaType.APPLICATION_FORM_URLENCODED, request);
                },

                response -> {
                    headersToUpdate.add("Cookie", response.getHeaders().getFirst("Set-Cookie"));
                    headersToUpdate.add("user-name","paulson");
                    return null;
                });
    }

}
