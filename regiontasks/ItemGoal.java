package regiontasks;

import com.google.common.collect.ImmutableSet;
import net.runelite.api.Item;
import net.runelite.client.plugins.Plugin;

import java.util.Set;

public class ItemGoal extends RegionGoal{

    private static final Set<ItemGoal> ITEM_GOALS = ImmutableSet.of(

    );
    private static RegionTasksPlugin plugin;

    private final String id;
    private final String text;
    private final Item item;
    private final String source;

    public ItemGoal(String id, String text, Item item, String source){
        super(plugin);
        this.id = id;
        this.text = text;
        this.item = item;
        this.source = source;
    }

}
