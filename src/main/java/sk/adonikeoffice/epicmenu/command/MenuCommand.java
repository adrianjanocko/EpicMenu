package sk.adonikeoffice.epicmenu.command;

import org.mineacademy.fo.command.SimpleCommand;
import sk.adonikeoffice.epicmenu.menu.ListMenu;

public class MenuCommand extends SimpleCommand {

	public MenuCommand() {
		super("menu");

		setPermission(null);
	}

	@Override
	protected void onCommand() {
		new ListMenu().displayTo(getPlayer());
	}

}
