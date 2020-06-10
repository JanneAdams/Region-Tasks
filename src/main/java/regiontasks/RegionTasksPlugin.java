package regiontasks;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;
import net.runelite.api.Skill;
import net.runelite.api.events.InteractingChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import regionlocker.RegionLocker;
import regionlocker.RegionLockerConfig;
import net.runelite.client.ui.overlay.OverlayManager;
import regionlocker.RegionLockerPlugin;

import javax.inject.Inject;
import java.util.ArrayList;

@Slf4j
@PluginDescriptor(
        name = RegionTasksPlugin.PLUGIN_NAME,
        description = "Region Tasks"
)

public class RegionTasksPlugin extends Plugin {
    static final String PLUGIN_NAME = "Region Tasks";
    public static final String CONFIG_KEY = "regiontasks";

    @Inject
    private Client client;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private RegionTasksOverlay regionTasksOverlay;

    @Inject
    private RegionTasksConfig config;

    @Inject
    private RegionLocker regionLocker;

    @Provides
    RegionTasksConfig provideConfigAlso(ConfigManager configManager)
    {
        return configManager.getConfig(RegionTasksConfig.class);
    }

    @Provides
    RegionLockerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(RegionLockerConfig.class);
    }

    public MapSquare activeMapSquare;
    private ArrayList<Integer> unlockedMapSquares = RegionLocker.getUnlockedRegions();
    private static ArrayList<Quest> completedQuests = new ArrayList<>();
    private static ArrayList<Quest> startedQuests = new ArrayList<>();
    private static ArrayList<Skill> unlockedSkills = new ArrayList<>();

    private static boolean showSkillingTasks;
    private static boolean showItemTasks;
    private static boolean showQuestTasks;
    private static boolean showDiaryTasks;

    @Override
    public void startUp(){
        overlayManager.add(regionTasksOverlay);
        MapSquare.setPlugin(this);
        ItemSourceSet.setPlugin(this);
        SkillingGoal.setPlugin(this);
        QuestGoal.setPlugin(this);
        DiaryGoal.setPlugin(this);
    }

    @Override
    public void shutDown(){
        overlayManager.remove(regionTasksOverlay);
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event)
    {
        if (!event.getGroup().equals(CONFIG_KEY)) { return; }
        readConfig();
    }

    private void readConfig() {
        showSkillingTasks = config.showSkillingTasks();
        showItemTasks = config.showItemTasks();
        showQuestTasks = config.showQuestTasks();
        showDiaryTasks = config.showDiaryTasks();
    }

    @Subscribe
    public void onInteractingChanged(InteractingChanged event){
        readConfig();
        int activeMapSquareID = getMapSquareID();
        activeMapSquare = getMapSquareInfo(activeMapSquareID);
        unlockedMapSquares = RegionLocker.getUnlockedRegions();
        updateCompletedQuests();
        updateStartedQuests();
        updateUnlockedSkills();
    }

    public int getMapSquareID() {
        return Integer.parseInt(RegionLocker.getActiveRegion());
    }

    public MapSquare getMapSquareInfo(int regionID){
        return MapSquare.forId(regionID);
    }

    public MapSquare getActiveMapSquare() {
        return activeMapSquare;
    }

    public ArrayList<Integer> getUnlockedMapSquares() {
        return unlockedMapSquares;
    }

    public static boolean doShowSkillingTasks() {
        return showSkillingTasks;
    }

    public static boolean doShowItemTasks() {
        return showItemTasks;
    }

    public static boolean doShowQuestTasks() {
        return showQuestTasks;
    }

    public static boolean doShowDiaryTasks() {
        return showDiaryTasks;
    }

    public static ArrayList<Quest> getCompletedQuests() {
        return RegionTasksPlugin.completedQuests;
    }

    public void updateCompletedQuests(){
        ArrayList<Quest> completedQuests = new ArrayList<>();
        for (Quest quest : Quest.values()){
            if (quest.getState(client) == QuestState.FINISHED){
                completedQuests.add(quest);
            }
        }
        RegionTasksPlugin.completedQuests = completedQuests;
    }

    public static ArrayList<Quest> getStartedQuests() {
        return startedQuests;
    }

    public void updateStartedQuests(){
        ArrayList<Quest> startedQuests = new ArrayList<>();
        for (Quest quest : Quest.values()){
            if (quest.getState(client) == QuestState.IN_PROGRESS || quest.getState(client) == QuestState.FINISHED){
                startedQuests.add(quest);
            }
        }
        RegionTasksPlugin.startedQuests = startedQuests;
    }

    public static ArrayList<Skill> getUnlockedSkills() {
        return RegionTasksPlugin.unlockedSkills;
    }

    public void updateUnlockedSkills() {
        ArrayList<Skill> unlockedSkills = new ArrayList<>();

        for (Integer squareId : unlockedMapSquares){
            MapSquare mapSquare = MapSquare.forId(squareId);
            if (mapSquare != null) {
                for (Skill skill : mapSquare.getAvailableTrainingMethods()) {
                    if (!unlockedSkills.contains(skill)) {
                        unlockedSkills.add(skill);
                    }
                }
            }
        }
        RegionTasksPlugin.unlockedSkills = unlockedSkills;
    }
}
