package cz.sognus.TelePlusPlus.managers;

import cz.sognus.TelePlusPlus.TelePlusPlus;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class PermissionsManager
{
    private TelePlusPlus plugin;

    public final String menu = "tpp.tp.menu";
    public final String player = "tpp.tp.player";
    public final String coords = "tpp.tp.coords";
    public final String here = "tpp.tp.here";
    public final String toggle = "tpp.tp.toggle";
    public final String back = "tpp.tp.back";
    public final String origin = "tpp.tp.origin";
    public final String clear = "tpp.tp.clear";
    public final String othersPlayer = "tpp.others.player";
    public final String othersCoords = "tpp.others.coords";
    public final String world = "tpp.world.tp";
    public final String top = "tpp.jump.top";
    public final String up = "tpp.jump.up";
    public final String jump = "tpp.jump.jump";
    public final String above = "tpp.mod.above";
    public final String mass = "tpp.mod.mass";
    public final String tool = "tpp.mod.tool";
    public final String mover = "tpp.mod.mover";
    public final String noTp = "tpp.mod.notp";
    public final String notify = "tpp.mod.notify";
    public final String take = "tpp.mod.take";
    public final String bypassLog = "tpp.admin.bypass.log";
    public final String bypassNotify = "tpp.admin.bypass.notify";
    public final String bypassNoTp = "tpp.admin.bypass.notp";
    public final String request = "tpp.request";

    public PermissionsManager(TelePlusPlus plugin)
    {
        this.plugin = plugin;
    }

    public boolean hasPermission(CommandSender sender, String permission)
    {
        if (sender == null)
        {
            return true;
        }

        return sender.hasPermission(permission);
    }

    public boolean isVanished(Player player)
    {
        List<MetadataValue> values = player.getMetadata("vanished");

        for (MetadataValue value : values)
        {
            if(value.asBoolean())
            {
                return true;
            }
        }

        return false;
    }
}
