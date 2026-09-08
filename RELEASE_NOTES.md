# First Edition Companion v0.8.1

v0.8.1 continues the character-creation refinement pass by replacing the free-form class field with a native class/subclass selector and detailed offline class reference.

## Character class changes
- Replaced the free-form **Class(es)** field with a **Class / Subclass** dropdown.
- Added a separate class-level field while continuing to store the combined class/level value in the existing character data format for backward compatibility.
- Added PHB entries for Cleric, Druid, Fighter, Paladin, Ranger, Magic-User, Illusionist, Thief, Assassin, Monk, and the optional PHB Appendix II Bard.
- Added the five fully described classes from the supplied UA-variant source: Cloistered Cleric, Anti-Paladin, Duelist, Necromancer, and Psionicist.
- Selecting a class immediately displays its minimum requirements/prime requisite, hit die, alignment restriction, and source.
- Added **Class Details**, a native/offline reference covering armor and weapons, spellcasting model, major class abilities, high-level abilities/restrictions, and advancement notes.
- Added a native **Multi-class / Dual-class Rules** reference summarizing the PHB distinction between nonhuman multiclassing and human dual-classing.
- Existing custom or multiclass class strings are preserved as legacy entries rather than overwritten.
- Added **Custom / Multi-class…** for campaign-specific or hand-entered combinations.
- Class selection does not automatically rewrite HP, THAC0, saving throws, alignment, spell tracks, or ability scores, preventing existing characters from being unintentionally changed.

## Source integrity
Core class information is grounded in the supplied AD&D 1e Players Handbook, including class requirements, hit dice, alignment restrictions, class abilities, spellcasting, armor/weapon limits, and multiclass/dual-class procedures.

The supplied `unearthedarcana.pdf` contains later/conversion-style mechanics. Its class entries are therefore labeled **supplied UA variant** in the app. The file references additional names such as Barbarian and Cavalier but does not provide full standalone class descriptions for them in the supplied source, so v0.8.1 does not invent missing mechanics.

All built-in descriptions are paraphrased table-use summaries stored natively in the APK.

## Updating
v0.8.1 uses the same stable prototype signing key as previous prototype releases and should install directly over v0.8.0 while preserving local character data. Export Saves before major updates is still recommended.

## Installation
Download the attached `FirstEditionCompanion-v0.8.1.apk` and install it on Android.
