package sk.adonikeoffice.epicmenu.registry;

import lombok.Getter;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.settings.YamlConfig;
import sk.adonikeoffice.epicmenu.registry.data.MenuData;

import java.util.List;

@Getter
public class MenuRegistry extends YamlConfig {

	@Getter
	private static final MenuRegistry instance = new MenuRegistry();

	private List<MenuData> loadedMenus;

	public MenuRegistry() {
		this.loadConfiguration("menu_example.yml", "menus.yml");
	}

	@Override
	protected void onLoadFinish() {
		this.loadedMenus = getList("Menus", MenuData.class);
	}

	@Override
	protected SerializedMap serialize() {
		return SerializedMap.ofArray(
				"Menus", this.loadedMenus
		);
	}

	public void createMenu(final MenuData menu) {
		Valid.checkBoolean(!isLoaded(menu.getName()), "Menu " + menu.getName() + " is already loaded.");

		this.loadedMenus.add(menu);
		this.save();
	}

	public void removeMenu(final MenuData menu) {
		Valid.checkBoolean(isLoaded(menu.getName()), "Menu " + menu.getName() + " is not defined.");

		this.loadedMenus.remove(menu);
		this.save();
	}

	public boolean isLoaded(final String name) {
		for (final MenuData menu : getLoadedMenus())
			if (menu.getName().equals(name))
				return true;

		return false;
	}

	public MenuData getMenu(final String name) {
		for (final MenuData menu : getLoadedMenus())
			if (menu.getName().equals(name))
				return menu;

		return null;
	}

}
