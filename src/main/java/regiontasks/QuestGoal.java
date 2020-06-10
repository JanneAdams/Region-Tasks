package regiontasks;

import com.google.common.collect.ImmutableSet;
import net.runelite.api.Quest;
import net.runelite.api.Skill;
import net.runelite.client.plugins.Plugin;

import static regiontasks.ItemSourceSet.*;

import javax.inject.Inject;
import java.util.*;

import static regiontasks.QuestGoalID.*;

public class QuestGoal{

    private static RegionTasksPlugin plugin;

    private static final Set<QuestGoal> QUEST_GOALS = ImmutableSet.of(
            new QuestGoal(COOKS_ASSISTANT_START, "Start the Quest", Quest.COOKS_ASSISTANT, 0),

            new QuestGoal(THE_LOST_TRIBE_START, "Show the Brooch to the Duke", Quest.THE_LOST_TRIBE, 0,
                    new ArrayList<>(Arrays.asList(Quest.GOBLIN_DIPLOMACY, Quest.RUNE_MYSTERIES)),
                    new ArrayList<>(Arrays.asList(Skill.AGILITY, Skill.THIEVING, Skill.MINING)),
                    new ArrayList<>(Arrays.asList(PICKAXE, LIGHT_SOURCE))),

            new QuestGoal(THE_RESTLESS_GHOST_START, "Start the Quest", Quest.THE_RESTLESS_GHOST, 0),

            new QuestGoal(RUNE_MYSTERIES_START, "Start the Quest", Quest.RUNE_MYSTERIES, 0),

            new QuestGoal(X_MARKS_THE_SPOT_START, "Obtain a Mysterious Orb", Quest.X_MARKS_THE_SPOT, 0)
    );

    private final int id;
    private final String text;
    private final Quest quest;
    private final int step;
    private boolean questRequirementsComplete;
    private boolean skillRequirementsComplete;
    private boolean itemRequirementsComplete;

    private QuestGoal(int id, String text, Quest quest, int step){
        this.id = id;
        this.text = text;
        this.quest = quest;
        this.step = step;
    }

    private QuestGoal(int id, String text, Quest quest, int step, ArrayList<Quest> questRequirements, ArrayList<Skill> skillRequirements, ArrayList<Integer> itemRequirements){
        this.id = id;
        this.text = text;
        this.quest = quest;
        this.step = step;
        this.questRequirementsComplete = questRequirementsCheck(questRequirements);
        this.skillRequirementsComplete = skillRequirementsCheck(skillRequirements);
        this.itemRequirementsComplete = itemRequirementsCheck(itemRequirements);
    }

    public static void setPlugin(RegionTasksPlugin plugin) {
        QuestGoal.plugin = plugin;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Quest getQuest() {
        return quest;
    }

    public int getStep() {
        return step;
    }

    private boolean questRequirementsCheck(ArrayList<Quest> questRequirements) {
        return true;
    }

    private boolean skillRequirementsCheck(ArrayList<Skill> skillRequirements) {
        return true;
    }

    private boolean itemRequirementsCheck(ArrayList<Integer> itemRequirements) {
        return ItemRequirement.checkIfRequiredItemsAreAvailable(itemRequirements);
    }

    public static QuestGoal task(int id){
        for (QuestGoal task : QUEST_GOALS){
            if (id == task.id){
                return task;
            }
        }
        return null;
    }
}
