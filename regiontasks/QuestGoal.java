package regiontasks;

import com.google.common.collect.ImmutableSet;
import net.runelite.api.Quest;
import net.runelite.api.Skill;

import static regiontasks.ItemSourceSet.*;
import static regiontasks.QuestGoalID.*;

import java.util.*;

public class QuestGoal extends RegionGoal{

    private static RegionTasksPlugin plugin;

    private static final Set<QuestGoal> QUEST_GOALS = ImmutableSet.of(
            new QuestGoal(COOKS_ASSISTANT_START, "Start the Quest", Quest.COOKS_ASSISTANT, 0),
            new QuestGoal(COOKS_ASSISTANT_MILK, "Bring the Cook a bucket of milk", Quest.COOKS_ASSISTANT, 1, null, null, new ArrayList<>(Arrays.asList(DAIRY_COW))),
            new QuestGoal(COOKS_ASSISTANT_EGG, "Bring the Cook an egg", Quest.COOKS_ASSISTANT, 1, null, null, new ArrayList<>(Arrays.asList(EGG))),
            new QuestGoal(COOKS_ASSISTANT_FLOUR, "Bring the Cook a pot of flour", Quest.COOKS_ASSISTANT, 1, null, null, new ArrayList<>(Arrays.asList(WINDMILL))),

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
    private ArrayList<Quest> questRequirements = new ArrayList<>();
    private ArrayList<Skill> skillRequirements = new ArrayList<>();
    private ArrayList<Integer> itemRequirements = new ArrayList<>();

    private QuestGoal(int id, String text, Quest quest, int step){
        super(plugin);
        this.id = id;
        this.text = text;
        this.quest = quest;
        this.step = step;
    }

    private QuestGoal(int id, String text, Quest quest, int step, ArrayList<Quest> questRequirements, ArrayList<Skill> skillRequirements, ArrayList<Integer> itemRequirements){
        super(plugin);
        this.id = id;
        this.text = text;
        this.quest = quest;
        this.step = step;
        if (questRequirements != null) {
            this.questRequirements = questRequirements; }
        if (skillRequirements != null) {
            this.skillRequirements = skillRequirements; }
        if (itemRequirements != null) {
            this.itemRequirements = itemRequirements; }
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

    public ArrayList<Quest> getQuestRequirements() {
        return questRequirements;
    }

    public ArrayList<Skill> getSkillRequirements() {
        return skillRequirements;
    }

    public ArrayList<Integer> getItemRequirements() {
        return itemRequirements;
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
