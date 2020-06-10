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
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import regionlocker.RegionLocker;
import regionlocker.RegionLockerConfig;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.ArrayList;

@Slf4j
@PluginDescriptor(
        name = "Region Tasks",
        description = "Region Tasks"
)

public class RegionTasksPlugin extends Plugin {

    public MapSquare activeMapSquare;

    @Inject
    private Client client;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private RegionTasksOverlay regionTasksOverlay;

    @Inject
    private RegionLocker regionLocker;

    @Provides
    RegionLockerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(RegionLockerConfig.class);
    }

    private ArrayList<Integer> unlockedMapSquares = regionLocker.getUnlockedRegions();
    private ArrayList<Quest> completedQuests;
    private ArrayList<Quest> startedQuests;
    private ArrayList<Skill> unlockedSkills;

    @Override
    public void startUp(){
        overlayManager.add(regionTasksOverlay);
        MapSquare.setPlugin(this);
        ItemSourceSet.setPlugin(this);
        QuestGoal.setPlugin(this);
    }

    @Override
    public void shutDown(){
        overlayManager.remove(regionTasksOverlay);
    }

    @Subscribe
    public void onInteractingChanged(InteractingChanged event){
        int activeMapSquareID = getMapSquareID();
        activeMapSquare = getMapSquareInfo(activeMapSquareID);
        unlockedMapSquares = regionLocker.getUnlockedRegions();
        completedQuests = updateCompletedQuests();
        startedQuests = updateStartedQuests();
        unlockedSkills = updateUnlockedSkills(activeMapSquareID,unlockedMapSquares);
    }

    public int getMapSquareID() {
        return Integer.parseInt(regionLocker.getActiveRegion());
    }

    public MapSquare getMapSquareInfo(int regionID){
        return MapSquare.forId(regionID);
    }

    public ArrayList<Integer> getUnlockedMapSquares() {
        return unlockedMapSquares;
    }

    public ArrayList<Quest> getCompletedQuests() {
        return completedQuests;
    }

    public ArrayList<Quest> updateCompletedQuests(){
        ArrayList<Quest> completedQuests = new ArrayList<>();
        for (Quest quest : Quest.values()){
            if (quest.getState(client) == QuestState.FINISHED){
                completedQuests.add(quest);
            }
        }
        return completedQuests;
    }

    public ArrayList<Quest> getStartedQuests() {
        return startedQuests;
    }

    public ArrayList<Quest> updateStartedQuests(){
        ArrayList<Quest> startedQuests = new ArrayList<>();
        for (Quest quest : Quest.values()){
            if (quest.getState(client) == QuestState.IN_PROGRESS){
                startedQuests.add(quest);
            }
        }
        return startedQuests;
    }

    public ArrayList<Skill> getUnlockedSkills() {
        return unlockedSkills;
    }

    public ArrayList<Skill> updateUnlockedSkills(int activeMapSquare, ArrayList<Integer> unlockedMapSquares) {
        unlockedMapSquares.add(activeMapSquare);

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
        return unlockedSkills;
    }
}
