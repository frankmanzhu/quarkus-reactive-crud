package org.frank.web;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.frank.data.entity.Quarkus;
import org.frank.data.QuarkusRepository;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Path("/context")
public class HelloResource {

    public HelloResource(QuarkusRepository quarkusRepository) {
        this.quarkusRepository = quarkusRepository;
    }

    private QuarkusRepository quarkusRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/quarkus")
    public List<Quarkus> getQuarkus() {
        return quarkusRepository.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/quarkus/{id}")
    public Optional<Quarkus> getQuarkus(@PathParam Long id) {
        return quarkusRepository.findById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/quarkus")
    public Quarkus putQuarkus(Quarkus quarkus) {
        return quarkusRepository.saveAndFlush(quarkus);
    }

    @DELETE
    @Path("/quarkus/{id}")
    public void putQuarkus(@PathParam Long id) {
        quarkusRepository.deleteById(id);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting")
    public Uni<String> greeting() {
        return Uni.createFrom().item("Hello World!");
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.TEXT_PLAIN)
    @Path("/stream/{count}/{name}")
    public Multi<String> greetingsAsStream(@PathParam int count, @PathParam String name) {
        return greetings(count, name);
    }

    public Multi<String> greetings(int count, String name) {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().apply(n -> String.format("hello %s - %d", name, n))
                .transform().byTakingFirstItems(count);

    }
}