//
// Mashups - a series of mashed up game prototypes
// https://github.com/samskivert/mashups/blob/master/LICENSE

package pokeros.core;

import playn.core.*;
import playn.core.util.TextBlock;
import static playn.core.PlayN.*;

import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import tripleplay.util.StyledText;
import tripleplay.util.TextStyle;

public class UI {

  public static final int GREEN = 0xFF008A44;
  public static final Font defaultFont = graphics().createFont("Copperplate", Font.Style.PLAIN, 18);

  public static final Styles bigButtonStyles = Styles.
    make(Style.FONT.is(UI.defaultFont.derive(48f)),
         Style.BACKGROUND.is(Background.blank().inset(0, 2, 2, 0))).
    addSelected(Style.BACKGROUND.is(Background.blank().inset(2, 0, 0, 2)));

  public static final Styles medButtonStyles = bigButtonStyles.add(
    Style.FONT.is(UI.defaultFont.derive(32f)));

  public static final Font textFont = graphics().createFont("Times New Roman", Font.Style.PLAIN, 18);
  public static final Styles textStyles = Styles.make(
    Style.TEXT_WRAP.on, Style.HALIGN.left, Style.FONT.is(textFont));

  public static final Styles titleStyles = Styles.make(
    Style.TEXT_EFFECT.vectorOutline, Style.COLOR.is(0xFFFFFFFF), Style.HIGHLIGHT.is(0xCC000000));

  public static Stylesheet.Builder newBuilder () {
    return SimpleStyles.newSheetBuilder().
      add(Element.class, Style.COLOR.is(0xFFFFFFFF), Style.FONT.is(defaultFont)).
      add(Button.class, Style.BACKGROUND.is(Background.blank().inset(0, 1, 1, 0)),
          Style.TEXT_EFFECT.shadow, Style.SHADOW.is(0x55000000),
          Style.SHADOW_X.is(2f), Style.SHADOW_Y.is(2f)).
      add(Button.class, Style.Mode.SELECTED, Style.SHADOW.is(0x00000000),
          Style.BACKGROUND.is(Background.blank().inset(1, 0, 0, 1))).
      add(Button.class, Style.Mode.DISABLED, Style.TEXT_EFFECT.none);
  }

  public static Stylesheet stylesheet () {
    return newBuilder().create();
  }

  public static Shim stretchShim () {
    return AxisLayout.stretch(new Shim(1, 1));
  }

  public static final TextStyle marqueeStyle = new TextStyle().withTextColor(GREEN).
    withOutline(0xFF000000, 1.5f).
    withFont(graphics().createFont(defaultFont.name(), Font.Style.BOLD, 48));
  public static ImageLayer mkMarquee (String text) {
    return StyledText.span(text, marqueeStyle).toLayer();
  }

  public static final TextStyle handStyle = new TextStyle().withTextColor(GREEN).
    withOutline(0xFF000000, 1.5f).
    withFont(graphics().createFont(defaultFont.name(), Font.Style.BOLD, 32));
  public static final TextStyle pointsStyle = new TextStyle().withTextColor(0xFFFFFFFF).
    withOutline(0xFF000000, 1.5f).
    withFont(graphics().createFont(defaultFont.name(), Font.Style.BOLD, 64));
  public static ImageLayer mkScore (String descrip, String score, float screenWidth) {
    StyledText dblock = new StyledText.Block(
      descrip, handStyle, new TextWrap(screenWidth), TextBlock.Align.CENTER);
    StyledText sblock = StyledText.span(score, pointsStyle);
    CanvasImage image = graphics().createImage(Math.max(dblock.width(), sblock.width()),
                                               dblock.height() + sblock.height());
    dblock.render(image.canvas(), (image.width() - dblock.width())/2, 0);
    sblock.render(image.canvas(), (image.width() - sblock.width())/2, dblock.height());
    return graphics().createImageLayer(image);
  }

  public static final TextStyle tipStyle = new TextStyle().withTextColor(0xFFFFFFFF).
    withShadow(0xFF000000, 1f, 1f).
    withFont(graphics().createFont(defaultFont.name(), Font.Style.PLAIN, 14));
  public static ImageLayer mkTip (String text) {
    return StyledText.block(text, tipStyle, 130).toLayer();
  }
}
