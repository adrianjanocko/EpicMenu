package sk.adonikeoffice.epicmenu.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import sk.adonikeoffice.epicmenu.menu.model.Menu;
import sk.adonikeoffice.epicmenu.registry.MenuRegistry;
import sk.adonikeoffice.epicmenu.registry.data.MenuData;

public class CommandListener implements Listener {

	@EventHandler
	public void onCommand(final PlayerCommandPreprocessEvent event) {
		final MenuRegistry menu = MenuRegistry.getInstance();
		final Player player = event.getPlayer();

		for (final MenuData menuData : menu.getLoadedMenus())
			for (final String command : menuData.getCommands()) {
				if (event.getMessage().startsWith("/" + command)) {
					new Menu(menuData, player).displayTo(event.getPlayer());

					event.setCancelled(true);
				}
			}
	}

}
