package regiontasks;

import com.google.common.collect.ImmutableSet;

import net.runelite.api.Quest;
import net.runelite.api.Skill;

import static regiontasks.ItemSourceSet.*;
import static regiontasks.DiaryGoalID.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class DiaryGoal{

    private static RegionTasksPlugin plugin;

    private static final Set<DiaryGoal> DIARY_GOALS = ImmutableSet.of(
            new DiaryGoal(LUMB_EASY_5, "Learn your age from Hans in Lumbridge"),
            new DiaryGoal(LUMB_EASY_6, "Pickpocket a man or woman in Lumbridge"),
            new DiaryGoal(LUMB_EASY_7, "Chop and burn some oak logs in Lumbridge"),
            new DiaryGoal(LUMB_EASY_10, "Bake some Bread on the Lumbridge kitchen range"),
            new DiaryGoal(LUMB_MEDIUM_5, "Cast the teleport to Lumbridge spell"),
            new DiaryGoal(LUMB_MEDIUM_6, "Catch some Salmon in Lumbridge")

    );

    private int id;
    private String text;
    private boolean questRequirementsComplete = true;
    private boolean skillRequirementsComplete = true;
    private boolean itemRequirementsComplete = true;

    private DiaryGoal(int id, String text){
        this.id = id;
        this.text = text;
    }

    private DiaryGoal(int id, String text, ArrayList<Quest> questRequirements, ArrayList<Skill> skillRequirements, ArrayList<Integer> itemRequirements){
        this.id = id;
        this.text = text;
        if (questRequirements != null){ this.questRequirementsComplete = questRequirementsCheck(questRequirements); }
        if (skillRequirements != null){ this.skillRequirementsComplete = skillRequirementsCheck(skillRequirements); }
        if (itemRequirements != null){ this.itemRequirementsComplete = itemRequirementsCheck(itemRequirements); }
    }

    public static void setPlugin(RegionTasksPlugin plugin) {
        DiaryGoal.plugin = plugin;
    }

    public String getText() {
        return text;
    }

    public static DiaryGoal task(int id){
        for (DiaryGoal task : DIARY_GOALS){
            if (id == task.id){
                return task;
            }
        }
        return null;
    }

    public boolean isSkillRequirementsComplete() {
        return skillRequirementsComplete;
    }

    public boolean isQuestRequirementsComplete() {
        return questRequirementsComplete;
    }

    public boolean isItemRequirementsComplete() {
        return itemRequirementsComplete;
    }

    private boolean questRequirementsCheck(ArrayList<Quest> questRequirements) {
        ArrayList<Quest> completedQuests = RegionTasksPlugin.getCompletedQuests();
        if (!Collections.disjoint(completedQuests, questRequirements)) {
            for (Quest quest : questRequirements) {
                if (!completedQuests.contains(quest)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean skillRequirementsCheck(ArrayList<Skill> skillRequirements) {
        ArrayList<Skill> unlockedSkills = RegionTasksPlugin.getUnlockedSkills();
        if (!Collections.disjoint(unlockedSkills, skillRequirements)) {
            for (Skill skill : skillRequirements) {
                if (!unlockedSkills.contains(skill)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean itemRequirementsCheck(ArrayList<Integer> itemRequirements) {
        return ItemRequirementChecker.checkIfRequiredItemsAreAvailable(itemRequirements);
    }
}
