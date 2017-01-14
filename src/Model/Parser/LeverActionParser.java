package Model.Parser;

import Model.Physics.Block.InconsistentStateBlock;
import Model.Physics.Block.Lever;
import Model.Physics.World.World;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by jardu on 1/14/2017.
 */
final class LeverActionParser {

    public static void setUpLeverAction(Map<String, String> parameters, World world){
        String mode = parameters.get("mode");

        Lever lever = getLever(parameters, world);

        switch(mode){
            case "toggle_block":
                addToggleAction(lever, parameters, world);
                break;
            case "link_teleporter":
                addLinkAction(lever, parameters, world);
                break;
        }
    }

    private static void addToggleAction(Lever lever, Map<String, String> parameters, World world){
        InconsistentStateBlock toToggle = (InconsistentStateBlock) world.getBlockById(parameters.get("block_id"));

        Consumer<Boolean> newAction = (isOn) -> toToggle.toggleSolidity();

        appendActionToLever(lever, newAction);
    }

    private static void addLinkAction(Lever lever, Map<String, String> parameters, World world){
        Consumer<Boolean> newAction = (isOn) -> LinkParser.performLinking(parameters, world);

        appendActionToLever(lever, newAction);
    }

    private static void appendActionToLever(Lever lever, Consumer<Boolean> newAction){
        Consumer<Boolean> currentAction = lever.getOnToggle();

        if(currentAction != null){
            //Do all the already registered actions, AND THEN do the new action
            lever.setOnToggle(currentAction.andThen(newAction));
        }else{
            lever.setOnToggle(newAction);
        }
    }

    private static Lever getLever(Map<String, String> parameters, World world){
        String leverId = parameters.get("lever_id");

        return (Lever) world.getBlockById(leverId);
    }
}
