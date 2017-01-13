package Model.Parser;

import Model.Physics.World.World;

import java.util.List;

/**
 * Created by jardu on 1/13/2017.
 */
public class WorldExtensionParser {

    public static void parse(List<String> statements, World world){
        statements.stream()
                .forEach((statement) -> parseStatement(statement));
    }

    private WorldExtensionParser(){
        throw new AssertionError();
    }

    private static void parseStatement(String statement){
        //TODO write this
    }
}
