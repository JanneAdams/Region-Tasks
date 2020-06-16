package regiontasks;

import net.runelite.client.config.*;
import regionlocker.RegionLockerPlugin;

import java.awt.*;

@ConfigGroup(RegionTasksPlugin.CONFIG_KEY)
public interface RegionTasksConfig extends Config {

    @ConfigItem(
            keyName = "showSkillingTasks",
            name = "Show available skilling tasks",
            description = "",
            position = 0
    )
    default boolean showSkillingTasks()
    {
        return true;
    }

    @ConfigItem(
            keyName = "showItemTasks",
            name = "Show available item tasks",
            description = "",
            position = 1
    )
    default boolean showItemTasks()
    {
        return true;
    }

    @ConfigItem(
            keyName = "showQuestTasks",
            name = "Show available quest tasks",
            description = "",
            position = 2
    )
    default boolean showQuestTasks()
    {
        return true;
    }

    @ConfigItem(
            keyName = "showDiaryTasks",
            name = "Show available diary tasks",
            description = "",
            position = 3
    )
    default boolean showDiaryTasks()
    {
        return true;
    }
}
