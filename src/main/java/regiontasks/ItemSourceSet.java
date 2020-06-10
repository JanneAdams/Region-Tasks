package regiontasks;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import jdk.nashorn.internal.ir.annotations.Immutable;
import net.runelite.api.Quest;
import net.runelite.api.Skill;
import net.runelite.client.config.ConfigManager;
import regionlocker.RegionLocker;
import regionlocker.RegionLockerConfig;
import regiontasks.RegionTasksPlugin;

import javax.inject.Inject;
import javax.swing.plaf.synth.Region;
import java.util.*;

public class ItemSourceSet {

    private static RegionTasksPlugin plugin;

    public static void setPlugin(RegionTasksPlugin plugin) {
        ItemSourceSet.plugin = plugin;
    }
    //09.06.2020
    public static final int TINDERBOX = 1;

    private static final Set<Integer> TINDERBOX_SQUARES = ImmutableSet.of(
            12340, 13623, 4921, 6202, 10291, 12085, 12084, 11051, 6200, 11310,
            13105, 11061, 6968, 9011, 14646, 12850, 11575, 11826, 10031, 9779,
            7226, 13106, 12089, 11569, 12853, 14907, 13613, 10545, 11828, 11056,
            8253, 10033, 10288, 9265, 10537, 13875, 12342, 13878, 12594, 9775,
            13618, 10292, 12338, 12339, 12854, 12601, 12341, 5943
    );

    private static final Set<Quest> TINDERBOX_QUESTS = ImmutableSet.of(
            Quest.IN_AID_OF_THE_MYREQUE, Quest.REGICIDE, Quest.THE_GIANT_DWARF,
            Quest.DEATH_TO_THE_DORGESHUUN, Quest.SINS_OF_THE_FATHER, Quest.HORROR_FROM_THE_DEEP,
            Quest.THE_FREMENNIK_TRIALS, Quest.LOST_CITY
    );

