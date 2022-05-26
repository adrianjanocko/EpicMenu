package sk.adonikeoffice.epicmenu;

import org.mineacademy.fo.Valid;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;
import sk.adonikeoffice.epicmenu.command.MenuCommand;
import sk.adonikeoffice.epicmenu.listener.CommandListener;
import sk.adonikeoffice.epicmenu.registry.MenuRegistry;
import sk.adonikeoffice.epicmenu.settings.Localization;
import sk.adonikeoffice.epicmenu.settings.Settings;

import java.util.Arrays;
import java.util.List;

public final class EpicMenuPlugin extends SimplePlugin {

	@Override
	protected void onPluginStart() {
	}

	@Override
	protected void onReloadablesStart() {
		Valid.checkBoolean(HookManager.isPlaceholderAPILoaded(), "You need to install PlaceholderAPI to the server, if you want to support placeholders.");

		new MenuRegistry();

		registerCommand(new MenuCommand());
		registerEvents(new CommandListener());
	}

	@Override
	public List<Class<? extends YamlStaticConfig>> getSettings() {
		return Arrays.asList(Settings.class, Localization.class);
	}

}
