package example;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

/**
 * @author msequeira
 */
class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(Application.class);

    @Test
    void verifyModularity() {
        // --> Module model
        System.out.println(modules.toString());

        // --> Trigger verification
        modules.verify();
    }

    @Test
    void renderDocumentation() {
        var canvasOptions = Documenter.CanvasOptions.defaults();

        var docOptions = Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.C4);

        new Documenter(modules).writeDocumentation(docOptions, canvasOptions);
    }
}
