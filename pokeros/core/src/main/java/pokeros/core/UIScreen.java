//
// Mashups - a series of mashed up game prototypes
// https://github.com/samskivert/mashups/blob/master/LICENSE

package pokeros.core;

import react.UnitSlot;

import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;

public abstract class UIScreen extends tripleplay.game.UIScreen {

  protected UIScreen (Pokeros game) {
    _game = game;
  }

  @Override public void wasAdded () {
    super.wasAdded();

    Root root = iface.createRoot(AxisLayout.vertical(), UI.stylesheet(), layer);
    root.addStyles(Style.BACKGROUND.is(Background.image(_game.media.felt).inset(10)));
    root.add(new Shim(1, 5),
             new Label(title()).addStyles(
               Style.FONT.is(UI.defaultFont.derive(64)), Style.AUTO_SHRINK.on));
    createUI(root);
    root.setSize(width(), height());
  }

  protected void pop () {
    _game.screens.remove(this);
  }

  protected UnitSlot popSlot () {
    return new UnitSlot() { public void onEmit () { pop(); }};
  }

  protected abstract String title ();
  protected abstract void createUI (Root root);

  protected final Pokeros _game;
}