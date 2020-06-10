package regiontasks;

import com.google.common.collect.ImmutableSet;
import net.runelite.api.Skill;
import net.runelite.client.plugins.Plugin;

import static regiontasks.SkillingGoalID.*;

import java.util.Set;

public class SkillingGoal{

    private static RegionTasksPlugin plugin;

    private static final Set<SkillingGoal> SKILLING_GOALS = ImmutableSet.of(
            //Attack
            new SkillingGoal(ATTA_MITHRIL_BATTLEAXE, "Wield a Mithril battleaxe", Skill.ATTACK, 20),
            //Cooking
            new SkillingGoal(COOK_FRIED_ONIONS, "Cook Fried onions", Skill.COOKING, 42),
            //Firemaking
            new SkillingGoal(FIRE_YEW_LOGS, "Burn Yew logs", Skill.FIREMAKING, 60),
            //Fishing
            new SkillingGoal(FISH_SALMON, "Fish a Raw salmon", Skill.FISHING, 30),
            //Fletching
            new SkillingGoal(FLET_YEW_LOGS, "Fletch Yew logs", Skill.FLETCHING, 60),
            //Woodcutting
            new SkillingGoal(WOOD_YEW_LOGS, "Chop Yew logs", Skill.WOODCUTTING, 60)
    );

    private final int id;
    private final String text;
    private final Skill skill;
    private final int level;

    private SkillingGoal(int id, String text, Skill skill, int level){
        this.id = id;
        this.text = text;
        this.skill = skill;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }

    public static SkillingGoal task(int id){
        for (SkillingGoal task : SKILLING_GOALS){
            if (id == task.id){
                return task;
            }
        }
        return null;
    }

}
