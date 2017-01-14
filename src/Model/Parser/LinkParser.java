package Model.Parser;

import Model.Physics.Block.Teleporter;
import Model.Physics.World.World;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Created by jardu on 1/14/2017.
 */
final class LinkParser {
    public static void performLinking(@NotNull Map<String, String> parameters, @NotNull World world) {
        boolean twoWay = ParserUtils.getBoolean(parameters, "twoWay");

        String sourceId = parameters.get("source");
        String destinationId = parameters.get("destination");

        Teleporter t1 = (Teleporter) world.getBlockById(sourceId);
        Teleporter t2 = (Teleporter) world.getBlockById(destinationId);

        t1.link(t2);

        if (twoWay) {
            t2.link(t1);
        }
    }
}
