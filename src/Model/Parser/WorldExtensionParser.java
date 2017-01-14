package Model.Parser;

import Control.ProjectUnknownProperties;
import Model.Physics.World.World;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing methods to parse the 'stardust .world extension' part of .world files.
 * All methods of this class are static to prevent 'Throw-away objects'
 * Created by jardu on 1/10/2017.
 */
public final class WorldExtensionParser {

    /**
     * Parses the given list of statements into game objects and adds them to the given world
     * @param statements
     * @param world
     */
    public static void parse(List<String> statements, World world){
        statements.stream()
                  .forEach((statement) -> parseStatement(statement, world));
    }

    private WorldExtensionParser(){
        throw new AssertionError();
    }

    private static void parseStatement(String statement, World world){
        Map<String, String> parameters = getParameters(statement);

        switch(parameters.get("__name")){
            case "define":
                world.addObject(DefinitionParser.constructBlock(parameters));
                break;
            case "link":
                LinkParser.performLinking(parameters, world);
                break;
            case "lever_action":
                LeverActionParser.setUpLeverAction(parameters, world);
                break;
            default:
                break;
        }
    }

    /**
     * Parses the given Statement into a {@link Map} containing all the parameters divided into keys and values.
     * The name of the statement is saved with a {@code __name} key.
     * @param statement
     * @return
     */

    private static Map<String, String> getParameters(String statement){
        Map<String, String> parameters = new HashMap<>();

        List<String> arguments = getArguments(statement);

        //The first argument is the name of the statement
        parameters.put("__name", arguments.get(0).toLowerCase());

        //Removes the first argument so it doesnt crash the following for loop
        arguments.remove(0);

        for(String argument : arguments){
            if(!argument.contains("=")){
                ProjectUnknownProperties.raiseException(new IOException("The World files has an incorrect format!"));
            }
            String[] parts = argument.split("=");

            parameters.put(parts[0], parts[1]);
        }

        return parameters;
    }

    /**
     * Parses the given statement into a list of arguments. The statement is split at every whitespace, if it is not
     * enclosed in double-quotes.
     * @param statement The statement to parse
     * @return a list of strings representing the individual arguments
     */
    private static List<String> getArguments(String statement){
        List<String> arguments = new ArrayList<>();

        int begin = 0;
        int quote_count = 0;

        statement.replaceAll("\\s+", " ");

        for(int i = 0; i < statement.length(); ++i){
            if(statement.charAt(i) == '"'){
                quote_count++;
            }else if(Character.isSpaceChar(statement.charAt(i)) && quote_count % 2 == 0){
                arguments.add(statement.substring(begin, i));
                begin = i + 1;
            }
        }

        arguments.add(statement.substring(begin, statement.length()));

        return arguments;
    }
}
