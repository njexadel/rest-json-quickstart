package org.acme.rest.engine;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.rest.engine.domain.Engine;
import org.acme.rest.engine.parser.engine.EngineParser;
import org.acme.rest.engine.request.EngineRequest;
import org.acme.rest.engine.visitor.EnginePrintVisitor;

@Path("/engine")
public class EngineResource {
    @Inject
    EnginePrintVisitor printVisitor;

    @Inject
    EngineParser engineParser;

    @POST
    public Engine create(EngineRequest engineRequest) {
        Engine engine = engineParser.toEngine(engineRequest);
        System.out.println(printVisitor.print(engine));
        return engine;
    }
}
