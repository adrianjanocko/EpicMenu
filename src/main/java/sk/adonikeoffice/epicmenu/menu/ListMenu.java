package sk.adonikeoffice.epicmenu.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.remain.CompMaterial;
import sk.adonikeoffice.epicmenu.menu.model.Menu;
import sk.adonikeoffice.epicmenu.registry.MenuRegistry;
import sk.adonikeoffice.epicmenu.registry.data.MenuData;

public class ListMenu extends MenuPagged<MenuData> {

	private final Button restartMenusButton;

	public ListMenu() {
		super(MenuRegistry.getInstance().getLoadedMenus());

		this.restartMenusButton = Button.makeSimple(ItemCreator.of(CompMaterial.CLOCK, "Reload Menus"), (target) -> {
			SimplePlugin.getInstance().reload();

			restartMenu("Restarted!");
		});
	}

	@Override
	protected ItemStack convertToItemStack(final MenuData menu) {
		return ItemCreator.of(CompMaterial.PAPER, menu.getName(), " ", " Click to edit").build().makeMenuTool();
	}

	@Override
	protected void onPageClick(final Player player, final MenuData menu, final ClickType click) {
		if (click == ClickType.LEFT) {
			new Menu(menu, player).displayTo(player);
		} else if (click == ClickType.RIGHT) {
			MenuRegistry.getInstance().removeMenu(menu);

			restartMenu("Deleted!");
		}
	}

	@Override
	public ItemStack getItemAt(final int slot) {
		if (slot == getCenterSlot())
			return restartMenusButton.getItem();

		return super.getItemAt(slot);
	}

}
