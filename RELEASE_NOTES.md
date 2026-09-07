# First Edition Companion v0.6.0

This release begins the source-grounded rules-reference pass using the rulebooks supplied for the project, with the 1978 AD&D 1e *Players Handbook* treated as the verified primary source.

## Highlights
- Added structured PHB spell-reference data: school/type, range, duration, area of effect, components, casting time, saving throw, source page, and a concise rules digest where the source can be matched confidently.
- PHB spell details now resolve dynamically, so characters created in older versions benefit from the richer reference without needing to recreate Spellbook or prepared-spell entries.
- Added a new **Rules** tab with table-ready PHB references for spell preparation and memorization, spellcasting and interruption, currency and money changing, armor and shield handling, weapon proficiency and weapon factors, encumbrance and movement, light and surprise, turning undead, saving throws and Armor Class, damage, falling, healing, and experience.
- Added optional local rulebook linking. Select your own PHB/UA/DMG PDF with Android's file picker, and source-grounded PHB entries can open the cited page inside the app.
- Gear catalog entries now expose a **Rules** action with PHB handling where the PHB supports it.
- Added PHB shield coverage mechanics, weapon-proficiency context, weapon-factor guidance, light-source rules, currency handling, and encumbrance guidance.
- Combat now includes source-grounded quick references for AC/saving throws, spell interruption, damage, falling, and healing.
- v0.6 backups identify themselves as v0.6.0 while retaining compatibility with the existing portable backup format.

## Source integrity
The supplied *Players Handbook* is a 1978 AD&D 1e PHB scan and is used as the authoritative PHB source in this release.

The supplied `unearthedarcana.pdf` uses original-style presentation but its internal rules text contains later revised/variant mechanics and terminology rather than matching a clean original 1985 TSR *Unearthed Arcana*. To avoid silently mixing editions, v0.6 keeps existing UA catalog entries but does not overwrite the verified PHB rules layer with those variant mechanics.

Many detailed magic-item powers live in the *Dungeon Masters Guide*. Because a DMG PDF was not supplied for this pass, DMG-only magic items remain clearly source-tagged and the app does not invent or guess their powers.

## Updating
v0.6.0 uses the same stable prototype signing key as v0.4.0 and v0.5.0, so it should install directly over v0.5.0 while preserving local data. **Export Saves** before updating is still recommended.

## Installation
Download the attached `FirstEditionCompanion-v0.6.0.apk` from this release and install it on Android.
