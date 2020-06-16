package regiontasks;

import goaltracker2.GoalTrackerPlugin;
import gpu.RegionGpuPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import regionlocker.RegionLockerPlugin;

public class RegionTasksPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(
				RegionTasksPlugin.class,
				RegionLockerPlugin.class,
				RegionGpuPlugin.class,
				GoalTrackerPlugin.class
		);
		RuneLite.main(args);
	}
}