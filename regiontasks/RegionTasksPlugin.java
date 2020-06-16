package regiontasks;

import com.google.inject.Provides;
import goaltracker2.GoalTrackerPlugin;
import gpu.RegionGpuPlugin;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuOptionClicked;
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
        readConfig();
        overlayManager.add(regionTasksOverlay);
        ItemSourceSet.setPlugin(this);
        MapSquare.setPlugin(this);
        SkillingGoal.setPlugin(this);
        QuestGoal.setPlugin(this);
        DiaryGoal.setPlugin(this);
        GoalTrackerPlugin.setPlugin(this);
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
    public void onMenuOptionClicked(MenuOptionClicked event) {
        int activeMapSquareID = getMapSquareID();
        activeMapSquare = getMapSquareInfo(activeMapSquareID);
        unlockedMapSquares = RegionLocker.getUnlockedRegions();
        updateUnlockedSkills();
        updateCompletedQuests();
        updateStartedQuests();
    }

//    @Subscribe
//    public void onGameTick(GameTick event){
//        int activeMapSquareID = getMapSquareID();
//        activeMapSquare = getMapSquareInfo(activeMapSquareID);
//        unlockedMapSquares = RegionLocker.getUnlockedRegions();
//        updateUnlockedSkills();
//        updateCompletedQuests();
//        updateStartedQuests();
//    }

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

        unlockedSkills.clear();

        if (!unlockedSkills.contains(Skill.AGILITY) && ItemSourceSet.agilityTrainingAvailable())
            unlockedSkills.add(Skill.AGILITY);

        if (!unlockedSkills.contains(Skill.ATTACK) && ItemSourceSet.attackableNpcAvailable())
            unlockedSkills.add(Skill.ATTACK);

        if (!unlockedSkills.contains(Skill.CONSTRUCTION)
            && ItemSourceSet.estateAgentAvailable() && ItemSourceSet.rimmingtonHousePortalAvailable()
            && ((ItemSourceSet.axeAvailable() && ItemSourceSet.sawmillAvailable()) || ItemSourceSet.plankAvailable())
            && ((ItemSourceSet.furnaceAvailable() && ItemSourceSet.copperRockAvailable() && ItemSourceSet.tinRockAvailable() && ItemSourceSet.pickaxeAvailable() && ItemSourceSet.hammerAvailable() && ItemSourceSet.anvilAvailable())
            || ItemSourceSet.nailsAvailable()))
            unlockedSkills.add(Skill.CONSTRUCTION);

        if (!unlockedSkills.contains(Skill.COOKING)
                && ((ItemSourceSet.tinderboxAvailable() && ItemSourceSet.axeAvailable()) || ItemSourceSet.cookingRangeAvailable())
                && ((ItemSourceSet.smallFishingNetAvailable() && ItemSourceSet.shrimpsFishingSpotAvailable()) || ItemSourceSet.meatSourceAvailable()))
            unlockedSkills.add(Skill.COOKING);

        if (!unlockedSkills.contains(Skill.CRAFTING)
                && ((ItemSourceSet.cowAvailable() && ItemSourceSet.needleAndThreadAvailable() && ItemSourceSet.hideTannerAvailable())
                || (ItemSourceSet.spinningWheelAvailable() && ItemSourceSet.sheepAvailable() && ItemSourceSet.shearsAvailable())
                || (ItemSourceSet.clayRockAvailable() && ItemSourceSet.pickaxeAvailable() && ItemSourceSet.potteryOvenAndWheelAvailable())
                || (ItemSourceSet.furnaceAvailable() && ItemSourceSet.bucketOfSandAvailable() && ItemSourceSet.cookingRangeAvailable() && ItemSourceSet.glassblowingPipeAvailable() && ItemSourceSet.seaweedAvailable())
                || (ItemSourceSet.spinningWheelAvailable() && ItemSourceSet.flaxAvailable() && getCompletedQuests().contains(Quest.MURDER_MYSTERY))))
            unlockedSkills.add(Skill.CRAFTING);

        if (!unlockedSkills.contains(Skill.DEFENCE) && ItemSourceSet.attackableNpcAvailable())
            unlockedSkills.add(Skill.DEFENCE);

        if (!unlockedSkills.contains(Skill.FARMING) && (ItemSourceSet.alternativeFarmingTrainingAvailable()
                || (ItemSourceSet.potatoSeedsAvailable() && ItemSourceSet.allotmentPatchAvailable())))
            unlockedSkills.add(Skill.FARMING);

        if (!unlockedSkills.contains(Skill.FISHING) && ItemSourceSet.smallFishingNetAvailable() && ItemSourceSet.shrimpsFishingSpotAvailable())
            unlockedSkills.add(Skill.FISHING);

        if (!unlockedSkills.contains(Skill.FIREMAKING) && ItemSourceSet.tinderboxAvailable() && ItemSourceSet.axeAvailable())
            unlockedSkills.add(Skill.FIREMAKING);

        if (!unlockedSkills.contains(Skill.FLETCHING) && ItemSourceSet.knifeAvailable() && ItemSourceSet.axeAvailable())
            unlockedSkills.add(Skill.FLETCHING);

        if (!unlockedSkills.contains(Skill.HERBLORE) && ItemSourceSet.druidicRitualCompletable())
            unlockedSkills.add(Skill.HERBLORE);

        if (!unlockedSkills.contains(Skill.HITPOINTS) && ItemSourceSet.attackableNpcAvailable())
            unlockedSkills.add(Skill.HITPOINTS);

        if (!unlockedSkills.contains(Skill.HUNTER)
                && ((ItemSourceSet.nooseWandAvailable() && (ItemSourceSet.polarKebbitsAvailable()
                || (ItemSourceSet.naturalHistoryQuizCompletable() && (ItemSourceSet.commonKebbitsAvailable() || ItemSourceSet.feldipWeaselsAvailable()))))
                || (ItemSourceSet.birdSnareAvailable() && (ItemSourceSet.crimsonSwiftsAvailable()
                || (ItemSourceSet.naturalHistoryQuizCompletable() && (ItemSourceSet.goldenWarblersAvailable() || ItemSourceSet.copperLongtailsAvailable()))))))
            unlockedSkills.add(Skill.HUNTER);

        if (!unlockedSkills.contains(Skill.MINING) && (ItemSourceSet.pickaxeAvailable() && (ItemSourceSet.clayRockAvailable()
                || ItemSourceSet.copperRockAvailable() || ItemSourceSet.tinRockAvailable()
                || getCompletedQuests().contains(Quest.RUNE_MYSTERIES))))
            unlockedSkills.add(Skill.MINING);

        if (!unlockedSkills.contains(Skill.MAGIC) && ItemSourceSet.attackableNpcAvailable()
                && (ItemSourceSet.airAltarAvailable() && ItemSourceSet.mindAltarAvailable() && getCompletedQuests().contains(Quest.RUNE_MYSTERIES))
                || ItemSourceSet.airAndMindRuneShopAvailable())
            unlockedSkills.add(Skill.MAGIC);

        if (!unlockedSkills.contains(Skill.PRAYER) && ItemSourceSet.bonesAvailable())
            unlockedSkills.add(Skill.PRAYER);

        if (!unlockedSkills.contains(Skill.RANGED) && ItemSourceSet.attackableNpcAvailable()
            && ((ItemSourceSet.bronzeKnivesAvailable() || (unlockedSkills.contains(Skill.SMITHING) && ItemSourceSet.hammerAvailable() && ItemSourceSet.anvilAvailable()))
            || ((ItemSourceSet.bowAvailable() || (unlockedSkills.contains(Skill.FLETCHING) && unlockedSkills.contains(Skill.CRAFTING) && ItemSourceSet.flaxAvailable() && ItemSourceSet.spinningWheelAvailable()))
            && (ItemSourceSet.arrowsAvailable() || (ItemSourceSet.featherAvailable() && ItemSourceSet.hammerAvailable() && ItemSourceSet.anvilAvailable() && unlockedSkills.contains(Skill.FLETCHING) && unlockedSkills.contains(Skill.SMITHING))))
            || ((ItemSourceSet.crossBowAvailable() || (unlockedSkills.contains(Skill.FLETCHING) && unlockedSkills.contains(Skill.CRAFTING) && unlockedSkills.contains(Skill.SMITHING) && ItemSourceSet.hammerAvailable() && ItemSourceSet.anvilAvailable() && ItemSourceSet.meatSourceAvailable() && unlockedSkills.contains(Skill.COOKING)))
            && (ItemSourceSet.boltsAvailable() || (ItemSourceSet.featherAvailable() && ItemSourceSet.hammerAvailable() && ItemSourceSet.anvilAvailable() && unlockedSkills.contains(Skill.FLETCHING) && unlockedSkills.contains(Skill.SMITHING))))))
            unlockedSkills.add(Skill.RANGED);

        if (!unlockedSkills.contains(Skill.RUNECRAFT)
            && ((getCompletedQuests().contains(Quest.RUNE_MYSTERIES) && ItemSourceSet.airAltarAvailable()) || ItemSourceSet.arceuusLibraryAvailable()))
            unlockedSkills.add(Skill.RUNECRAFT);

        if (!unlockedSkills.contains(Skill.SLAYER) && ItemSourceSet.attackableNpcAvailable() && ItemSourceSet.slayerMasterAvailable())
            unlockedSkills.add(Skill.SLAYER);

        if (!unlockedSkills.contains(Skill.SMITHING) && (ItemSourceSet.furnaceAvailable() && ItemSourceSet.copperRockAvailable()
                && ItemSourceSet.tinRockAvailable() && ItemSourceSet.pickaxeAvailable()))
            unlockedSkills.add(Skill.SMITHING);

        if (!unlockedSkills.contains(Skill.STRENGTH) && ItemSourceSet.attackableNpcAvailable())
            unlockedSkills.add(Skill.STRENGTH);

        if (!unlockedSkills.contains(Skill.THIEVING) && (ItemSourceSet.thievableManAvailable() || (ItemSourceSet.otherThievingMethodAvailable()
                && getCompletedQuests().contains(Quest.FIGHT_ARENA)) || getCompletedQuests().contains(Quest.THE_FREMENNIK_TRIALS)))
            unlockedSkills.add(Skill.THIEVING);

        if (!unlockedSkills.contains(Skill.WOODCUTTING) && ItemSourceSet.axeAvailable())
            unlockedSkills.add(Skill.WOODCUTTING);
    }
}
