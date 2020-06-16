package regionlocker;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup(RegionLockerPlugin.CONFIG_KEY)
public interface RegionLockerConfig extends Config {
    @ConfigItem(
            keyName = "renderLockedRegions",
            name = "Enable gray chunks",
            description = "Adds graphical change to all chunk that are locked",
            position = 0
    )
    default boolean renderLockedRegions()
    {
        return true;
    }

    @ConfigItem(
            keyName = "renderRegionBorders",
            name = "Render chunk borders",
            description = "Draws the chunk borders in the environment",
            position = 1
    )
    default boolean renderRegionBorders()
    {
        return false;
    }

    @ConfigItem(
            keyName = "unlockUnderground",
            name = "Unlock underground",
            description = "Unlock all underground chunks",
            position = 4
    )
    default boolean unlockUnderground()
    {
        return true;
    }

    @ConfigItem(
            keyName = "unlockRealms",
            name = "Unlock realms",
            description = "Unlock all realm chunks like Zanaris and the TzHaar area",
            position = 5
    )
    default boolean unlockRealms()
    {
        return true;
    }

    @ConfigItem(
            keyName = "drawMapOverlay",
            name = "Draw chunks on map",
            description = "Draw a color overlay for each locked/unlocked chunk",
            position = 6
    )
    default boolean drawMapOverlay()
    {
        return true;
    }

    @ConfigItem(
            keyName = "invertMapOverlay",
            name = "Invert map overlay",
            description = "Switches which chunks the map will draw the color overlay for (true = locked, false = unlocked)",
            position = 7
    )
    default boolean invertMapOverlay()
    {
        return true;
    }

    @ConfigItem(
            keyName = "hardBorder",
            name = "Hard chunk border cutoff",
            description = "Switches which chunks the map will draw the color overlay for (true = locked, false = unlocked)",
            position = 8
    )
    default boolean hardBorder()
    {
        return true;
    }

    @Alpha
    @ConfigItem(
            keyName = "shaderGrayColor",
            name = "Chunk shader color",
            description = "The color of the locked chunks in the shader",
            position = 9
    )
    default Color shaderGrayColor()
    {
        return new Color(0, 31, 77, 204);
    }

    @Alpha
    @ConfigItem(
            keyName = "shaderGrayAmount",
            name = "Chunk gray opacity",
            description = "The amount of gray scale that is applied to a locked chunk in the shader (alpha only)",
            position = 10
    )
    default Color shaderGrayAmount()
    {
        return new Color(0, 0, 0, 204);
    }

    @Alpha
    @ConfigItem(
            keyName = "mapOverlayColor",
            name = "Map overlay color",
            description = "The color the map overlay will draw the chunks in",
            position = 11
    )
    default Color mapOverlayColor()
    {
        return new Color(200, 16, 0, 100);
    }

    @Alpha
    @ConfigItem(
            keyName = "unlockableOverlayColor",
            name = "Unlockable overlay color",
            description = "The color the map overlay will draw the unlockable chunks in",
            position = 12
    )
    default Color unlockableOverlayColor()
    {
        return new Color(60, 200, 160, 100);
    }

    @Alpha
    @ConfigItem(
            keyName = "blacklistedOverlayColor",
            name = "Blacklisted overlay color",
            description = "The color the map overlay will draw the blacklisted chunks in",
            position = 13
    )
    default Color blacklistedOverlayColor()
    {
        return new Color(0, 0, 0, 200);
    }

    @Alpha
    @ConfigItem(
            keyName = "regionBorderColor",
            name = "Chunk border color",
            description = "The color of the chunk borders",
            position = 14
    )
    default Color regionBorderColor()
    {
        return new Color(0, 200, 83, 200);
    }

    @ConfigItem(
            keyName = "regionBorderWidth",
            name = "Chunk border width",
            description = "How wide the region border will be",
            position = 15
    )
    default int regionBorderWidth()
    {
        return 1;
    }

    @ConfigItem(
            keyName = "drawMapGrid",
            name = "Draw map grid",
            description = "Draw the grid of chunks on the map",
            position = 16
    )
    default boolean drawMapGrid()
    {
        return true;
    }

    @ConfigItem(
            keyName = "drawRegionId",
            name = "Draw region IDs",
            description = "Draw the chunk ID for each chunk on the map",
            position = 17
    )
    default boolean drawRegionId()
    {
        return true;
    }

    @ConfigItem(
            keyName = "unlockKey",
            name = "Unlock hotkey",
            description = "When you hold this key you can click on the map to unlock a chunk",
            position = 19
    )
    default Keybind unlockKey()
    {
        return Keybind.SHIFT;
    }

    @ConfigItem(
            keyName = "blacklistKey",
            name = "Blacklist hotkey",
            description = "When you hold this key you can click on the map to blacklist a chunk",
            position = 20
    )
    default Keybind blacklistKey()
    {
        return Keybind.CTRL;
    }

    @ConfigItem(
            keyName = "activeRegion",
            name = "Active region",
            description = "Single active region",
            position = 21
    )
    default String activeRegion() {
        return "";
    }

    @ConfigItem(
            keyName = "unlockedRegions",
            name = "Unlocked regions",
            description = "List of unlocked regions seperated by a ',' symbol",
            position = 22
    )
    default String unlockedRegions() {
        return "";
    }

    @ConfigItem(
            keyName = "unlockableRegions",
            name = "Unlockable regions",
            description = "List of unlockable regions seperated by a ',' symbol",
            position = 23
    )
    default String unlockableRegions() {
        return "";
    }

    @ConfigItem(
            keyName = "blacklistedRegions",
            name = "Blacklisted regions",
            description = "List of blacklisted regions seperated by a ',' symbol",
            position = 24
    )
    default String blacklistedRegions() {
        return "";
    }


}
