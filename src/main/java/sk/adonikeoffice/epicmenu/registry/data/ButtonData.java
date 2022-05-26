package sk.adonikeoffice.epicmenu.registry.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.model.ConfigSerializable;
import org.mineacademy.fo.model.Variables;
import org.mineacademy.fo.remain.CompMaterial;
import sk.adonikeoffice.epicmenu.registry.MenuRegistry;

import java.util.List;

@Getter
@AllArgsConstructor
public class ButtonData implements ConfigSerializable {

	private int slot;
	private CompMaterial material;
	private String name;
	private int amount;
	private boolean glowEnabled;
	private List<String> lore;
	private List<String> actions;

	@Override
	public SerializedMap serialize() {
		return SerializedMap.ofArray(
				"Slot", this.slot,
				"Material", this.material,
				"Name", this.name,
				"Amount", this.amount,
				"Glow", this.glowEnabled,
				"Lore", this.lore,
				"Actions", this.actions
		);
	}

	public static ButtonData deserialize(final SerializedMap map) {
		final int slot = map.getInteger("Slot");
		final CompMaterial material = map.getMaterial("Material");
		final String name = map.getString("Name");
		final int amount = map.getInteger("Amount");
		final boolean glow = map.getBoolean("Glow");
		final List<String> lore = map.getList("Lore", String.class);
		final List<String> actions = map.getList("Actions", String.class);

		return new ButtonData(slot, material, name, amount, glow, lore, actions);
	}

	public Button toButton(final Player player) {
		final List<String> lore = getLore();
		lore.replaceAll(string -> Variables.replace(string, player));

		return Button.makeSimple(ItemCreator.of(
				getMaterial(),
				getName(),
				lore
		).amount(getAmount()).glow(isGlowEnabled()), (target) -> {
			for (final String action : getActions()) {
				final String[] args = action.split(" ");
				final String command = Common.joinRange(1, args);

				if (action.equals(ActionType.CLOSE.getActionType()))
					target.closeInventory();

				if (action.startsWith(ActionType.PLAYER.getActionType()))
					Common.dispatchCommandAsPlayer(target, command);

				if (action.startsWith(ActionType.CONSOLE.getActionType()))
					Common.dispatchCommand(target, command);

				if (action.startsWith(ActionType.RESTART_MENU.getActionType()))
					Menu.getMenu(player).restartMenu(command);
				else if (action.equals(ActionType.RESTART_MENU.getActionType()))
					Menu.getMenu(player).restartMenu();

				if (action.startsWith(ActionType.OPEN_MENU.getActionType()))
					new sk.adonikeoffice.epicmenu.menu.model.Menu(MenuRegistry.getInstance().getMenu(command), player).displayTo(player);
			}
		});
	}

}