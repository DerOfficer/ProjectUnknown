package Model.Parser;

import Control.ProjectUnknownProperties;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Utility class with methods frequently used by the Parser classes
 * Created by jardu on 1/14/2017.
 */
final class ParserUtils {
    /**
     * Gets the x position from the parameters, or 0 if not defined.
     *
     * @param parameters the parameters
     * @return the x position or 0 if none is defined
     */
    public static int getX(@NotNull Map<String, String> parameters) {
        return getInt(parameters, "x");
    }

    /**
     * Gets the y position from the parameters, or 0 if not defined.
     *
     * @param parameters the parameters
     * @return the y position or 0 if none is defined
     */
    public static int getY(@NotNull Map<String, String> parameters) {
        return getInt(parameters, "y");
    }

    /**
     * Tries to get the given parameter as an Integer. Returns 0 if it doesnt find the key, and crashes if the
     * value cant be parsed into an int
     *
     * @param parameters the parameters to search through
     * @param key        the key to search for
     * @return the value bound to the given key as an integer, or 0 if the key doesnt exist
     */
    public static int getInt(@NotNull Map<String, String> parameters, String key) {
        try {
            return Integer.parseInt(parameters.getOrDefault(key, "0"));
        } catch (NumberFormatException nfe) {
            ProjectUnknownProperties.raiseException(nfe);
        }
        //Stupid compiler strikes again
        return -6;
    }

    /**
     * Tries to get the given parameter as an Integer. Return false if it doesnt find the key
     *
     * @param parameters the parameters to search through
     * @param key        the key to search for
     * @return the value bound to the given key as a boolean, or false if the key doesnt exist
     */
    public static boolean getBoolean(@NotNull Map<String, String> parameters, String key) {
        return Boolean.parseBoolean(parameters.getOrDefault(key, "false"));
    }
}
