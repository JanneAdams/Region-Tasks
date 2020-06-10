package regiontasks;

import regionlocker.RegionLocker;

import java.util.List;

import static regiontasks.ItemSourceSet.*;

public class ItemRequirement {

    public static boolean checkIfRequiredItemsAreAvailable(List<Integer> itemCodes){

        for (Integer itemCode : itemCodes) {
            switch(itemCode){
                case TINDERBOX:
                    if (!ItemSourceSet.tinderboxAvailable()){ return false; }
                case LIGHT_SOURCE:
                    if (!ItemSourceSet.lightSourceAvailable()){ return false; }
                case PICKAXE:
                    if (!ItemSourceSet.pickaxeAvailable()){ return false; }
            }
        }
        return true;
    }

}
