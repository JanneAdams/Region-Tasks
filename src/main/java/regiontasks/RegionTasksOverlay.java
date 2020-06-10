package regiontasks;

import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;
import regionlocker.RegionLockerConfig;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;

public class RegionTasksOverlay extends Overlay {

    private RegionTasksPlugin plugin;

    private PanelComponent panelComponent = new PanelComponent();

    private static final String UNICODE_CHECK_MARK = "\u2713";
    private static final String UNICODE_BALLOT_X = "\u2717";

    @Inject
    public RegionTasksOverlay(RegionTasksPlugin plugin, RegionTasksConfig config) {
        super(plugin);
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        ArrayList<Integer> skillingGoals = plugin.getActiveMapSquare().getEligibleSkillingGoals();
        ArrayList<Integer> questGoals = plugin.getActiveMapSquare().getEligibleQuestGoals();
        ArrayList<Integer> diaryGoals = plugin.getActiveMapSquare().getEligibleDiaryGoals();

        panelComponent.getChildren().clear();

        panelComponent.getChildren().add(TitleComponent.builder()
                .text(plugin.activeMapSquare.getLocationName()).color(Color.green)
                .build());

        if (!skillingGoals.isEmpty() && RegionTasksPlugin.doShowSkillingTasks()) {

            panelComponent.getChildren().add(LineComponent.builder().build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Skilling Tasks").leftColor(Color.lightGray)
                    .build());

            for (int id : skillingGoals) {
                SkillingGoal task = SkillingGoal.task(id);
                panelComponent.getChildren().add(LineComponent.builder()
                        .left("- " + task.getText()).leftColor(Color.GRAY)
                        .build());
            }
        }

        if (!questGoals.isEmpty() && RegionTasksPlugin.doShowQuestTasks()){

            panelComponent.getChildren().add(LineComponent.builder().build());

            for (int id: questGoals) {
                QuestGoal task = QuestGoal.task(id);
                panelComponent.getChildren().add(LineComponent.builder()
                        .left(task.getQuest().getName()).leftColor(Color.lightGray)
                        .build());
                panelComponent.getChildren().add(LineComponent.builder()
                        .left(task.getText()).leftColor(Color.GRAY)
                        .build());
            }
        }

        if (!diaryGoals.isEmpty() && RegionTasksPlugin.doShowDiaryTasks()){

            panelComponent.getChildren().add(LineComponent.builder().build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Diary Tasks").leftColor(Color.lightGray)
                    .build());

            for (int id: diaryGoals) {
                DiaryGoal task = DiaryGoal.task(id);
                panelComponent.getChildren().add(LineComponent.builder()
                        .left("- " + task.getText()).leftColor(Color.GRAY)
                        .build());
            }
        }

        Dimension panelSize = new Dimension(160, 0);
        panelComponent.setPreferredSize(panelSize);

        return panelComponent.render(graphics);
    }
}
