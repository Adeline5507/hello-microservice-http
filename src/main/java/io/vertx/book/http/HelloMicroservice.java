package io.vertx.book.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class HelloMicroservice extends AbstractVerticle {

    @Override
    public void start() {
        //vertx.createHttpServer().requestHandler(req -> req.response().end("hello")).listen(8080);
        
//        Router router = Router.router(vertx);
//        router.get("/").handler(req -> req.response().end("Hello"));
//        router.get("/:name").handler(req -> req.response().end("Hellow "+req.pathParam("name")));
        //vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        
        Router router = Router.router(vertx);
        router.get("/").handler(this::hello);
        router.get("/:name").handler(this::hello);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
    
    private void hello(RoutingContext rc){
        String message = "Hello";
        if(rc.pathParam("name") != null){
            message += " "+rc.pathParam("name");
        }
        JsonObject json = new JsonObject().put("greeting", message);
        rc.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json").end(json.encode());
    }
}