    public static boolean tinderboxAvailable() {

        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();
        ArrayList<Quest> startedQuests = RegionTasksPlugin.getStartedQuests();
        ArrayList<Skill> unlockedSkills = RegionTasksPlugin.getUnlockedSkills();

        if (!Collections.disjoint(unlockedRegions, TINDERBOX_SQUARES)) {
            for (Integer squareId : TINDERBOX_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 12085 && !unlockedRegions.contains(12086)) {
                        continue;
                    }
                    if (squareId == 14646 && !completedQuests.contains(Quest.THE_RESTLESS_GHOST)) {
                        continue;
                    }
                    if (squareId == 10031 && !completedQuests.contains(Quest.WATCHTOWER)) {
                        continue;
                    }
                    if (squareId == 9265 && !startedQuests.contains(Quest.MOURNINGS_END_PART_I)) {
                        continue;
                    }
                    if (squareId == 10292 && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    if (squareId == 9779 && !unlockedRegions.contains(10035) && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    if (squareId == 9775 && !unlockedSkills.contains(Skill.THIEVING)) {
                        continue;
                    }
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)){
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, TINDERBOX_QUESTS);
    }

    //09.06.2020
    public static final int LIGHT_SOURCE = 2;

    private static final Set<Integer> LIGHT_SOURCE_SQUARES = ImmutableSet.of(
            11056, 11310, 10288, 13613, 7224, 6968, 6456, 10032, 10290, 12853,
            5941, 14899, 14900, 14644, 11316, 11061, 12341, 13618, 6462
    );

    private static final Set<Quest> LIGHT_SOURCE_QUESTS = ImmutableSet.of(
            Quest.SINS_OF_THE_FATHER, Quest.DEATH_TO_THE_DORGESHUUN, Quest.THE_GIANT_DWARF,
            Quest.HORROR_FROM_THE_DEEP, Quest.THE_FREMENNIK_TRIALS
    );

    public static boolean lightSourceAvailable() {

        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();
        ArrayList<Skill> unlockedSkills = RegionTasksPlugin.getUnlockedSkills();

        if (!Collections.disjoint(unlockedRegions, LIGHT_SOURCE_SQUARES)) {
            for (Integer squareId : LIGHT_SOURCE_SQUARES) {
                if (unlockedRegions.contains(squareId) && tinderboxAvailable()) {
                    if (squareId == 10032 && !unlockedRegions.contains(10033) && !unlockedRegions.contains(10288)) {
                        continue;
                    }
                    if (squareId == 14644 && !unlockedRegions.contains(14645) && !unlockedRegions.contains(14643)) {
                        continue;
                    }
                    if (squareId == 11316 && !unlockedSkills.contains(Skill.THIEVING)){
                        continue;
                    }
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)){
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, LIGHT_SOURCE_QUESTS) && tinderboxAvailable();
    }

    //09.06.2020
    public static final int PICKAXE = 3;

    private static final Set<Integer> PICKAXE_SQUARES = ImmutableSet.of(
            12341, 12084, 12850, 11826, 11051, 13360, 14130, 6966, 10033, 12085,
            5692, 11061, 10291, 11310, 9779, 13106, 12850, 10545, 11056, 6202,
            12089, 12086, 10287, 5946, 6203, 5947, 14645, 14900, 9775, 10810,
            10554, 10042, 12594, 4918, 7479, 7222, 7221, 6710, 6965, 6453,
            11325, 13365, 9011, 13618, 6457, 14908, 15162
    );

    private static final Set<Quest> PICKAXE_QUESTS = ImmutableSet.of(
            Quest.THE_GIANT_DWARF, Quest.FISHING_CONTEST
    );

    public static boolean pickaxeAvailable() {

        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();
        ArrayList<Quest> startedQuests = RegionTasksPlugin.getStartedQuests();
        ArrayList<Skill> unlockedSkills = RegionTasksPlugin.getUnlockedSkills();

        if (!Collections.disjoint(unlockedRegions, PICKAXE_SQUARES)) {
            for (Integer squareId : PICKAXE_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 12085 && !unlockedRegions.contains(12086)) {
                        continue;
                    }
                    if (squareId == 9779 && !unlockedRegions.contains(10035) && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    if (squareId == 9775 && !unlockedSkills.contains(Skill.THIEVING)) {
                        continue;
                    }
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, PICKAXE_QUESTS);
    }

    //10.06.2020
    public static final int FLY_FISHING_ROD = 4;

    private static final Set<Integer> FLY_FISHING_ROD_SQUARES = ImmutableSet.of(
            12082, 11310, 10803, 10044, 10300, 10553, 7226
    );

    public static boolean flyFishingRodAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, FLY_FISHING_ROD_SQUARES);
    }

    //10.06.2020
    public static final int AXE = 5;

    private static final Set<Integer> AXE_SQUARES = ImmutableSet.of(
            10554, 11057, 11828, 12089, 12850, 9275, 6200, 11310, 11056, 10537,
            6454, 9775, 11316, 12341, 10039, 6197, 12853, 14645, 14900, 13618,
            12594, 11826, 12339, 10292, 9777, 5174, 12090, 12854, 12342, 9782,
            10291, 10036, 6711, 13365, 11573, 10031, 12339, 12338, 9779, 12601,
            5943, 13875, 9265, 12591, 7226, 9011, 11061, 14388
    );

    private static final Set<Quest> AXE_QUESTS = ImmutableSet.of(
            Quest.JUNGLE_POTION, Quest.IN_AID_OF_THE_MYREQUE
    );

    public static boolean axeAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        ArrayList<Quest> startedQuests = plugin.getStartedQuests();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();

        if (!Collections.disjoint(unlockedRegions, AXE_SQUARES)) {
            for (Integer squareId : AXE_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 6454 && !unlockedRegions.contains(6967)) {
                        continue;
                    }
                    if (squareId == 10036 && !completedQuests.contains(Quest.BIOHAZARD)) {
                        continue;
                    }
                    if (squareId == 10031 && !completedQuests.contains(Quest.WATCHTOWER)) {
                        continue;
                    }
                    if (squareId == 13365 && !completedQuests.contains(Quest.THE_DIG_SITE)) {
                        continue;
                    }
                    if (squareId == 9265 && !startedQuests.contains(Quest.MOURNINGS_END_PART_I)) {
                        continue;
                    }
                    if (squareId == 9779 && !unlockedRegions.contains(10035) && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    if (squareId == 9775 && !unlockedSkills.contains(Skill.THIEVING)) {
                        continue;
                    }
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, PICKAXE_QUESTS);
    }

    public static final int KNIFE = 6;

    private static final Set<Integer> KNIFE_SQUARES = ImmutableSet.of(
            10554, 12850, 10806, 10035, 11317, 11569, 10805, 12349, 14386, 10291,
            14900, 12853, 12854, 13623, 10288, 5432, 5178, 14645, 14900, 9775,
            13618, 11313, 11567, 11566, 11312, 12594, 12596, 12081, 7225, 12342,
            9782, 10547, 9012, 9275, 13106, 13104, 6968, 12590, 11575, 10031,
            11056, 10545, 13878, 11058, 11825, 14646, 14637, 8496, 8500
    );

    private static final Set<Quest> KNIFE_QUESTS = ImmutableSet.of(
            Quest.WATCHTOWER, Quest.SWAN_SONG, Quest.HORROR_FROM_THE_DEEP,
            Quest.SINS_OF_THE_FATHER
    );

    public static boolean knifeAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        ArrayList<Quest> startedQuests = plugin.getStartedQuests();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();

        if (!Collections.disjoint(unlockedRegions, KNIFE_SQUARES)) {
            for (Integer squareId : KNIFE_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 11317 && !unlockedRegions.contains(11061)) {
                        continue;
                    }
                    if (squareId == 14900 && !unlockedRegions.contains(14899)) {
                        continue;
                    }
                    if (squareId == 5432 && !unlockedRegions.contains(5176) && !unlockedRegions.contains(5688) && !unlockedRegions.contains(5433)) {
                        continue;
                    }
                    if (squareId == 11825 && !unlockedRegions.contains(11569) && !unlockedRegions.contains(12082)) {
                        continue;
                    }
                    if (squareId == 9775 && !unlockedSkills.contains(Skill.THIEVING)) {
                        continue;
                    }
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    if (squareId == 10035 && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    if (squareId == 10031 && !completedQuests.contains(Quest.WATCHTOWER)) {
                        continue;
                    }
                    if (squareId == 12349 && !completedQuests.contains(Quest.THE_MAGE_ARENA)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(unlockedRegions, KNIFE_SQUARES);
    }
}
