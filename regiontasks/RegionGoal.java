package regiontasks;

import net.runelite.client.plugins.Plugin;

public class RegionGoal {

    private final Plugin plugin;

    private boolean completed;

    public RegionGoal(Plugin plugin){
        this.plugin = plugin;
        this.completed = false;
    }

    public void complete(RegionGoal regionGoal){
        regionGoal.completed = true;
    }

}
