package sk.adonikeoffice.epicmenu.registry.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {
	CLOSE("[close]"), PLAYER("[player]"), CONSOLE("[console]"), RESTART_MENU("[restart_menu]"), OPEN_MENU("[open_menu]");

	private final String actionType;
}
