package sk.adonikeoffice.epicmenu.menu.model;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.SimpleSound;
import org.mineacademy.fo.model.Variables;
import sk.adonikeoffice.epicmenu.registry.data.ButtonData;
import sk.adonikeoffice.epicmenu.registry.data.InfoData;
import sk.adonikeoffice.epicmenu.registry.data.MenuData;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Menu extends org.mineacademy.fo.menu.Menu {

	private final MenuData menu;
	private final Player player;

	public Menu(final MenuData menu, final Player player) {
		this.menu = menu;
		this.player = player;

		this.setTitle(menu.getTitle());
		this.setSize(9 * menu.getRows());
		setSound(new SimpleSound(menu.getOpenSound().name()));
	}

	@Override
	protected void onMenuClick(final Player player, final int slot, final ItemStack clicked) {
	}

	@Override
	protected List<Button> getButtonsToAutoRegister() {
		final List<Button> button = new ArrayList<>();

		for (final ButtonData createdButton : menu.getButton())
			button.add(createdButton.toButton(player));

		return button;
	}

	@Override
	public ItemStack getItemAt(final int slot) {
		for (final ButtonData button : menu.getButton())
			if (slot == button.getSlot())
				return button.toButton(player).getItem();

		final InfoData infoButton = menu.getInfo();

		if (slot == infoButton.getSlot()) {
			final List<String> infoLore = infoButton.getLore();
			infoLore.replaceAll(string -> Variables.replace(string, player));

			return ItemCreator.of(infoButton.getMaterial(), infoButton.getName(), infoLore).build().makeMenuTool();
		}

		return menu.getFill().isFillEnabled() ? ItemCreator.of(menu.getFill().getMaterial()).build().makeMenuTool() : null;
	}

}
