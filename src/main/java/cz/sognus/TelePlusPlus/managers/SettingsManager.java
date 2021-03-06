package cz.sognus.TelePlusPlus.managers;

import cz.sognus.TelePlusPlus.TelePlusPlus;
import cz.sognus.TelePlusPlus.enums.TransparentMaterials;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Material;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SettingsManager
{
    public boolean logPlayer;
    public boolean logCoords;
    public boolean logWorld;
    public boolean logHere;
    public boolean logMass;
    public boolean logTop;
    public boolean logUp;
    public boolean logOthersPlayer;
    public boolean logOthersCoords;
    public boolean logAbove;
    public boolean logJump;
    public boolean logToggle;
    public boolean logBack;
    public boolean logOrigin;
    public boolean logTool;
    public boolean logMover;
    public boolean logRequest;

    public boolean notifyPlayer;
    public boolean notifyCoords;
    public boolean notifyWorld;
    public boolean notifyHere;
    public boolean notifyMass;
    public boolean notifyTop;
    public boolean notifyUp;
    public boolean notifyOthersPlayer;
    public boolean notifyOthersCoords;
    public boolean notifyAbove;
    public boolean notifyToggle;
    public boolean notifyJump;
    public boolean notifyBack;
    public boolean notifyOrigin;
    public boolean notifyTool;
    public boolean notifyMover;
    public boolean notifyRequest;

    public boolean sayPlayer;
    public boolean sayCoords;
    public boolean sayWorld;
    public boolean sayHere;
    public boolean sayMass;
    public boolean sayTop;
    public boolean sayUp;
    public boolean sayOthersPlayer;
    public boolean sayOthersCoords;
    public boolean sayAbove;
    public boolean sayToggle;
    public boolean sayJump;
    public boolean sayBack;
    public boolean sayOrigin;
    public boolean sayTool;
    public boolean sayMover;
    public boolean sayRequest;

    public boolean disablePlayer;
    public boolean disableCoords;
    public boolean disableWorld;
    public boolean disableHere;
    public boolean disableMass;
    public boolean disableTop;
    public boolean disableUp;
    public boolean disableOthersPlayer;
    public boolean disableOthersCoords;
    public boolean disableAbove;
    public boolean disableToggle;
    public boolean disableJump;
    public boolean disableClear;
    public boolean disableBack;
    public boolean disableOrigin;
    public boolean disableTool;
    public boolean disableMover;
    public boolean disableRequest;
    public boolean explosionEffect;
    public boolean clientSideGlass;

    public int settingsFallBlockDistance;
    public long fallImmunitySeconds;
    public boolean fallImmunity;
    private boolean noCrossWorldTps;
    public int purgeRequestMinutes;
    public Material moverItem;
    public Material toolItem;
    private int pageSize;
    private List<Material> throughFieldsSet = new LinkedList<Material>();
    private Material[] throughFields = TransparentMaterials.array;
    private HashMap<String, Material> throughMap = TransparentMaterials.map;

    // Cooldown between two actions in nanoseconds
    public long actionCooldown;
    public boolean actionMessage;

    // Internal
    public double checkDistance = 0.1;
    public int maxDistance = 1000;

    private TelePlusPlus plugin;
    private File main;
    private FileConfiguration config;


    public SettingsManager(TelePlusPlus plugin)
    {
        this.plugin = plugin;
        config = plugin.getConfig();
        main = new File(plugin.getDataFolder() + File.separator + "config.yml");
        loadConfiguration();
    }

    /**
     * Load the configuration
     */
    @SuppressWarnings("unchecked")
    public void loadConfiguration()
    {
        boolean exists = (main).exists();

        if (exists)
        {
            try
            {
                config.options().copyDefaults(true);
                config.load(main);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            config.options().copyDefaults(true);
        }

        for (Material throughField : throughFields)
        {
            throughFieldsSet.add(throughField);
        }

        logPlayer = config.getBoolean("log.tp.player", false);
        logCoords = config.getBoolean("log.tp.coords", false);
        logHere = config.getBoolean("log.tp.here", false);
        logToggle = config.getBoolean("log.tp.toggle", false);
        logBack = config.getBoolean("log.tp.back", false);
        logOrigin = config.getBoolean("log.tp.origin", false);
        logOthersPlayer = config.getBoolean("log.others.player", false);
        logOthersCoords = config.getBoolean("log.others.coords", false);
        logWorld = config.getBoolean("log.world.tp", false);
        logTop = config.getBoolean("log.jump.top", false);
        logUp = config.getBoolean("log.jump.up", false);
        logJump = config.getBoolean("log.jump.jump", false);
        logAbove = config.getBoolean("log.mod.above", false);
        logMass = config.getBoolean("log.mod.mass", false);
        logTool = config.getBoolean("log.mod.tool", false);
        logMover = config.getBoolean("log.mod.mover", false);
        logRequest = config.getBoolean("log.request", false);

        notifyPlayer = config.getBoolean("notify.tp.player", false);
        notifyCoords = config.getBoolean("notify.tp.coords", false);
        notifyHere = config.getBoolean("notify.tp.here", false);
        notifyToggle = config.getBoolean("notify.tp.toggle", false);
        notifyBack = config.getBoolean("notify.tp.back", false);
        notifyOrigin = config.getBoolean("notify.tp.origin", false);
        notifyOthersPlayer = config.getBoolean("notify.others.player", false);
        notifyOthersCoords = config.getBoolean("notify.others.coords", false);
        notifyWorld = config.getBoolean("notify.world.tp", false);
        notifyTop = config.getBoolean("notify.jump.top", false);
        notifyUp = config.getBoolean("notify.jump.up", false);
        notifyJump = config.getBoolean("notify.jump.jump", false);
        notifyAbove = config.getBoolean("notify.mod.above", false);
        notifyMass = config.getBoolean("notify.mod.mass", false);
        notifyTool = config.getBoolean("notify.mod.tool", false);
        notifyMover = config.getBoolean("notify.mod.mover", false);
        notifyRequest = config.getBoolean("notify.request", false);

        sayPlayer = config.getBoolean("say.tp.player", false);
        sayCoords = config.getBoolean("say.tp.coords", false);
        sayHere = config.getBoolean("say.tp.here", false);
        sayToggle = config.getBoolean("say.tp.toggle", false);
        sayBack = config.getBoolean("say.tp.back", false);
        sayOrigin = config.getBoolean("say.tp.origin", false);
        sayOthersPlayer = config.getBoolean("say.others.player", false);
        sayOthersCoords = config.getBoolean("say.others.coords", false);
        sayWorld = config.getBoolean("say.world.tp", false);
        sayTop = config.getBoolean("say.jump.top", false);
        sayUp = config.getBoolean("say.jump.up", false);
        sayJump = config.getBoolean("say.jump.jump", false);
        sayAbove = config.getBoolean("say.mod.above", false);
        sayMass = config.getBoolean("say.mod.mass", false);
        sayTool = config.getBoolean("say.mod.tool", false);
        sayMover = config.getBoolean("say.mod.mover", false);
        sayRequest = config.getBoolean("say.request", false);

        disablePlayer = config.getBoolean("disable.tp.player", false);
        disableCoords = config.getBoolean("disable.tp.coords", false);
        disableHere = config.getBoolean("disable.tp.here", false);
        disableToggle = config.getBoolean("disable.tp.toggle", false);
        disableClear = config.getBoolean("disable.tp.clear", false);
        disableBack = config.getBoolean("disable.tp.back", false);
        disableOrigin = config.getBoolean("disable.tp.origin", false);
        disableOthersPlayer = config.getBoolean("disable.others.player", false);
        disableOthersCoords = config.getBoolean("disable.others.coords", false);
        disableWorld = config.getBoolean("disable.world.tp", false);
        disableTop = config.getBoolean("disable.jump.top", false);
        disableUp = config.getBoolean("disable.jump.up", false);
        disableJump = config.getBoolean("disable.jump.jump", false);
        disableAbove = config.getBoolean("disable.mod.above", false);
        disableMass = config.getBoolean("disable.mod.mass", false);
        disableTool = config.getBoolean("disable.mod.tool", false);
        disableMover = config.getBoolean("disable.mod.mover", false);
        disableRequest = config.getBoolean("disable.request", false);

        settingsFallBlockDistance = config.getInt("glassed.fall-block-distance", 10);
        fallImmunity = config.getBoolean("glassed.fall-immunity", false);
        fallImmunitySeconds = config.getInt("glassed.fall-immunity-seconds", 5);

        noCrossWorldTps = config.getBoolean("settings.no-cross-world-tps", false);
        purgeRequestMinutes = config.getInt("settings.purge-requests-minutes", 5);

        /* Names are case SENSITIVE - CAPITAL */
        moverItem = Material.getMaterial(config.getString("settings.mover-item", "STICK").toUpperCase());
        toolItem = Material.getMaterial(config.getString("settings.tool-item", "BONE").toUpperCase());

        /* Check items for config failure - replacing it with default items */
        if(toolItem == null) { Bukkit.getLogger().severe("[TelePlusPlus] Configuration of settings.tool-item is not valid, using default value (BONE)"); }
        if(moverItem == null) { Bukkit.getLogger().severe("[TelePlusPlus] Configuration of settings.mover-item is not valid, using default value (STICK)"); }
        moverItem = moverItem != null ? moverItem : Material.STICK;
        toolItem = toolItem != null ? toolItem : Material.BONE;

        /* Cooldown handling */
        actionMessage = config.getBoolean("settings.action-cooldown-message", true);
        String cooldownType = config.getString("settings.action-cooldown-time-unit", "ms");
        long timeMultiple;
        switch(cooldownType)
        {
            case "ns":
            case "nanosecond":
                timeMultiple = 1;
                break;
            case "ms":
            case "milisecond":
                timeMultiple = 1000000;
                break;
            case "s":
            case "sec":
            case "second":
               timeMultiple = 1000000000;
               break;
               default:
                   timeMultiple = 1000000;
                   break;
        }

        try
        {
            actionCooldown = config.getInt("settings.action-cooldown", 500);
            actionCooldown = timeMultiple;
        }
        catch (Exception e)
        {
            // Default value
            actionCooldown = 500 * 1000000;
        }

        // Internal config
        checkDistance = config.getDouble("settings.internal-check-distance", 0.1 );
        maxDistance = config.getInt("settings.internal-maximum-distance", 1000);

        // Others
        pageSize = config.getInt("settings.page-size", 10);
        clientSideGlass = config.getBoolean("settings.client-side-glass", true);
        explosionEffect = config.getBoolean("settings.explosion-effect", true);

        save();
    }

    /**
     *
     */
    public void save()
    {
        try
        {
            config.save(main);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *  @deprecated  since 3.0.1
     *  Use {@link #getThroughMap()}  }
     *
     * @return the throughFieldsSet
     */
    @Deprecated
    public List<Material> getThroughFieldsSet()
    {
        return throughFieldsSet;
    }

    /** @since 3.0.1
     *
     * @return through map
     *
     */
    public HashMap<String, Material> getThroughMap(){ return throughMap;}

    public boolean isSeeThrough(Object id)
    {
        Material material = null;

        if(id instanceof Block)
        {
            material = ((Block)(id)).getType();
        }

        if(id instanceof Material)
        {
            material = (Material)(id);
        }

        if(material == null)
        {
            // Assume false or failed input
            return false;
        }

        if(throughMap == null) {
            return throughFieldsSet.contains(material);
        }
        else
        {
            return throughMap.containsKey(material.name());
        }
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public boolean isNoCrossWorldTps()
    {
        return noCrossWorldTps;
    }
}