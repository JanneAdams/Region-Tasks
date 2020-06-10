package regionlocker;

import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionLocker {
    private final Client client;
    private final RegionLockerConfig config;
    private final ConfigManager configManager;

    @Getter
    public static Map<String, RegionTypes> regions = new HashMap<>();

    public static boolean renderLockedRegions;
    public static Color grayColor;
    public static int grayAmount;
    public static boolean hardBorder;
    private static boolean unlockRealms;
    private static boolean unlockUnderground;

    @Inject
    RegionLocker(Client client, RegionLockerConfig config, ConfigManager configManager) {
        this.client = client;
        this.config = config;
        this.configManager = configManager;
        readConfig();
    }

    private List<String> StringToList(String s) {
        List<String> regs;
        if (s.isEmpty())
            regs = new ArrayList<>();
        else
            regs = new ArrayList<>(Text.fromCSV(s));
        return regs;
    }

    public void readConfig() {
        renderLockedRegions = config.renderLockedRegions();
        unlockRealms = config.unlockRealms();
        unlockUnderground = config.unlockUnderground();
        grayColor = config.shaderGrayColor();
        grayAmount = config.shaderGrayAmount().getAlpha();
        hardBorder = config.hardBorder();

        regions.clear();

        String unlockedString = config.unlockedRegions();
        List<String> unlockedRegions = StringToList(unlockedString);
        setRegions(unlockedRegions, RegionTypes.UNLOCKED);

        String unlockableString = config.unlockableRegions();
        List<String> unlockableRegions = StringToList(unlockableString);
        setRegions(unlockableRegions, RegionTypes.UNLOCKABLE);

        String blacklistedString = config.blacklistedRegions();
        List<String> blacklistedRegions = StringToList(blacklistedString);
        setRegions(blacklistedRegions, RegionTypes.BLACKLISTED);

        String activeString = config.activeRegion();
        List<String> activeRegion = StringToList(activeString);
        setRegions(activeRegion, RegionTypes.ACTIVE);
    }

    private void setRegions(List<String> regs, RegionTypes type) {
        for (String id : regs) {
            regions.put(id, type);
        }
    }

    private void setConfig() {
        List<String> unlockedRegions = new ArrayList<>();
        List<String> unlockableRegions = new ArrayList<>();
        List<String> blacklistedRegions = new ArrayList<>();
        List<String> activeRegion = new ArrayList<>();;

        regions.forEach((key, value) -> {
            if (value == RegionTypes.UNLOCKED) unlockedRegions.add(key);
            if (value == RegionTypes.UNLOCKABLE) unlockableRegions.add(key);
            if (value == RegionTypes.BLACKLISTED) blacklistedRegions.add(key);
            if (value == RegionTypes.ACTIVE) activeRegion.add(key);
        });

        String csv = Text.toCSV(unlockedRegions);
        configManager.setConfiguration(RegionLockerPlugin.CONFIG_KEY, "unlockedRegions", csv);

        csv = Text.toCSV(unlockableRegions);
        configManager.setConfiguration(RegionLockerPlugin.CONFIG_KEY, "unlockableRegions", csv);

        csv = Text.toCSV(blacklistedRegions);
        configManager.setConfiguration(RegionLockerPlugin.CONFIG_KEY, "blacklistedRegions", csv);

        csv = Text.toCSV(activeRegion);
        configManager.setConfiguration(RegionLockerPlugin.CONFIG_KEY, "activeRegion", csv);
    }

    public void addRegion(int regionId) {
        String id = Integer.toString(regionId);
        RegionTypes type = regions.get(id);
        if (type == null)
            regions.put(id, RegionTypes.UNLOCKABLE);
        else if (type == RegionTypes.UNLOCKABLE){
            for (HashMap.Entry<String, RegionTypes> region : regions.entrySet()) {
                if (region.getValue().equals(RegionTypes.ACTIVE))
                    regions.put(region.getKey(), RegionTypes.UNLOCKED);
            }
            regions.put(id, RegionTypes.ACTIVE);
        }
        else
            regions.remove(id);
        setConfig();
    }

    public void blockRegion(int regionId) {
        String id = Integer.toString(regionId);
        RegionTypes type = regions.get(id);
        if (type != RegionTypes.BLACKLISTED)
            if (type == null) regions.put(id, RegionTypes.BLACKLISTED);
            else regions.replace(id, RegionTypes.BLACKLISTED);
        else
            regions.remove(id);
        setConfig();
    }

    public static RegionTypes getType(int regionId) {
        String id = Integer.toString(regionId);
        int y = getY(regionId);
        if (unlockRealms && y >= 4160 && y < 5952) return RegionTypes.UNLOCKED;
        if (unlockUnderground && y >= 8960) return RegionTypes.UNLOCKED;
        if (regions == null) return null;
        return regions.get(id);
    }

    public static boolean hasRegion(int regionId) {
        RegionTypes type = getType(regionId);
        if (type == null) return false;
        return type == RegionTypes.UNLOCKED || type == RegionTypes.ACTIVE;
    }

    public static boolean isUnlockable(int regionId) {
        RegionTypes type = getType(regionId);
        if (type == null) return false;
        return type == RegionTypes.UNLOCKABLE;
    }

    public static boolean isBlacklisted(int regionId) {
        RegionTypes type = getType(regionId);
        if (type == null) return false;
        return type == RegionTypes.BLACKLISTED;
    }

    public String getActiveRegion(){
        Map<String, RegionTypes> regionSet = regions;

        for (HashMap.Entry<String, RegionTypes> region : regionSet.entrySet()) {
            if (region.getValue().equals(RegionTypes.ACTIVE))
                return region.getKey();
        }
        return null;
    }

    public ArrayList<Integer> getUnlockedRegions(){
        Map<String, RegionTypes> regionSet = regions;
        ArrayList<Integer> unlockedRegions = new ArrayList<>();

        for (HashMap.Entry<String, RegionTypes> region : regionSet.entrySet()) {
            if (region.getValue().equals(RegionTypes.UNLOCKED) || region.getValue().equals(RegionTypes.ACTIVE))
                unlockedRegions.add(Integer.parseInt(region.getKey()));
        }
        return unlockedRegions;
    }

    public static int getX(int id) {
        return ((id >> 8) << 6);
    }

    public static int getY(int id) {
        return ((id & 255) << 6);
    }
}
