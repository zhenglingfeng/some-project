package app;

import app.web.HomeController;
import app.web.HomePage;
import app.web.LanguageManager;
import app.web.ajax.AJAXController;
import app.web.ajax.Bean;
import app.web.ajax.ErrorCodes;
import app.web.interceptor.WebInterceptor;
import core.framework.api.http.HTTPStatus;
import core.framework.module.Module;
import core.framework.web.Response;

import java.time.Duration;
import java.util.List;

import static core.framework.http.HTTPMethod.GET;
import static core.framework.http.HTTPMethod.POST;

/**
 * @author richard
 */
public class WebModule extends Module {
    @Override
    protected void initialize() {
        http().intercept(bind(WebInterceptor.class));
        http().route(GET, "/hello", request -> Response.text("hello").status(HTTPStatus.CREATED));
        http().route(GET, "/hello/", request -> Response.text("hello with trailing slash").status(HTTPStatus.CREATED));
        http().route(GET, "/hello/:name", request -> Response.text("hello " + request.pathParam("name")).status(HTTPStatus.CREATED));
        http().route(GET, "/hello-redirect", request -> Response.redirect("/hello"));

        site().staticContent("/static").cache(Duration.ofHours(1));
        site().staticContent("/favicon.ico").cache(Duration.ofHours(1));
        site().staticContent("/robots.txt");

        List<String> messages = List.of("messages/main.properties", "messages/main_en.properties", "messages/main_en_CA.properties");
        site().message(messages, "en_US", "en_CA");
        site().template("/template/home.html", HomePage.class);

        bind(LanguageManager.class);

        HomeController home = bind(HomeController.class);
        http().route(GET, "/", home::home);
        http().route(POST, "/submit", home::submit);
        http().route(GET, "/logout", home::logout);

        http().route(POST, "/ajax", bind(AJAXController.class)::ajax);
        http().bean(Bean.class);
        http().bean(ErrorCodes.class);


    }
}
