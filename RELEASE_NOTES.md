# First Edition Companion v0.8.0

v0.8.0 begins a focused character-creation refinement pass, starting with race and subrace selection.

## Character creation changes
- Replaced the free-form **Race** field with a source-aware race/subrace dropdown.
- Added native PHB race choices and relevant PHB variations, including Human, standard/hill and mountain Dwarves, High Elves, Surface Gnomes, Half-Elves, Half-Orcs, and Hairfoot/Stout/Tallfellow halflings.
- Added supplied-UA-variant choices including Gray/Duergar Dwarves, Gray, Wood/Sylvan, Wild/Grugach, and Dark/Drow Elves, Deep/Svirfneblin Gnomes, Half-Elf ancestry variants, and Half-Ogres.
- Split male and female drow into separate choices because the supplied UA variant gives them different starting ability-score adjustments.
- Selecting a race immediately displays its racial ability-score adjustments and source.
- Added **Race Details**, a native/offline reference containing racial abilities, resistances, vision, languages, class/multiclass notes, combat/detection traits, and restrictions where the supplied sources support them.
- Race Details do not require linked PDFs.
- Existing custom or legacy race values are preserved rather than overwritten during upgrade.
- Added a **Custom…** race option for campaign-specific and house-ruled ancestries.
- The app deliberately does not automatically change stored STR/INT/WIS/DEX/CON/CHA values when changing the dropdown, preventing racial modifiers from being accidentally applied twice to existing characters.

## Source integrity
Core race mechanics are grounded in the supplied AD&D 1e Players Handbook. The PHB establishes the seven main player-character racial stocks and provides the racial ability adjustments and special capabilities used by the built-in core entries.

The supplied `unearthedarcana.pdf` explicitly expands player race/subrace options, but that supplied file also contains later/conversion-style mechanics. Those race entries are therefore clearly labeled **supplied UA variant** in the app instead of being silently presented as verified original 1985 rules.

All racial descriptions are paraphrased table-use summaries stored natively in the APK.

## Updating
v0.8.0 uses the same stable prototype signing key as previous prototype releases and should install directly over v0.7.1 while preserving local character data. Export Saves before major updates is still recommended.

## Installation
Download the attached `FirstEditionCompanion-v0.8.0.apk` and install it on Android.
