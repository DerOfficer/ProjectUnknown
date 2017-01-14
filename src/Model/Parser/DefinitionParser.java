package Model.Parser;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.*;

import java.io.IOException;
import java.util.Map;

/**
 * Parses a definition statement(__name = define)
 * Created by jardu on 1/14/2017.
 */
final class DefinitionParser {
    /**
     * Constructs a block from the given parameters
     *
     * @param parameters
     * @return a Block
     */
    public static AbstractBlock constructBlock(Map<String, String> parameters) {
        int x = ParserUtils.getX(parameters);
        int y = ParserUtils.getY(parameters);

        String type = parameters.get("type");
        String id = parameters.get("id");

        boolean inconsistentState = ParserUtils.getBoolean(parameters, "inconsistent");

        AbstractBlock toReturn = null;

        switch (type) {
            case "lever":
                boolean isOn = ParserUtils.getBoolean(parameters, "on");
                toReturn = new Lever(x, y, id, isOn);
                break;
            case "teleporter":
                toReturn = new Teleporter(x, y, id);
                break;
            default:
                BlockType blockType = getBlockType(type);
                if (inconsistentState) {
                    toReturn = new InconsistentStateBlock(x, y, blockType, id);
                } else {
                    toReturn = new Block(x, y, blockType, id);
                }
                break;
        }

        return toReturn;
    }

    /**
     * Gets the {@link BlockType} object which has the same name as the given String
     *
     * @param type the name to search for
     * @return the BlockType object
     */
    private static BlockType getBlockType(String type) {
        for (BlockType blockType : BlockType.values()) {
            if (blockType.toString().toLowerCase().equals(type.toLowerCase())) {
                return blockType;
            }
        }
        ProjectUnknownProperties.raiseException(new IOException("Invalid Block Type"));
        return null; //yuy good compiler
    }
}
