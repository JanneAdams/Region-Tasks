package regiontasks;

import com.google.common.collect.ImmutableSet;
import net.runelite.api.Quest;
import net.runelite.api.Skill;

import static regiontasks.SkillingGoalID.*;
import static regiontasks.ItemSourceSet.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class SkillingGoal extends RegionGoal{

    private static RegionTasksPlugin plugin;

    private static final Set<SkillingGoal> SKILLING_GOALS = ImmutableSet.of(
            //Attack
            new SkillingGoal(ATTA_MITHRIL_BATTLEAXE, "Wield a Mithril battleaxe", Skill.ATTACK, 20,  null, new ArrayList<>(Arrays.asList(Skill.ATTACK)),null),
            //Cooking
            new SkillingGoal(COOK_FRIED_ONIONS, "Cook Fried onions", Skill.COOKING, 42, null, new ArrayList<>(Arrays.asList(Skill.COOKING)),null),
            //Firemaking
            new SkillingGoal(FIRE_YEW_LOGS, "Burn Yew logs", Skill.FIREMAKING, 60, null, new ArrayList<>(Arrays.asList(Skill.FIREMAKING)), null),
            //Fishing
            new SkillingGoal(FISH_SALMON, "Fish a Raw salmon", Skill.FISHING, 30, null, new ArrayList<>(Arrays.asList(Skill.FISHING)), new ArrayList<>(Arrays.asList(FLY_FISHING_ROD))),
            //Fletching
            new SkillingGoal(FLET_YEW_LOGS, "Fletch Yew logs", Skill.FLETCHING, 60, null, new ArrayList<>(Arrays.asList(Skill.FLETCHING)), null),
            //Woodcutting
            new SkillingGoal(WOOD_YEW_LOGS, "Chop Yew logs", Skill.WOODCUTTING, 60, null, new ArrayList<>(Arrays.asList(Skill.WOODCUTTING)), null)
    );

    private final int id;
    private final String text;
    private final Skill skill;
    private final int level;
    private ArrayList<Quest> questRequirements = new ArrayList<>();
    private ArrayList<Skill> skillRequirements = new ArrayList<>();
    private ArrayList<Integer> itemRequirements = new ArrayList<>();

    private SkillingGoal(int id, String text, Skill skill, int level){
        super(plugin);
        this.id = id;
        this.text = text;
        this.skill = skill;
        this.level = level;
    }

    private SkillingGoal(int id, String text, Skill skill, int level, ArrayList<Quest> questRequirements, ArrayList<Skill> skillRequirements, ArrayList<Integer> itemRequirements){
        super(plugin);
        this.id = id;
        this.text = text;
        this.skill = skill;
        this.level = level;
        if (questRequirements != null) {
            this.questRequirements = questRequirements; }
        if (skillRequirements != null) {
            this.skillRequirements = skillRequirements; }
        if (itemRequirements != null) {
            this.itemRequirements = itemRequirements; }
    }

    public static void setPlugin(RegionTasksPlugin plugin) {
        SkillingGoal.plugin = plugin;
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

    public ArrayList<Quest> getQuestRequirements() {
        return questRequirements;
    }

    public ArrayList<Skill> getSkillRequirements() {
        return skillRequirements;
    }

    public ArrayList<Integer> getItemRequirements() {
        return itemRequirements;
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
