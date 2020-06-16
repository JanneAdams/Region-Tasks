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

    public static final int TINDERBOX = 1;
    public static final int LIGHT_SOURCE = 2;
    public static final int PICKAXE = 3;
    public static final int FLY_FISHING_ROD = 4;
    public static final int AXE = 5;
    public static final int KNIFE = 6;
    public static final int SMALL_FISHING_NET = 7;
    public static final int SHRIMPS_FISHING_SPOT = 8;
    public static final int CLAY_ROCK = 9;
    public static final int COPPER_ROCK = 10;
    public static final int TIN_ROCK = 11;
    public static final int FURNACE = 12;
    public static final int THIEVABLE_MAN = 13;
    public static final int OTHER_THIEVING_METHOD = 14;
    public static final int CHICKEN = 15;
    public static final int EGG = 16;
    public static final int DAIRY_COW = 17;
    public static final int WINDMILL = 18;
    public static final int LEVEL1_AGILITY_OBSTACLE = 20;
    public static final int ROPE = 21;
    public static final int BONES = 25;
    public static final int ATTACKABLE_NPC = 26;
    public static final int ANVIL = 27;
    public static final int FEATHER = 28;
    public static final int FLAX = 29;
    public static final int SPINNING_WHEEL = 30;
    public static final int BOW = 31;
    public static final int CROSSBOW = 32;
    public static final int BOLTS = 33;
    public static final int ARROWS = 34;

    //12.06.2020 KEEP ADDING


    private static final Set<Integer> ATTACKABLE_NPC_SQUARES = ImmutableSet.of(
            12850
    );

    public static boolean attackableNpcAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, ATTACKABLE_NPC_SQUARES);
    }

    //12.06.2020 KEEP ADDING


    private static final Set<Integer> BONES_SQUARES = ImmutableSet.of(
            12850
    );

    public static boolean bonesAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, BONES_SQUARES);
    }

    //12.06.2020
    public static final int HAMMER = 26;

    private static final Set<Integer> HAMMER_SQUARES = ImmutableSet.of(
            10294, 10547, 11571, 6966, 4921, 11828, 13623, 12850, 12594, 11830,
            11826, 12339, 12341, 10292, 9777, 12090, 5174, 12086, 12084, 10287,
            6203, 5946, 6202, 5947, 11313, 11051, 6200, 8253, 12342, 9275,
            10033, 11575, 10537, 13875, 11056, 13105, 11061, 14646, 14907, 11310,
            12591, 10031, 7226, 9779, 12089, 11569, 11826, 14638, 13104, 6968,
            12853, 13613, 10545, 10044, 11058, 14637, 8496, 12082, 13878
    );

    private static final Set<Quest> HAMMER_QUESTS = ImmutableSet.of(
            Quest.THE_GRAND_TREE, Quest.THE_GIANT_DWARF, Quest.DEATH_TO_THE_DORGESHUUN,
            Quest.FISHING_CONTEST, Quest.SINS_OF_THE_FATHER, Quest.LOST_CITY,
            Quest.MOURNINGS_END_PART_I, Quest.THE_FREMENNIK_TRIALS, Quest.HORROR_FROM_THE_DEEP
    );

    public static boolean hammerAvailable() {

        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();
        ArrayList<Quest> startedQuests = RegionTasksPlugin.getStartedQuests();
        ArrayList<Skill> unlockedSkills = RegionTasksPlugin.getUnlockedSkills();

        if (!Collections.disjoint(unlockedRegions, HAMMER_SQUARES)) {
            for (Integer squareId : HAMMER_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10294 && !unlockedRegions.contains(10293) && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    if (squareId == 11571 && !unlockedSkills.contains(Skill.CRAFTING)) {
                        continue;
                    }
                    if (squareId == 10031 && !startedQuests.contains(Quest.WATCHTOWER)) {
                        continue;
                    }
                    if (squareId == 10044 && !startedQuests.contains(Quest.ROYAL_TROUBLE)) {
                        continue;
                    }
                    if (squareId == 9779 && !unlockedRegions.contains(10035) && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, HAMMER_QUESTS);
    }

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
                    if (squareId == 7226 && !completedQuests.contains(Quest.THE_QUEEN_OF_THIEVES)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, TINDERBOX_QUESTS);
    }

    //09.06.2020


    private static final Set<Integer> LIGHT_SOURCE_SQUARES = ImmutableSet.of(
            11056, 11310, 10288, 13613, 7224, 6968, 6456, 10032, 10290, 12853,
            5941, 14899, 14900, 14644, 11316, 11061, 12341, 13618, 6462, 12593
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


    private static final Set<Integer> PICKAXE_SQUARES = ImmutableSet.of(
            12341, 12084, 12850, 11826, 11051, 13360, 14130, 6966, 10033, 12085,
            5692, 11061, 10291, 11310, 9779, 13106, 12850, 10545, 11056, 6202,
            12089, 12086, 10287, 5946, 6203, 5947, 14645, 14900, 9775, 10810,
            10554, 10042, 12594, 4663, 7479, 7222, 7221, 6710, 6965, 6453,
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
                    if (squareId == 4663 && !unlockedRegions.contains(4919)) {
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

    //12.06.2020


    private static final Set<Integer> BOW_SQUARES = ImmutableSet.of(
            12850, 13360, 11573, 11317, 9782, 12853, 10036, 13618, 9779
    );

    public static boolean bowAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();
        ArrayList<Quest> startedQuests = RegionTasksPlugin.getStartedQuests();
        ArrayList<Skill> unlockedSkills = RegionTasksPlugin.getUnlockedSkills();

        if (!Collections.disjoint(unlockedRegions, BOW_SQUARES)) {
            for (Integer squareId : BOW_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10036 && !completedQuests.contains(Quest.BIOHAZARD)) {
                        continue;
                    }
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    if (squareId == 9779 && !unlockedRegions.contains(10035) && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    //12.06.2020


    private static final Set<Integer> CROSSBOW_SQUARES = ImmutableSet.of(
            12601, 13114, 5941, 11317, 9782, 12853, 10036
    );

    private static final Set<Quest> CROSSBOW_QUESTS = ImmutableSet.of(
            Quest.MOURNINGS_END_PART_I
    );

    public static boolean crossBowAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();

        if (!Collections.disjoint(unlockedRegions, CROSSBOW_SQUARES)) {
            for (Integer squareId : CROSSBOW_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10036 && !completedQuests.contains(Quest.BIOHAZARD)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, CROSSBOW_QUESTS);
    }

    //12.06.2020


    private static final Set<Integer> BOLTS_SQUARES = ImmutableSet.of(
            12345, 12082, 10036, 12853, 11317, 9782
    );

    private static final Set<Quest> BOLTS_QUESTS = ImmutableSet.of(
            Quest.MOURNINGS_END_PART_I
    );

    public static boolean boltsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();

        if (!Collections.disjoint(unlockedRegions, BOLTS_SQUARES)) {
            for (Integer squareId : BOLTS_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10036 && !completedQuests.contains(Quest.BIOHAZARD)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, BOLTS_QUESTS);
    }

    //12.06.2020


    private static final Set<Integer> ARROWS_SQUARES = ImmutableSet.of(
            12850, 11826, 13360, 11828, 12344, 12342, 6201, 11317, 10033, 10291,
            9779, 9782, 12853, 10036
    );

    private static final Set<Quest> ARROWS_QUESTS = ImmutableSet.of(
            Quest.MOURNINGS_END_PART_I
    );

    public static boolean arrowsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();
        ArrayList<Quest> startedQuests = RegionTasksPlugin.getStartedQuests();
        if (!Collections.disjoint(unlockedRegions, ARROWS_SQUARES)) {
            for (Integer squareId : ARROWS_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10036 && !completedQuests.contains(Quest.BIOHAZARD)) {
                        continue;
                    }
                    if (squareId == 9779 && !unlockedRegions.contains(10035) && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, ARROWS_QUESTS);
    }

    //10.06.2020


    private static final Set<Integer> FLY_FISHING_ROD_SQUARES = ImmutableSet.of(
            12082, 11310, 10803, 10044, 10300, 10553, 7226
    );

    public static boolean flyFishingRodAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, FLY_FISHING_ROD_SQUARES);
    }

    //11.06.2020


    private static final Set<Integer> AXE_SQUARES = ImmutableSet.of(
            6197, 9782, 10291, 6454, 12594, 10292, 10031, 12339, 10537, 11057,
            11826, 11828, 12850, 12851, 13365, 10554, 12854, 12338, 10039, 12853,
            5174, 9777, 6200, 12341, 9775, 9275, 11316, 11056, 6711, 11573,
            12342, 12595
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
                    if (squareId == 11057 && !unlockedRegions.contains(11313) && !unlockedRegions.contains(10547) && !unlockedRegions.contains(11570)) {
                        continue;
                    }
                    if (squareId == 11056 && !unlockedRegions.contains(11313) && !unlockedRegions.contains(10545) && !completedQuests.contains(Quest.THE_GRAND_TREE)) {
                        continue;
                    }
                    if (squareId == 10554 && !unlockedRegions.contains(10809) && !unlockedRegions.contains(10552)) {
                        continue;
                    }
                    if (squareId == 10537 && !unlockedRegions.contains(12082)) {
                        continue;
                    }
                    if (squareId == 6454 && !unlockedRegions.contains(6967)) {
                        continue;
                    }
                    if (squareId == 9777 && !unlockedRegions.contains(9776) && !unlockedRegions.contains(9778)) {
                        continue;
                    }
                    if (squareId == 9775 && !unlockedSkills.contains(Skill.THIEVING)) {
                        continue;
                    }
                    if (squareId == 10036 && !completedQuests.contains(Quest.BIOHAZARD)) {
                        continue;
                    }
                    if (squareId == 13365 && !completedQuests.contains(Quest.THE_DIG_SITE)) {
                        continue;
                    }
                    if (squareId == 10031 && !completedQuests.contains(Quest.WATCHTOWER)) {
                        continue;
                    }

                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, AXE_QUESTS);
    }

    //11.06.2020


    private static final Set<Integer> KNIFE_SQUARES = ImmutableSet.of(
            10554, 12850, 10806, 10035, 11317, 11569, 10805, 12349, 14386, 10291,
            14900, 12853, 12854, 13623, 10288, 5432, 5178, 14645, 14900, 9775,
            13618, 11313, 11567, 11566, 11312, 12594, 12596, 12081, 7225, 12342,
            9782, 10547, 9012, 9275, 13106, 13104, 6968, 12590, 11575, 10031,
            11056, 10545, 13878, 11058, 11825, 14646, 14637, 8496, 8500, 9265,
            12591, 7226, 9011, 11061
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
                    if (squareId == 9265 && !startedQuests.contains(Quest.MOURNINGS_END_PART_I)) {
                        continue;
                    }
                    if (squareId == 7226 && !completedQuests.contains(Quest.THE_QUEEN_OF_THIEVES)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, KNIFE_QUESTS);
    }

    //11.06.2020


    private static final Set<Integer> SMALL_FISHING_NET_SQUARES = ImmutableSet.of(
            10028, 11833, 12849, 12082, 7227, 10803, 11317, 10300, 10044
    );

    private static final Set<Quest> SMALL_FISHING_NET_QUESTS = ImmutableSet.of(
            Quest.SWAN_SONG, Quest.THE_FREMENNIK_TRIALS
    );

    public static boolean smallFishingNetAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        return (!Collections.disjoint(unlockedRegions, SMALL_FISHING_NET_SQUARES) || !Collections.disjoint(completedQuests, SMALL_FISHING_NET_QUESTS));
    }

    //11.06.2020


    private static final Set<Integer> SHRIMPS_FISHING_SPOT_SQUARES = ImmutableSet.of(
            12338, 12849, 11569, 11825, 12089, 13105, 10028, 11317, 11316, 10553,
            10039, 11059, 6971
    );

    public static boolean shrimpsFishingSpotAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        if (!Collections.disjoint(unlockedRegions, SHRIMPS_FISHING_SPOT_SQUARES)) {
            for (Integer squareId : SHRIMPS_FISHING_SPOT_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 6971 && !unlockedRegions.contains(6715) && !unlockedRegions.contains(6970) && !unlockedRegions.contains(7227)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    //11.06.2020


    private static final Set<Integer> CLAY_ROCK_SQUARES = ImmutableSet.of(
            13618, 12589, 11571, 12084, 12085, 10545, 9782, 6966, 9272, 10553,
            11826, 6201, 12596, 13617
    );

    private static final Set<Quest> CLAY_ROCK_QUESTS = ImmutableSet.of(
            Quest.THE_GIANT_DWARF
    );

    public static boolean clayRockAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, CLAY_ROCK_SQUARES)) {
            for (Integer squareId : CLAY_ROCK_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    if (squareId == 11571 && !unlockedSkills.contains(Skill.CRAFTING)) {
                        continue;
                    }
                    if (squareId == 12085 && !unlockedRegions.contains(12086)) {
                        continue;
                    }
                    if (squareId == 13617 && !unlockedRegions.contains(13616)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, CLAY_ROCK_QUESTS);
    }

    //11.06.2020


    private static final Set<Integer> COPPER_ROCK_SQUARES = ImmutableSet.of(
            13618, 13107, 10545, 12085, 12084, 9276, 9532, 12849, 12342, 9778,
            6966, 6970, 9272, 11826, 13108, 11572
    );

    private static final Set<Quest> COPPER_ROCK_QUESTS = ImmutableSet.of(
            Quest.THE_GIANT_DWARF
    );

    public static boolean copperRockAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        ArrayList<Quest> startedQuests = plugin.getStartedQuests();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, COPPER_ROCK_SQUARES)) {
            for (Integer squareId : COPPER_ROCK_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    if (squareId == 13103 && !startedQuests.contains(Quest.THE_TOURIST_TRAP)) {
                        continue;
                    }
                    if (squareId == 12085 && !unlockedRegions.contains(12086)) {
                        continue;
                    }
                    if (squareId == 6970 && !unlockedRegions.contains(6969)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, COPPER_ROCK_QUESTS);
    }

    //11.06.2020


    private static final Set<Integer> TIN_ROCK_SQUARES = ImmutableSet.of(
            13618, 13107, 12341, 9778, 13103, 12085, 12084, 9532, 12849, 12342,
            10545, 6966, 9531, 6970, 9272, 11826, 13108, 12596, 11572
    );

    private static final Set<Quest> TIN_ROCK_QUESTS = ImmutableSet.of(
            Quest.THE_GIANT_DWARF
    );

    public static boolean tinRockAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        ArrayList<Quest> startedQuests = plugin.getStartedQuests();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, TIN_ROCK_SQUARES)) {
            for (Integer squareId : TIN_ROCK_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 13618 && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    if (squareId == 12596 && !unlockedSkills.contains(Skill.AGILITY) && !unlockedRegions.contains(12852) && !unlockedRegions.contains(12597)) {
                        continue;
                    }
                    if (squareId == 13103 && !startedQuests.contains(Quest.THE_TOURIST_TRAP)) {
                        continue;
                    }
                    if (squareId == 12085 && !unlockedRegions.contains(12086)) {
                        continue;
                    }
                    if (squareId == 6970 && !unlockedRegions.contains(6969)) {
                        continue;
                    }
                    if (squareId == 9531 && !startedQuests.contains(Quest.THE_FREMENNIK_ISLES)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, TIN_ROCK_QUESTS);
    }

    //11.06.2020


    private static final Set<Integer> FURNACE_SQUARES = ImmutableSet.of(
            12342, 13105, 11828, 12850, 12602, 9011, 11310, 9275, 5179, 14646,
            11313, 10291, 5435, 11316, 10297, 8753, 5946
    );

    private static final Set<Quest> FURNACE_QUESTS = ImmutableSet.of(
            Quest.SWAN_SONG, Quest.IN_AID_OF_THE_MYREQUE, Quest.LOST_CITY,
            Quest.THE_GIANT_DWARF, Quest.SINS_OF_THE_FATHER
    );

    public static boolean furnaceAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, FURNACE_SQUARES)) {
            for (Integer squareId : FURNACE_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10297 && !completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, FURNACE_QUESTS);
    }

    //12.06.2020


    private static final Set<Integer> ANVIL_SQUARES = ImmutableSet.of(
            12597, 12853, 11829, 12085, 12084, 11825, 12339, 12338, 11833, 12341,
            10806, 10288, 12093, 10549, 13613, 10545, 9531, 10044, 11056, 10537,
            10036, 11575, 10297, 7226, 6201, 5947, 5434
    );

    private static final Set<Quest> ANVIL_QUESTS = ImmutableSet.of(
            Quest.SONG_OF_THE_ELVES, Quest.THE_GIANT_DWARF, Quest.SINS_OF_THE_FATHER
    );

    public static boolean anvilAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, ANVIL_SQUARES)) {
            for (Integer squareId : ANVIL_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 11829 && !completedQuests.contains(Quest.DORICS_QUEST)) {
                        continue;
                    }
                    if (squareId == 12085 && !unlockedRegions.contains(12086)) {
                        continue;
                    }
                    if (squareId == 11825 && !unlockedRegions.contains(11826) && !unlockedRegions.contains(12081)) {
                        continue;
                    }
                    if (squareId == 10036 && !unlockedRegions.contains(10035) && !completedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    if (squareId == 10297 && !unlockedRegions.contains(10553)) {
                        continue;
                    }
                    if (squareId == 6201 && !unlockedRegions.contains(6457)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, ANVIL_QUESTS);
    }

    //12.06.2020


    private static final Set<Integer> FEATHER_SQUARES = ImmutableSet.of(
            4921, 10293, 12082, 10803, 7226, 12340, 11310, 13104, 10044, 10300,
            12851, 12595, 12083, 11828, 11316, 10553, 11055, 11575, 12337, 10803,
            10549, 10807, 6712, 5942, 14391
    );

    private static final Set<Quest> FEATHER_QUESTS = ImmutableSet.of(
            Quest.THE_FREMENNIK_TRIALS
    );

    public static boolean featherAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, FEATHER_SQUARES)) {
            for (Integer squareId : FEATHER_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10293 && !unlockedSkills.contains(Skill.FISHING)) {
                        continue;
                    }
                    if (squareId == 12340 && !completedQuests.contains(Quest.ANIMAL_MAGNETISM)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, FEATHER_QUESTS);
    }

    //12.06.2020


    private static final Set<Integer> FLAX_SQUARES = ImmutableSet.of(
            9781, 10805, 10296, 10552, 8508, 8509, 5941, 6969
    );

    private static final Set<Quest> FLAX_QUESTS = ImmutableSet.of(
            Quest.MOURNINGS_END_PART_I
    );

    public static boolean flaxAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        return (!Collections.disjoint(unlockedRegions, FLAX_SQUARES) || !Collections.disjoint(completedQuests, FLAX_QUESTS));

    }

    //12.06.2020


    private static final Set<Integer> SPINNING_WHEEL_SQUARES = ImmutableSet.of(
            12850, 10806, 12341, 11571, 11827, 10297, 9275, 10803, 13099, 6455,
            14907
    );

    private static final Set<Quest> SPINNING_WHEEL_QUESTS = ImmutableSet.of(
            Quest.MOURNINGS_END_PART_I
    );

    public static boolean spinningWheelAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        if (!Collections.disjoint(unlockedRegions, SPINNING_WHEEL_SQUARES)) {
            for (Integer squareId : SPINNING_WHEEL_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 11571 && !unlockedSkills.contains(Skill.CRAFTING)) {
                        continue;
                    }
                    if (squareId == 14907 && !unlockedSkills.contains(Skill.CONSTRUCTION)) {
                        continue;
                    }
                    if (squareId == 10297 && !unlockedRegions.contains(10553)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, SPINNING_WHEEL_QUESTS);

    }

    //11.06.2020 ~~


    private static final Set<Integer> THIEVABLE_MAN_SQUARES = ImmutableSet.of(
            12850, 10546, 10291, 12594
    );

    public static boolean thievableManAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, THIEVABLE_MAN_SQUARES);
    }

    //11.06.2020~~~


    private static final Set<Integer> OTHER_THIEVING_METHOD_SQUARES = ImmutableSet.of(
            12851, 10548, 12595, 6455, 10547, 13099, 6457, 13109, 10547, 11575,
            12605, 12093, 10553
    );

    private static final Set<Quest> OTHER_THIEVING_METHOD_QUESTS = ImmutableSet.of(
            Quest.THE_GIANT_DWARF, Quest.MONKEY_MADNESS_I
    );

    public static boolean otherThievingMethodAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        return (!Collections.disjoint(unlockedRegions, OTHER_THIEVING_METHOD_SQUARES) || !Collections.disjoint(completedQuests, OTHER_THIEVING_METHOD_QUESTS));
    }

    //12.06.2020 ~~


    private static final Set<Integer> CHICKEN_SQUARES = ImmutableSet.of(
            12851, 12595, 12083, 11828, 11316, 10553, 11055, 11575, 12337, 10803,
            10549, 10807, 10044, 10300, 6712, 4921, 5942
    );

    private static final Set<Integer> RAW_CHICKEN_SHOP_SQUARES = ImmutableSet.of(
            12082, 13106, 13878, 9012
    );

    public static boolean chickenAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, CHICKEN_SQUARES);
    }

    private static final Set<Integer> COW_SQUARES = ImmutableSet.of(
            12851, 11571, 10548, 12083, 12595, 6968, 5178, 4921, 10807, 10288
    );

    private static final Set<Quest> COW_QUESTS = ImmutableSet.of(
            Quest.LOST_CITY
    );

    private static final Set<Integer> RAW_BEEF_SHOP_SQUARES = ImmutableSet.of(
            12082, 13878, 9012, 6970
    );

    public static boolean cowAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, COW_SQUARES)) {
            for (Integer squareId : COW_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 11571 && !unlockedSkills.contains(Skill.CRAFTING)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, COW_QUESTS);
    }

    private static final Set<Integer> GIANT_RAT_SQUARES = ImmutableSet.of(
            10290, 9777, 10292, 12342, 5941, 11826, 12594, 12341, 12854, 13108,
            11833, 12855
    );

    private static final Set<Integer> RAW_RAT_MEAT_SHOP_SQUARES = ImmutableSet.of(
            13878
    );

    public static boolean giantRatAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, GIANT_RAT_SQUARES);
    }

    private static final Set<Integer> BEAR_SQUARES = ImmutableSet.of(
            11830, 11316, 10286, 10033, 13108, 12856, 10808, 6200, 5942, 12854,
            9524, 10804, 5174, 11832, 12344, 12345
    );

    private static final Set<Integer> BEAR_MEAT_SHOP_SQUARES = ImmutableSet.of(
            13878
    );

    public static boolean bearAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, BEAR_SQUARES);
    }

    public static boolean druidicRitualCompletable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return (unlockedRegions.contains(11574) && unlockedRegions.contains(11573)
                && (chickenAvailable() || !Collections.disjoint(unlockedRegions, RAW_CHICKEN_SHOP_SQUARES))
                && (cowAvailable() || !Collections.disjoint(unlockedRegions, RAW_BEEF_SHOP_SQUARES))
                && (giantRatAvailable() || !Collections.disjoint(unlockedRegions, RAW_RAT_MEAT_SHOP_SQUARES))
                && (bearAvailable() || !Collections.disjoint(unlockedRegions, BEAR_MEAT_SHOP_SQUARES)));
    }

    //12.06.2020


    private static final Set<Integer> LEVEL1_AGILITY_SQUARES = ImmutableSet.of(
            9781, 11057, 9526, 10040, 13620, 12593
    );

    private static final Set<Integer> OTHER_AGILITY_SQUARES = ImmutableSet.of(
            11572, 10028, 10802, 10801, 12342, 12339
    );

    public static boolean agilityTrainingAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, LEVEL1_AGILITY_SQUARES)) {
            for (Integer squareId : LEVEL1_AGILITY_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 12593 && (!lightSourceAvailable() || !ropeAvailable())) {
                        continue;
                    }
                    return true;
                }
            }
        }
        if (!Collections.disjoint(unlockedRegions, OTHER_AGILITY_SQUARES)) {
            for (Integer squareId : OTHER_AGILITY_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 11572 && (!completedQuests.contains(Quest.RECRUITMENT_DRIVE)
                            || !completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)
                            || !completedQuests.contains(Quest.UNDERGROUND_PASS)
                            || !completedQuests.contains(Quest.ICTHLARINS_LITTLE_HELPER))) {
                        continue;
                    }
                    if (squareId == 10028 && (!completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)
                            || !completedQuests.contains(Quest.UNDERGROUND_PASS)
                            || !completedQuests.contains(Quest.ICTHLARINS_LITTLE_HELPER))) {
                        continue;
                    }
                    if (squareId == 10802 && (!completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)
                            || !completedQuests.contains(Quest.UNDERGROUND_PASS)
                            || !completedQuests.contains(Quest.ICTHLARINS_LITTLE_HELPER))) {
                        continue;
                    }
                    if (squareId == 10801 && axeAvailable() && (!completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)
                            || !completedQuests.contains(Quest.UNDERGROUND_PASS)
                            || !completedQuests.contains(Quest.ICTHLARINS_LITTLE_HELPER))) {
                        continue;
                    }
                    if (squareId == 12342 && (!completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)
                            || !completedQuests.contains(Quest.UNDERGROUND_PASS)
                            || !completedQuests.contains(Quest.ICTHLARINS_LITTLE_HELPER))) {
                        continue;
                    }
                    if (squareId == 12339 && !unlockedRegions.contains(12338)
                            && (!completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)
                            || !completedQuests.contains(Quest.UNDERGROUND_PASS)
                            || !completedQuests.contains(Quest.ICTHLARINS_LITTLE_HELPER))) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    //13.06.2020


    private static final Set<Integer> ROPE_SQUARES = ImmutableSet.of(
            10287, 11569, 5178, 11059, 11581, 10028, 12342, 12596, 12594, 12081,
            7225, 12338, 11051, 11575, 13104, 9779, 6968, 10291, 11310, 11061,
            14907, 10545, 6714, 11056, 11058, 11825, 14646, 14367, 8496, 8500
    );

    private static final Set<Quest> ROPE_QUESTS = ImmutableSet.of(
            Quest.HORROR_FROM_THE_DEEP, Quest.DEATH_TO_THE_DORGESHUUN, Quest.SINS_OF_THE_FATHER,
            Quest.THE_FREMENNIK_TRIALS, Quest.THE_GIANT_DWARF
    );

    private static boolean ropeAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        ArrayList<Quest> startedQuests = plugin.getStartedQuests();
        if (!Collections.disjoint(unlockedRegions, ROPE_SQUARES)) {
            for (Integer squareId : ROPE_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10287 && !unlockedRegions.contains(10030)) {
                        continue;
                    }
                    if (squareId == 11825 && !unlockedRegions.contains(11569)) {
                        continue;
                    }
                    if (squareId == 11575 && !unlockedSkills.contains(Skill.AGILITY) && !unlockedSkills.contains(Skill.THIEVING)) {
                        continue;
                    }
                    if (squareId == 9779 && !unlockedRegions.contains(10035) && !startedQuests.contains(Quest.PLAGUE_CITY)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, ROPE_QUESTS);
    }

    //11.06.2020
    public static final int COOKING_RANGE = 12;

    private static final Set<Integer> COOKING_RANGE_SQUARES = ImmutableSet.of( //NOT COMPLETED
            13105, 13106, 12850, 12082, 11826, 11571, 11828, 12084, 12597, 12853,
            12854, 11313, 11057
    );

    public static boolean cookingRangeAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, COOKING_RANGE_SQUARES)) {
            for (Integer squareId : COOKING_RANGE_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10297 && !completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> YAK_SQUARES = ImmutableSet.of(
            9275
    );

    private static final Set<Integer> YAK_SHOP_SQUARES = ImmutableSet.of(
            9531
    );

    private static final Set<Integer> RABBIT_SQUARES = ImmutableSet.of(
            10044, 10300, 9009, 10808, 7222
    );

    private static final Set<Integer> RABBIT_SHOP_SQUARES = ImmutableSet.of(
            11061, 11058, 11825, 10545, 14646, 14637, 8496, 12081, 8500
    );

    public static boolean rabbitShopAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        if (!Collections.disjoint(unlockedRegions, RABBIT_SHOP_SQUARES)) {
            for (Integer squareId : RABBIT_SHOP_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 11825 && !unlockedRegions.contains(11569)) {
                        continue;
                    }
                    if (squareId == 12081 && !unlockedRegions.contains(12082)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean meatSourceAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return (chickenAvailable() || !Collections.disjoint(unlockedRegions, RAW_CHICKEN_SHOP_SQUARES)
                || cowAvailable() || !Collections.disjoint(unlockedRegions, RAW_BEEF_SHOP_SQUARES)
                || giantRatAvailable() || !Collections.disjoint(unlockedRegions, RAW_RAT_MEAT_SHOP_SQUARES)
                || bearAvailable() || !Collections.disjoint(unlockedRegions, BEAR_MEAT_SHOP_SQUARES)
                || !Collections.disjoint(unlockedRegions, YAK_SQUARES) || !Collections.disjoint(unlockedRegions, YAK_SHOP_SQUARES)
                || !Collections.disjoint(unlockedRegions, RABBIT_SQUARES) || rabbitShopAvailable());
    }

    private static final Set<Integer> POTATO_SEED_SOURCE_SQUARES = ImmutableSet.of(
            12338, 10548, 12852, 6968
    );

    public static boolean potatoSeedsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        if (!Collections.disjoint(unlockedRegions, POTATO_SEED_SOURCE_SQUARES)) {
            for (Integer squareId : POTATO_SEED_SOURCE_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10548 || squareId == 12852 || squareId == 6968
                            && !unlockedSkills.contains(Skill.THIEVING)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> ALLOTMENT_PATCH_SQUARES = ImmutableSet.of(
            12083, 14391, 11062, 10548, 6967, 15148
    );

    public static boolean allotmentPatchAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, ALLOTMENT_PATCH_SQUARES);
    }

    private static final Set<Integer> ALT_FARMING_TRAINING_SQUARES = ImmutableSet.of(
            13105, 6967
    );

    public static boolean alternativeFarmingTrainingAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, ALT_FARMING_TRAINING_SQUARES)) {
            for (Integer squareId : ALT_FARMING_TRAINING_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 13105 && !completedQuests.contains(Quest.PRINCE_ALI_RESCUE)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> BRONZE_KNIVES_SHOP_SQUARES = ImmutableSet.of(
            11575
    );

    public static boolean bronzeKnivesAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        if (!Collections.disjoint(unlockedRegions, BRONZE_KNIVES_SHOP_SQUARES)) {
            for (Integer squareId : BRONZE_KNIVES_SHOP_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 11575 && !unlockedSkills.contains(Skill.THIEVING) && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> NATURAL_HISTORY_QUIZ = ImmutableSet.of(
            12853
    );

    public static boolean naturalHistoryQuizCompletable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, NATURAL_HISTORY_QUIZ);
    }

    private static final Set<Integer> POLAR_KEBBIT_SQUARES = ImmutableSet.of(
            10811
    );

    public static boolean polarKebbitsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, POLAR_KEBBIT_SQUARES);
    }

    private static final Set<Integer> COMMON_KEBBIT_SQUARES = ImmutableSet.of(
            9271
    );

    public static boolean commonKebbitsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, COMMON_KEBBIT_SQUARES);
    }

    private static final Set<Integer> FELDIP_WEASEL_SQUARES = ImmutableSet.of(
            10029
    );

    public static boolean feldipWeaselsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, FELDIP_WEASEL_SQUARES);
    }

    private static final Set<Integer> NOOSE_WAND_SQUARES = ImmutableSet.of(
            13613, 10288
    );

    public static boolean nooseWandAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, NOOSE_WAND_SQUARES);
    }

    private static final Set<Integer> CRIMSON_SWIFTS_SQUARES = ImmutableSet.of(
            10285, 4664
    );

    public static boolean crimsonSwiftsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, CRIMSON_SWIFTS_SQUARES);
    }

    private static final Set<Integer> GOLDEN_WARBLERS_SQUARES = ImmutableSet.of(
            13616
    );

    public static boolean goldenWarblersAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, GOLDEN_WARBLERS_SQUARES);
    }

    private static final Set<Integer> COPPER_LONGTAILS_SQUARES = ImmutableSet.of(
            9272, 6197
    );

    public static boolean copperLongtailsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, COPPER_LONGTAILS_SQUARES);
    }

    private static final Set<Integer> BIRD_SNARE_SQUARES = ImmutableSet.of(
            13613, 10288, 5941
    );

    public static boolean birdSnareAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, BIRD_SNARE_SQUARES);
    }

    private static final Set<Integer> HIDE_TANNER_SQUARES = ImmutableSet.of(
            13105, 10549, 13878, 6711
    );

    public static boolean hideTannerAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        if (!Collections.disjoint(unlockedRegions, HIDE_TANNER_SQUARES)) {
            for (Integer squareId : HIDE_TANNER_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10549 && !unlockedSkills.contains(Skill.RANGED)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> NEEDLE_AND_THREAD_SQUARES = ImmutableSet.of(
            12853, 9275, 11826, 8253, 6968, 13878, 13105
    );

    private static final Set<Quest> NEEDLE_AND_THREAD_QUESTS = ImmutableSet.of(
            Quest.MOURNINGS_END_PART_I, Quest.ICTHLARINS_LITTLE_HELPER, Quest.SINS_OF_THE_FATHER,
            Quest.MONKEY_MADNESS_I, Quest.SWAN_SONG, Quest.SONG_OF_THE_ELVES,
            Quest.THE_GIANT_DWARF
    );

    public static boolean needleAndThreadAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        return (!Collections.disjoint(unlockedRegions, NEEDLE_AND_THREAD_SQUARES) || !Collections.disjoint(completedQuests, NEEDLE_AND_THREAD_QUESTS));
    }

    private static final Set<Integer> SHEARS_SQUARES = ImmutableSet.of(
            12595, 6455, 6969, 12340, 7226, 12342, 12850, 13875, 12084, 12085,
            11826, 6968, 10545, 10033, 12853, 13613, 14646, 6200, 11569, 11828,
            8753, 13105, 11575, 10537, 8253, 11061, 11058, 14637, 8496
    );

    private static final Set<Quest> SHEARS_QUESTS = ImmutableSet.of(
            Quest.MOURNINGS_END_PART_I, Quest.LOST_CITY
    );

    public static boolean shearsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, SHEARS_SQUARES)) {
            for (Integer squareId : SHEARS_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 7226 && !completedQuests.contains(Quest.THE_QUEEN_OF_THIEVES)) {
                        continue;
                    }
                    if (squareId == 12085 && !unlockedRegions.contains(12086)) {
                        continue;
                    }
                    if (squareId == 12081 && !unlockedRegions.contains(12082)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, SHEARS_QUESTS);
    }

    private static final Set<Integer> SHEEP_SQUARES = ImmutableSet.of(
            12850, 12595, 10548, 12086, 6968, 11571, 6455
    );

    private static final Set<Quest> SHEEP_QUESTS = ImmutableSet.of(
            Quest.MOURNINGS_END_PART_I, Quest.HOLY_GRAIL, Quest.LOST_CITY
    );

    public static boolean sheepAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        return (!Collections.disjoint(unlockedRegions, SHEEP_SQUARES) || !Collections.disjoint(completedQuests, SHEEP_QUESTS));
    }

    private static final Set<Integer> POTTERY_OVEN_AND_WHEEL_SQUARES = ImmutableSet.of(
            10291, 12341, 10297
    );

    public static boolean potteryOvenAndWheelAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, POTTERY_OVEN_AND_WHEEL_SQUARES)) {
            for (Integer squareId : POTTERY_OVEN_AND_WHEEL_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 10297 && !completedQuests.contains(Quest.THE_FREMENNIK_TRIALS)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> BUCKET_OF_SAND_SQUARES = ImmutableSet.of(
            10032, 10553, 11316
    );

    private static final Set<Quest> BUCKET_OF_SAND_QUESTS = ImmutableSet.of(
            Quest.SONG_OF_THE_ELVES, Quest.LOST_CITY, Quest.DEATH_TO_THE_DORGESHUUN
    );

    public static boolean bucketOfSandAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        return (!Collections.disjoint(unlockedRegions, BUCKET_OF_SAND_SQUARES) || !Collections.disjoint(completedQuests, BUCKET_OF_SAND_QUESTS));
    }

    private static final Set<Integer> GLASSBLOWING_PIPE_SQUARES = ImmutableSet.of(
            11316, 10549, 11310, 11061, 11058, 11825, 12081, 10545, 14646, 14637,
            8496
    );

    private static final Set<Quest> GLASSBLOWING_PIPE_QUESTS = ImmutableSet.of(
            Quest.DEATH_TO_THE_DORGESHUUN, Quest.SWAN_SONG, Quest.SONG_OF_THE_ELVES
    );

    public static boolean glassblowingPipeAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, GLASSBLOWING_PIPE_SQUARES)) {
            for (Integer squareId : GLASSBLOWING_PIPE_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 11825 && !unlockedRegions.contains(11569)) {
                        continue;
                    }
                    if (squareId == 12081 && !unlockedRegions.contains(12082)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, GLASSBLOWING_PIPE_QUESTS);
    }

    private static final Set<Integer> SEAWEED_SQUARES = ImmutableSet.of(
            11313, 11056, 11055, 11054, 11310, 11822, 11823, 11568, 15151, 6969,
            7224, 7223, 7479, 7222, 7221, 6966, 6710, 10810, 11061, 11058, 11825,
            12081, 8496, 14646, 14637
    );

    public static boolean seaweedAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        if (!Collections.disjoint(unlockedRegions, SEAWEED_SQUARES)) {
            for (Integer squareId : SEAWEED_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 15151 && !unlockedRegions.contains(14894)) {
                        continue;
                    }
                    if (squareId == 11825 && !unlockedRegions.contains(11569)) {
                        continue;
                    }
                    if (squareId == 12081 && !unlockedRegions.contains(12082)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> AIR_ALTAR_SQUARES = ImmutableSet.of(
            11827
    );

    public static boolean airAltarAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, AIR_ALTAR_SQUARES);
    }

    private static final Set<Integer> MIND_ALTAR_SQUARES = ImmutableSet.of(
            11830
    );

    public static boolean mindAltarAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, MIND_ALTAR_SQUARES);
    }

    private static final Set<Integer> AIR_AND_MIND_RUNE_SHOP_SQUARES = ImmutableSet.of(
            12850, 6714, 12082, 12853, 12349, 12343, 10537, 13106, 11313
    );

    public static boolean airAndMindRuneShopAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, AIR_AND_MIND_RUNE_SHOP_SQUARES)) {
            for (Integer squareId : AIR_AND_MIND_RUNE_SHOP_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 12343 && !completedQuests.contains(Quest.ENTER_THE_ABYSS)) {
                        continue;
                    }
                    if (squareId == 13106 && !completedQuests.contains(Quest.THE_FEUD)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> ARCEUUS_LIBRARY_SQUARES = ImmutableSet.of(
            6459
    );

    public static boolean arceuusLibraryAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, ARCEUUS_LIBRARY_SQUARES);
    }

    private static final Set<Integer> ESTATE_AGENT_SQUARES = ImmutableSet.of(
            12854, 11828, 10806, 10547, 6968
    );

    public static boolean estateAgentAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, ESTATE_AGENT_SQUARES);
    }

    private static final Set<Integer> SAWMILL_SQUARES = ImmutableSet.of(
            6454, 13110
    );

    public static boolean sawmillAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, SAWMILL_SQUARES)) {
            for (Integer squareId : SAWMILL_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 6454 && !unlockedRegions.contains(6967)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static final Set<Integer> PLANK_SQUARES = ImmutableSet.of(
            10545, 10039, 10794, 12601, 14907, 11316, 11314, 13113, 13875
    );

    public static boolean plankAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, PLANK_SQUARES);
    }

    private static final Set<Integer> NAILS_SQUARES = ImmutableSet.of(
            7226, 6454, 13110
    );

    public static boolean nailsAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, NAILS_SQUARES);
    }

    private static final Set<Integer> RIMMINGTON_HOUSE_PORTAL_SQUARES = ImmutableSet.of(
            11826
    );

    public static boolean rimmingtonHousePortalAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        return !Collections.disjoint(unlockedRegions, RIMMINGTON_HOUSE_PORTAL_SQUARES);
    }

    private static final Set<Integer> SLAYER_MASTER_SQUARES = ImmutableSet.of(
            11575, 12342, 13878, 12854, 5179, 9525, 11310
    );

    private static final Set<Quest> SLAYER_MASTER_QUESTS = ImmutableSet.of(
            Quest.LOST_CITY
    );

    public static boolean slayerMasterAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        if (!Collections.disjoint(unlockedRegions, SLAYER_MASTER_SQUARES)) {
            for (Integer squareId : SLAYER_MASTER_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 12854 && !unlockedSkills.contains(Skill.AGILITY)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, SLAYER_MASTER_QUESTS);
    }

    private static final Set<Integer> DAIRY_COW_SQUARES = ImmutableSet.of(
            12851, 11571, 10548, 12083, 10288, 10807, 10553, 5178
    );

    private static final Set<Quest> DAIRY_COW_QUESTS = ImmutableSet.of(
            Quest.LOST_CITY
    );

    public static boolean dairyCowAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        return !Collections.disjoint(unlockedRegions, DAIRY_COW_SQUARES) || !Collections.disjoint(completedQuests, DAIRY_COW_QUESTS);
    }

    private static final Set<Integer> EGG_SQUARES = ImmutableSet.of(
            12083, 12595, 12851, 11316, 4921
    );

    private static final Set<Quest> EGG_QUESTS = ImmutableSet.of(
            Quest.LOST_CITY, Quest.THE_FREMENNIK_TRIALS
    );

    public static boolean eggAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        return !Collections.disjoint(unlockedRegions, EGG_SQUARES) || !Collections.disjoint(completedQuests, EGG_QUESTS);
    }

    private static final Set<Integer> WINDMILL_SQUARES = ImmutableSet.of(
            12595, 10548, 12597, 15148, 6967, 8757
    );

    private static final Set<Quest> WINDMILL_QUESTS = ImmutableSet.of(
            Quest.LOST_CITY
    );

    public static boolean windmillAvailable() {
        ArrayList<Integer> unlockedRegions = plugin.getUnlockedMapSquares();
        ArrayList<Quest> completedQuests = plugin.getCompletedQuests();
        ArrayList<Skill> unlockedSkills = plugin.getUnlockedSkills();
        if (!Collections.disjoint(unlockedRegions, WINDMILL_SQUARES)) {
            for (Integer squareId : WINDMILL_SQUARES) {
                if (unlockedRegions.contains(squareId)) {
                    if (squareId == 12597 && !unlockedSkills.contains(Skill.COOKING)) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return !Collections.disjoint(completedQuests, WINDMILL_QUESTS);
    }
}
