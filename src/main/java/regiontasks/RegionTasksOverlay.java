package regiontasks;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import javax.swing.text.GapContent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegionTasksOverlay extends Overlay {

    private RegionTasksPlugin plugin;

    private PanelComponent panelComponent = new PanelComponent();

    private static final String UNICODE_CHECK_MARK = "\u2713";
    private static final String UNICODE_BALLOT_X = "\u2717";

    private ArrayList<Integer> skillingGoals;
    private ArrayList<Integer> questGoals;

    @Inject
    public RegionTasksOverlay(RegionTasksPlugin plugin) {
        super(plugin);
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
        skillingGoals = plugin.activeMapSquare.getSkillingGoals();
        questGoals = plugin.activeMapSquare.getQuestGoals();
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        panelComponent.getChildren().clear();

        panelComponent.getChildren().add(TitleComponent.builder()
                .text(plugin.activeMapSquare.getLocationName()).color(Color.WHITE)
                .build());

        if (skillingGoals != null) {

            panelComponent.getChildren().add(LineComponent.builder().build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Skilling Tasks").leftColor(Color.lightGray)
                    .build());

            for (int id : skillingGoals) {
                SkillingGoal task = SkillingGoal.task(id);
                panelComponent.getChildren().add(LineComponent.builder()
                        .left(task.getText()).leftColor(Color.GRAY)
                        .build());
            }
        }

        if (questGoals != null){

            panelComponent.getChildren().add(LineComponent.builder().build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("Quest Tasks").leftColor(Color.lightGray)
                    .build());

            for (int id: questGoals) {
                QuestGoal task = QuestGoal.task(id);
                panelComponent.getChildren().add(LineComponent.builder()
                        .left(task.getText()).leftColor(Color.GRAY)
                        .build());
            }
        }

        Dimension panelSize = new Dimension(150, 0);
        panelComponent.setPreferredSize(panelSize);

        return panelComponent.render(graphics);
    }
}
