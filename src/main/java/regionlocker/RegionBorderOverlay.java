package regionlocker;

import com.google.inject.Inject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

public class RegionBorderOverlay extends Overlay {private static final int LOCAL_TILE_SIZE = Perspective.LOCAL_TILE_SIZE;
    private static final int CHUNK_SIZE = 8;
    private static final int MAP_SQUARE_SIZE = CHUNK_SIZE * CHUNK_SIZE; // 64
    private static final int CULL_CHUNK_BORDERS_RANGE = 16;

    private final Client client;
    private final RegionLockerConfig config;

    @Inject
    private RegionBorderOverlay(Client client, RegionLockerConfig config)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.HIGH);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.client = client;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (config.renderRegionBorders()) renderMapSquares(graphics);

        return null;
    }

    private void renderMapSquares(Graphics2D graphics)
    {
        WorldPoint wp = client.getLocalPlayer().getWorldLocation();
        int startX = (wp.getX() - CULL_CHUNK_BORDERS_RANGE + MAP_SQUARE_SIZE - 1) / MAP_SQUARE_SIZE * MAP_SQUARE_SIZE;
        int startY = (wp.getY() - CULL_CHUNK_BORDERS_RANGE + MAP_SQUARE_SIZE - 1) / MAP_SQUARE_SIZE * MAP_SQUARE_SIZE;
        int endX = (wp.getX() + CULL_CHUNK_BORDERS_RANGE) / MAP_SQUARE_SIZE * MAP_SQUARE_SIZE;
        int endY = (wp.getY() + CULL_CHUNK_BORDERS_RANGE) / MAP_SQUARE_SIZE * MAP_SQUARE_SIZE;

        graphics.setStroke(new BasicStroke(config.regionBorderWidth()));
        Color c = config.regionBorderColor();
        graphics.setColor(c);

        GeneralPath path = new GeneralPath();
        for (int x = startX; x <= endX; x += MAP_SQUARE_SIZE)
        {
            LocalPoint lp1 = LocalPoint.fromWorld(client, x, wp.getY() - CULL_CHUNK_BORDERS_RANGE);
            LocalPoint lp2 = LocalPoint.fromWorld(client, x, wp.getY() + CULL_CHUNK_BORDERS_RANGE);

            boolean first = true;
            for (int y = lp1.getY(); y <= lp2.getY(); y += LOCAL_TILE_SIZE)
            {
                Point p = Perspective.localToCanvas(client,
                        new LocalPoint(lp1.getX() - LOCAL_TILE_SIZE / 2, y - LOCAL_TILE_SIZE / 2),
                        client.getPlane());
                if (p != null)
                {
                    if (first)
                    {
                        path.moveTo(p.getX(), p.getY());
                        first = false;
                    }
                    else
                    {
                        path.lineTo(p.getX(), p.getY());
                    }
                }
            }
        }
        for (int y = startY; y <= endY; y += MAP_SQUARE_SIZE)
        {
            LocalPoint lp1 = LocalPoint.fromWorld(client, wp.getX() - CULL_CHUNK_BORDERS_RANGE, y);
            LocalPoint lp2 = LocalPoint.fromWorld(client, wp.getX() + CULL_CHUNK_BORDERS_RANGE, y);

            boolean first = true;
            for (int x = lp1.getX(); x <= lp2.getX(); x += LOCAL_TILE_SIZE)
            {
                Point p = Perspective.localToCanvas(client,
                        new LocalPoint(x - LOCAL_TILE_SIZE / 2, lp1.getY() - LOCAL_TILE_SIZE / 2),
                        client.getPlane());
                if (p != null)
                {
                    if (first)
                    {
                        path.moveTo(p.getX(), p.getY());
                        first = false;
                    }
                    else
                    {
                        path.lineTo(p.getX(), p.getY());
                    }
                }
            }
        }
        graphics.draw(path);
    }
}